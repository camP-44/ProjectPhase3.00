package application.models;

import java.util.Date;
import java.util.List;

public class Patient extends Person {
    private String contactInfo;
    private String insuranceInfo;
    private String pharmacyInfo;
    private List<Visit> visitHistory;
    private List<Message> messages;

    public Patient(String firstName, String lastName, Date birthDate) {
        super(firstName, lastName, birthDate);
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public String getPharmacyInfo() {
        return pharmacyInfo;
    }

    public void setPharmacyInfo(String pharmacyInfo) {
        this.pharmacyInfo = pharmacyInfo;
    }

    public List<Visit> getVisitHistory() {
        return visitHistory;
    }

    public void setVisitHistory(List<Visit> visitHistory) {
        this.visitHistory = visitHistory;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    protected String generateAccountId() {
        return getFirstName().toLowerCase() + getLastName().toLowerCase() + getBirthDateAsKey();
    }
}
