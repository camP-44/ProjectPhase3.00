package application.models;

import java.util.Date;

public class Doctor extends Person {
    private String specialty;
    private String licenseNumber;

    public Doctor(String firstName, String lastName, String specialty, String licenseNumber, Date birthDate) {
        super(firstName, lastName, birthDate);
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    protected String generateAccountId() {
        return getFirstName().toLowerCase() + getLastName().toLowerCase() + getBirthDateAsKey();
    }
}
