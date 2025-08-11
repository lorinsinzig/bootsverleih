package controllers;

import auth.Authenticated;
import models.DatabaseExecutionContext;
import models.ReservationData;
import play.api.i18n.MessagesApi;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Security;

import javax.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.*;

public class RentController {

    private Database db;
    private DatabaseExecutionContext executionContext;
    ReservationData data;
    private MessagesApi messagesApi;
    private FormFactory formFactory;

    @Security.Authenticated(Authenticated.class)
    public Result rent(Http.Request request) {
        Form<ReservationData> reservationForm = formFactory.form(ReservationData.class);
        return ok(views.html.rental.render(reservationForm, request, messagesApi.preferred(request)));
    }

    @Inject
    public RentController(Database db, DatabaseExecutionContext executionContext, FormFactory formFactory, MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
        this.db = db;
        this.executionContext = executionContext;
    }

    @Security.Authenticated(Authenticated.class)
    public Result create(Http.Request request) {
        Form<ReservationData> reservationForm = formFactory.form(ReservationData.class);

        // Bind the form data from the request
        Form<ReservationData> boundForm = reservationForm.bindFromRequest(request);

        // validate the input
        if (boundForm.hasErrors()) {
            System.out.println("Form has errors:");
            for (play.data.validation.ValidationError error : boundForm.errors()) {
                System.out.println("Field: " + error.key() + " - Error: " + error.message());
            }
            return badRequest(views.html.rental.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            data = boundForm.get();
        }

        try {
            DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // If any of these checks fail, an exception will be thrown
            LocalDate parsedDate = LocalDate.parse(data.getDate(), inputDateFormatter);
            LocalTime parsedTimeStart = LocalTime.parse(data.getTimeStart(), inputTimeFormatter);
            LocalTime parsedTimeEnd = LocalTime.parse(data.getTimeEnd(), inputTimeFormatter);

        } catch (DateTimeParseException e) {
            System.out.println("Date/Time parsing error: " + e.getMessage());

            Form<ReservationData> formWithErrors = boundForm.withError("date", "Please provide a valid date and time.");
            return badRequest(views.html.rental.render(formWithErrors, request, messagesApi.preferred(request)));
        }

        try {
            insertReservation(data);
        } catch (SQLException e) {
            // Log the full error for debugging
            e.printStackTrace();
            // Add a global form error to show the user something went wrong
            Form<ReservationData> formWithDbError = reservationForm.withError("", "Could not save reservation due to a database error. Please try again later.");
            return internalServerError(views.html.rental.render(formWithDbError, request, messagesApi.preferred(request)));
        }

        return redirect(routes.RentController.rent());
    }

    private CompletionStage insertReservation(ReservationData data) throws SQLException {

        // Use a PreparedStatement to prevent SQL injection attacks
        return CompletableFuture.runAsync(
                () -> {
                    String sql = "INSERT INTO Reservationsanfragen (name, email, tel_nr, date, time_start, time_end) VALUES (?, ?, ?, ?, ?, ?)";

                    try (Connection connection = db.getConnection();
                         PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, data.getName());
                        stmt.setString(2, data.getEmail());
                        stmt.setString(3, data.getTelNr());
                        stmt.setDate(4, java.sql.Date.valueOf(LocalDate.parse(data.getDate())));
                        stmt.setTime(5, java.sql.Time.valueOf(LocalTime.parse(data.getTimeStart())));
                        stmt.setTime(6, java.sql.Time.valueOf(LocalTime.parse(data.getTimeEnd())));

                        stmt.executeUpdate();
                        System.out.println("Reservation successfully saved to database for: " + data.getName());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                executionContext);
    }
}
