package auth;

import play.mvc.Security;
import play.mvc.Http;
import play.mvc.Result;
import java.util.Optional;

public class Authenticated extends Security.Authenticator {
    @Override
    public Optional<String> getUsername(Http.Request req) {
        return req.session().get("username");
    }

    @Override
    public Result onUnauthorized(Http.Request req) {
        return redirect(controllers.routes.AuthController.auth());
    }
}