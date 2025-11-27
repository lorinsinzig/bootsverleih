package models;

import io.ebean.Model;
import models.finders.BoatFinder;
import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
@Table(name = "boat")
public class Boat extends Model {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "kfz")
    @Constraints.Required(message = "KFZ is required")
    public String kfz;

    @Column(name = "name")
    @Constraints.Required(message = "Name is required")
    public String name;

    public static final BoatFinder FINDER = new BoatFinder();

    public String getKfz() {
        return kfz;
    }

    public void setKfz(String kfz) {
        this.kfz = kfz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}