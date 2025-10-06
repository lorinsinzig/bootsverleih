package models;

import io.ebean.Model;
import models.finders.BoatFinder;

import javax.persistence.*;

@Entity
@Table(name = "boat")
public class Boat extends Model {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "kfz")
    public String kfz;

    @Column(name = "name")
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