package models;

import play.data.validation.Constraints;

public class BoatData {
    @Constraints.Required
    protected int id;

    @Constraints.Required(message = "KFZ is required")
    protected String kfz;

    @Constraints.Required(message = "Boats name is required")
    protected String name;

    public BoatData(int id, String kfz, String name) {
        this.id = id;
        this.kfz = kfz;
        this.name = name;
    }

    public BoatData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
