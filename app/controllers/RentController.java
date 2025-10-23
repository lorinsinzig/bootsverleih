package controllers;

import models.Boat;
import models.Reservation;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;

import play.libs.Json;
import play.libs.Scala;
import play.mvc.Http;
import play.mvc.Result;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import static play.mvc.Results.*;

public class RentController {

    Reservation data;
    private MessagesApi messagesApi;
    private FormFactory formFactory;
    Form<Reservation> reservationForm;

    public Result rent(Http.Request request) {
        reservationForm = formFactory.form(Reservation.class);
        List<Boat> boats = getBoats();

        return ok(views.html.rent.render(reservationForm, Scala.asScala(boats), request, messagesApi.preferred(request)));
    }

    @Inject
    public RentController(FormFactory formFactory, MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
    }

    private List<Boat> getBoats() {
        return Boat.FINDER.getAllBoats();
    }

    public Result getReservations(int id, String date) {
        List<Reservation> reservations = Reservation.FINDER.getSameDayReservations(Boat.FINDER.byId(id), date);

        return ok(Json.toJson(reservations));
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

        data.setTimeStart(LocalTime.parse(data.timeStartString));
        data.setTimeEnd(LocalTime.parse(data.timeEndString));

        // Check for existing reservation in 15-minute range
        List<Reservation> concurrentReservations = Reservation.FINDER.getConcurrentReservations(data.getBoat(), data.date, data.timeStart, data.timeEnd);

        if (!concurrentReservations.isEmpty()) {
            List<Boat> boats = getBoats();

            System.out.println("Reservation in selbem Zeitraum bereits bestehend.");

            return ok(views.html.rent.render(reservationForm, Scala.asScala(boats), request, messagesApi.preferred(request)));
        }

        Reservation reservation = new Reservation();

        reservation.name = data.name;
        reservation.email = data.email;
        reservation.telNr = data.telNr;
        reservation.date = data.date;
        reservation.timeStart = data.timeStart;
        reservation.timeEnd = data.timeEnd;
        reservation.setBoat(data.boat);
        reservation.save();

        return redirect(routes.HomeController.index());
    }
}
