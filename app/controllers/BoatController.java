package controllers;

import models.Boat;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BoatController extends Controller {

    private MessagesApi messagesApi;
    private FormFactory formFactory;
    Boat data;

    public Result boat(Http.Request request) {
        List<Boat> boats = Boat.FINDER.getAllBoats();

        return ok(views.html.boats.render(boats, request));
    }

    @Inject
    public BoatController(MessagesApi messagesApi, FormFactory formfactory) {
        this.messagesApi = messagesApi;
        this.formFactory = formfactory;
    }

    public Result newBoat(Http.Request request) {
        Form<Boat> boatForm = formFactory.form(Boat.class);

        return ok(views.html.newboat.render(boatForm, request, messagesApi.preferred(request)));
    }

    public Result createBoat(Http.Request request) {
        Form<Boat> boatForm = formFactory.form(Boat.class);

        Form<Boat> boundForm = boatForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            System.out.println("Form has errors:");
            for (play.data.validation.ValidationError error : boundForm.errors()) {
                System.out.println("Field: " + error.key() + " - Error: " + error.message());
            }
            return badRequest(views.html.newboat.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            data = boundForm.get();
        }

        Boat boat = new Boat();

        boat.kfz = data.kfz;
        boat.name = data.name;
        boat.save();

        return redirect(routes.BoatController.boat());
    }

    public Result deleteBoat(int id) {
        Boat boat = Boat.FINDER.byId(id);

        if (boat == null) {
            return notFound("Boat was not found");
        }

        boat.delete();

        return ok("Boat deleted successfully.");
    }
}