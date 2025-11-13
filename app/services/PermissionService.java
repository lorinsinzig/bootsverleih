package services;

import controllers.routes;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Optional;

public class PermissionService extends Security.Authenticator {

    public Optional<String> getUsername(Http.Request request) {
        Optional<String> id = request.session().getOptional("id");
        Optional<String> admin = request.session().getOptional("isAdmin");

        if (id.isPresent() && admin.isPresent() && admin.get().equals("1")) {
            return id;
        }

        return Optional.empty();
    }

    @Override
    public Result onUnauthorized(Http.Request request) {
        if (request.session().getOptional("id").isPresent() && request.session().getOptional("isAdmin").isPresent()) {
            return redirect(controllers.routes.HomeController.index())
                .flashing("error", "Sie haben keine Berechtigung für diese Aktion");
        } else {
            return redirect(controllers.routes.HomeController.login());
        }
    }
}