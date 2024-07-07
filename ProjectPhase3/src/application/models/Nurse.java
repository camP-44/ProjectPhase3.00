package application.models;

import java.util.Date;

public class Nurse extends Person {
    private String nurseId;
    private String department;

    public Nurse(String firstName, String lastName, String nurseId, String department, Date birthDate) {
        super(firstName, lastName, birthDate);
        this.nurseId = nurseId;
        this.department = department;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    protected String generateAccountId() {
        return getFirstName().toLowerCase() + getLastName().toLowerCase() + getBirthDateAsKey();
    }
}
