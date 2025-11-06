package models.finders;

import io.ebean.Finder;
import models.Boat;
import models.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservationFinder extends Finder<Integer, Reservation> {

    public ReservationFinder() {
        super(Reservation.class);
    }

    public List<Reservation> getConcurrentReservations(Boat boat, String date, LocalTime timeStart, LocalTime timeEnd) {
        LocalTime windowStart = timeStart.minusMinutes(15);
        LocalTime windowEnd = timeEnd.plusMinutes(15);

        return query()
                .where()
                .eq("boot_id", boat.id)
                .eq("date", date)
                .lt("time_start", windowEnd)
                .gt("time_end", windowStart)
                .findList();
    }

    public List<Reservation> getSameDayReservations(Boat boat, String date) {
        return query()
                .where()
                .eq("boot_id", boat.id)
                .eq("date", date)
                .findList();
    }

    public List<Reservation> getAllAheadReservations(LocalDate date) {
        return query()
                .fetch("boat")
                .where()
                .gt("date", date)
                .orderBy("time_start")
                .findList();
    }
}