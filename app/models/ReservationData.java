package models;

import play.data.validation.Constraints;

public class ReservationData {
    @Constraints.Required(message = "Name is required")
    protected String name;

    @Constraints.Required(message = "Email is required")
    @Constraints.Email(message = "Invalid email format")
    protected String email;

    @Constraints.Required(message = "Phone number is required")
    @Constraints.Pattern(value = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
    protected String telNr;

    @Constraints.Required(message = "Date is required")
    protected String date;

    @Constraints.Required(message = "Time is required")
    protected String timeStart;

    @Constraints.Required(message = "Time is required")
    protected String timeEnd;

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
    public String getTelNr() {
        return telNr;
    }
    public void setTelNr(String telNr) {
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
    public void setTimeStart(String time_start) {
        this.timeStart = time_start;
    }
    public String getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(String time_end) {
        this.timeEnd = time_end;
    }
}
