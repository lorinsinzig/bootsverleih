package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Model;
import models.finders.BoatFinder;
import models.finders.ReservationFinder;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
public class Reservation extends Model {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    @Constraints.Required(message = "Name is required")
    public String name;

    @Column(name = "email")
    @Constraints.Required(message = "E-Mail is required")
    public String email;

    @Column(name = "tel_nr")
    @Constraints.Required(message = "TelNr is required")
    public Integer telNr;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    @Constraints.Required(message = "Date is required")
    public String date;

    @Transient
    public String timeStartString;

    @Column(name = "time_start")
    @Constraints.Required(message = "Starting time is required")
    public LocalTime timeStart;

    @Transient
    public String timeEndString;

    @Column(name = "time_end")
    @Constraints.Required(message = "Ending time is required")
    public LocalTime timeEnd;

    @Transient
    public String boatId;

    @ManyToOne
    @JoinColumn(name = "boot_id", referencedColumnName = "id")
    public Boat boat;

    public static final ReservationFinder FINDER = new ReservationFinder();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelNr() {
        return telNr;
    }

    public void setTelNr(Integer telNr) {
        this.telNr = telNr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getBoatId() {
        return boatId;
    }

    public void setBoatId(String boatId) {
        this.boatId = boatId;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public String getTimeStartString() {
        return timeStartString;
    }

    public void setTimeStartString(String timeStartString) {
        this.timeStartString = timeStartString;
    }

    public String getTimeEndString() {
        return timeEndString;
    }

    public void setTimeEndString(String timeEndString) {
        this.timeEndString = timeEndString;
    }
}
