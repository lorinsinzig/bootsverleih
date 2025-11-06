package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.LoginService;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class ContactController {

    public Result contact(Http.Request request) {
        return ok(views.html.contact.render(request));
    }

    @Inject
    public ContactController() {

    }
}
