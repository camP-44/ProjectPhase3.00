package application.controllers;

import application.models.Patient;
import application.utils.FileSystemHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientRegistrationController {
    private final FileSystemHandler fileSystemHandler;

    public PatientRegistrationController() {
        this.fileSystemHandler = FileSystemHandler.getInstance();
    }

    public void handleRegistration(String firstName, String lastName, String birthDateString, String password) {
        Date birthDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            birthDate = dateFormat.parse(birthDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM-dd-yyyy.");
            return;
        }

        // Generate account ID based on first name, last name, and birth date
        String accountId = generateAccountId(firstName, lastName, birthDate);

        // Create patient object
        Patient patient = new Patient(firstName, lastName, birthDate);

        // Save patient information using FileSystemHandler
        fileSystemHandler.savePatient(patient, password);

        System.out.println("Patient registered successfully!");
        System.out.println("Account ID: " + accountId);
    }

    private String generateAccountId(String firstName, String lastName, Date birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        String formattedDate = dateFormat.format(birthDate);
        return firstName.toLowerCase() + lastName.toLowerCase() + formattedDate;
    }
}
