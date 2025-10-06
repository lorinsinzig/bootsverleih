package controllers;

import models.Boat;
import models.Reservation;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;

import play.libs.Scala;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import javax.inject.Inject;

import static play.mvc.Results.*;

public class RentController {

    Reservation data;
    private MessagesApi messagesApi;
    private FormFactory formFactory;

    public Result rent(Http.Request request) {
        Form<Reservation> reservationForm = formFactory.form(Reservation.class);
        List<Boat> boats = getBoats();

        return ok(views.html.rent.render(reservationForm, Scala.asScala(boats), request, messagesApi.preferred(request)));
    }

    @Inject
    public RentController(FormFactory formFactory, MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
    }

    private List<Boat> getBoats() {
        List<Boat> boats = Boat.FINDER.query().orderBy("kfz desc").findList();

        return boats;
    }

    public Result create(Http.Request request) {
        Form<Reservation> reservationForm = formFactory.form(Reservation.class).bindFromRequest(request);

        if (reservationForm.hasErrors()) {
            List<Boat> boats = getBoats();
            return badRequest(views.html.rent.render(reservationForm, Scala.asScala(boats), request, messagesApi.preferred(request)));
        }

        data = reservationForm.get();

        Integer boatId = Integer.parseInt(data.boatId);
        data.setBoat(Boat.FINDER.byId(boatId));

        Reservation reservation = new Reservation();

        reservation.name = data.name;
        reservation.email = data.email;
        reservation.telNr = data.telNr;
        reservation.date = data.date;
        reservation.timeStart = data.timeStart;
        reservation.timeEnd = data.timeEnd;
        reservation.setBoat(data.boat);
        reservation.save();

        return redirect(routes.RentController.rent());
    }
}
