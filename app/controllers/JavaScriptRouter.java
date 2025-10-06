package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.JavaScriptReverseRouter;

public class JavaScriptRouter extends Controller {

    // Make sure the method accepts the Http.Request
    public Result javascriptRoutes(Http.Request request) {
        return ok(
                JavaScriptReverseRouter.create(
                        "jsRoutes",
                        "xhr",

                        request.host(),

                        routes.javascript.BoatController.deleteBoat()
                )
        ).as(Http.MimeTypes.JAVASCRIPT);
    }
}