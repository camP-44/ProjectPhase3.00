package application.models;

import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Person(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    protected abstract String generateAccountId();

    protected String getBirthDateAsKey() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddYYYY");
        return dateFormat.format(birthDate);
    }
}
