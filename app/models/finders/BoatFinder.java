package models.finders;

import io.ebean.Finder;
import models.Boat;

import java.util.List;

public class BoatFinder extends Finder<Integer, Boat> {

    public BoatFinder() {
        super(Boat.class);
    }
}
