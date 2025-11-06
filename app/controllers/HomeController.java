package controllers;

import models.User;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import javax.inject.Inject;

public class HomeController extends Controller {

    private MessagesApi messagesApi;
    private FormFactory formFactory;
    User data;

    @Inject
    public HomeController(FormFactory formfactory,  MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
        this.formFactory = formfactory;
    }

    public Result index(Http.Request request) {
        return ok(views.html.index.render(request));
    }

    public Result login(Http.Request request) {
        Form<User> userForm = formFactory.form(User.class);

        return ok(views.html.login.render(userForm, request, messagesApi.preferred(request)));
    }

    public Result authenticate(Http.Request request) {
        Form<User> userForm = formFactory.form(User.class);

        Form<User> boundForm = userForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            System.out.println("Form has errors:");
            for (play.data.validation.ValidationError error : boundForm.errors()) {
                System.out.println("Field: " + error.key() + " - Error: " + error.message());
            }
            return badRequest(views.html.login.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            data = boundForm.get();
        }

        User user = User.FINDER.getUserByCredentials(data.username, data.password);

        System.out.println(user.username + ' ' + user.password);

        if (user == null) {
            return redirect(routes.HomeController.login())
                    .flashing("danger", "Invalid username or password.");
        } else {
            String originalUrl = request.session().get("originalUrl").orElse(routes.HomeController.index().url());

            Http.Session newSession = request.session()
                        .adding("id", user.id.toString())
                        .adding("isAdmin", user.admin)
                        .removing("originalUrl");

            return redirect(originalUrl)
                    .withSession(newSession);
        }
    }
}
