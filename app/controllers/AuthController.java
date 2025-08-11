package controllers;

import models.DatabaseExecutionContext;
import models.UserData;
import play.api.i18n.MessagesApi;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;
import play.data.Form;
import play.data.FormFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.*;

public class AuthController {

    private Database db;
    private DatabaseExecutionContext executionContext;
    private MessagesApi messagesApi;
    private FormFactory formFactory;

    public Result auth(Http.Request request) {
        Form<UserData> userForm = formFactory.form(UserData.class);
        return ok(views.html.login.render(userForm, request, messagesApi.preferred(request)));
    }

    @Inject
    public AuthController(Database db, DatabaseExecutionContext executionContext, FormFactory formFactory, MessagesApi messagesApi) {
        this.db = db;
        this.executionContext = executionContext;
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
    }

    public CompletionStage<Result> create(Http.Request request) {
        Form<UserData> userForm = formFactory.form(UserData.class);
        Form<UserData> boundForm = userForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            return CompletableFuture.completedFuture(
                    badRequest(views.html.login.render(boundForm, request, messagesApi.preferred(request)))
            );
        }

        UserData data = boundForm.get();

        return login(data).thenApplyAsync(isLoginSuccessful -> {
            if (isLoginSuccessful) {
                return redirect(routes.RentController.rent())
                        .addingToSession(request, "username", data.getUsername());
            } else {
                return badRequest(views.html.login.render(boundForm, request, messagesApi.preferred(request)));
            }
        }, executionContext.current()).exceptionally(throwable -> {
            throwable.printStackTrace();
            return internalServerError(views.html.login.render(boundForm, request, messagesApi.preferred(request)));
        });
    }

    private CompletionStage<Boolean> login(UserData data) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT 1 FROM user WHERE username = ? AND password = ?";
            try (Connection connection = db.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, data.getUsername());
                stmt.setString(2, data.getPassword());

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executionContext.current());
    }
}
