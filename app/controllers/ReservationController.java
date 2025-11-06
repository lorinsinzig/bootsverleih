package controllers;

import models.Reservation;
import play.api.i18n.MessagesApi;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.LoginService;
import services.PermissionService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static play.mvc.Results.ok;

public class ReservationController {

    private MessagesApi messagesApi;
    private FormFactory formFactory;

    @Inject
    public ReservationController(MessagesApi messagesApi, FormFactory formfactory) {
        this.messagesApi = messagesApi;
        this.formFactory = formfactory;
    }

    @Security.Authenticated(PermissionService.class)
    public Result reservations(Http.Request request) {
        List<Reservation> reservations = Reservation.FINDER.getAllAheadReservations(LocalDate.now());

        return ok(views.html.reservations.render(reservations, request));
    }
}
