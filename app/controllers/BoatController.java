package controllers;

import auth.Authenticated;
import models.BoatData;
import models.DatabaseExecutionContext;
import models.BoatData;
import models.UserData;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import scala.collection.immutable.List;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class BoatController extends Controller {

    private Database db;
    private DatabaseExecutionContext executionContext;
    private MessagesApi messagesApi;
    private FormFactory formFactory;
    BoatData data;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    public CompletionStage<Result> boat(Http.Request request) {
        CompletionStage<java.util.List<BoatData>> boats;

        try {
            boats = list();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return boats.thenApply(boatList -> {
            if (boatList.isEmpty()) {
                return redirect(routes.BoatController.newboat());
            } else {
                return ok(views.html.boats.render(boatList, request))
                        .withHeader("Cache-Control", "no-cache, no-store, must-revalidate")
                        .withHeader("Pragma", "no-cache")
                        .withHeader("Expires", "0");
            }
        });
    }

    @Inject
    public BoatController(Database db, DatabaseExecutionContext executionContext, MessagesApi messagesApi, FormFactory formfactory) {
        this.db = db;
        this.executionContext = executionContext;
        this.messagesApi = messagesApi;
        this.formFactory = formfactory;
    }

    @Security.Authenticated(Authenticated.class)
    public Result newboat(Http.Request request) {
        Form<BoatData> boatForm = formFactory.form(BoatData.class);

        return ok(views.html.newboat.render(boatForm, request, messagesApi.preferred(request)));
    }

    private CompletionStage<java.util.List<BoatData>> list() throws SQLException {
        return CompletableFuture.supplyAsync(() -> {
            java.util.List<BoatData> boats = new java.util.ArrayList<>();
            String sql = "SELECT * FROM Boot";

            try (Connection connection = db.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        BoatData boat = new BoatData(
                            rs.getInt("id"),
                            rs.getString("kfz"),
                            rs.getString("name")
                        );

                        boats.add(boat);
                    }

                    return boats;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executionContext.current());
    }

    public Result createboat(Http.Request request) {
        Form<BoatData> boatForm = formFactory.form(BoatData.class);

        // Bind the form data from the request
        Form<BoatData> boundForm = boatForm.bindFromRequest(request);

        // validate the input
        if (boundForm.hasErrors()) {
            System.out.println("Form has errors:");
            for (play.data.validation.ValidationError error : boundForm.errors()) {
                System.out.println("Field: " + error.key() + " - Error: " + error.message());
            }
            return badRequest(views.html.newboat.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            data = boundForm.get();
        }

        try {
            insertBoat(data);
        } catch (SQLException e) {
            // Log the full error for debugging
            e.printStackTrace();
            // Add a global form error to show the user something went wrong
            Form<BoatData> formWithDbError = boatForm.withError("", "Could not save reservation due to a database error. Please try again later.");
            return internalServerError(views.html.newboat.render(formWithDbError, request, messagesApi.preferred(request)));
        }

        return redirect(routes.BoatController.boat());
    }

    private CompletionStage insertBoat(BoatData data) throws SQLException {

        // Use a PreparedStatement to prevent SQL injection attacks
        return CompletableFuture.runAsync(
                () -> {
                    String sql = "INSERT INTO Boot (kfz, name) VALUES (?, ?)";

                    try (Connection connection = db.getConnection();
                         PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, data.getKfz());
                        stmt.setString(2, data.getName());

                        stmt.executeUpdate();
                        System.out.println("Boat successfully created to database for: " + data.getName());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                executionContext);
    }

    public CompletionStage<Result> deleteboat(int id) {
        return CompletableFuture.runAsync(
                        () -> {
                            String sql = "DELETE FROM Boot WHERE id = ?";

                            try (Connection connection = db.getConnection();
                                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                                stmt.setInt(1, id);

                                int rowsAffected = stmt.executeUpdate();
                                if (rowsAffected == 0) {
                                    System.out.println("No boat found with id: " + id);
                                } else {
                                    System.out.println("Boat with id " + id + " successfully deleted.");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        executionContext)
                .thenApply(v -> {
                    return ok("Boat deleted successfully.");
                }).exceptionally(ex -> {
                    ex.printStackTrace();
                    return internalServerError("Failed to delete boat.");
                });
    }
}