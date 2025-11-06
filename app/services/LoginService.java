package services;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Optional;

public class LoginService extends Security.Authenticator {

    @Override
    public Optional getUsername(Http.Request request) {
        return request.session().getOptional("id");
    }

    @Override
    public Result onUnauthorized(Http.Request request) {
        return redirect(controllers.routes.HomeController.login())
                .withSession(request.session().adding("originalUrl", request.uri()));
    }
}