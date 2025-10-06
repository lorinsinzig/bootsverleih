package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation extends Model {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "tel_nr")
    public Integer telNr;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    public String date;

    @Formats.DateTime(pattern = "HH:mm")
    @Column(name = "time_start")
    public String timeStart;

    @Formats.DateTime(pattern = "HH:mm")
    @Column(name = "time_end")
    public String timeEnd;

    @Transient
    public String boatId;

    @ManyToOne
    @JoinColumn(name = "boot_id", referencedColumnName = "id")
    public Boat boat;

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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
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
}
