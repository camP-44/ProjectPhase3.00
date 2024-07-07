package application.utils;

import application.models.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemHandler {
    private static final String PATIENTS_FILE = "patients.txt";
    private static final String DOCTORS_FILE = "doctors.txt";
    private static final String NURSES_FILE = "nurses.txt";
    private static final String MESSAGES_FILE = "messages.txt";
    private static final String VISITS_FILE = "visits.txt";

    private static FileSystemHandler instance;

    private FileSystemHandler() {}

    public static synchronized FileSystemHandler getInstance() {
        if (instance == null) {
            instance = new FileSystemHandler();
        }
        return instance;
    }

    // Save methods
    public void savePatient(Patient patient, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENTS_FILE, true))) {
            writer.write(patient.getFirstName() + "," + patient.getLastName() + "," + formatDate(patient.getBirthDate()) + ","
                    + patient.getContactInfo() + "," + patient.getInsuranceInfo() + "," + patient.getPharmacyInfo() + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDoctor(Doctor doctor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORS_FILE, true))) {
            writer.write(doctor.getFirstName() + "," + doctor.getLastName() + "," + doctor.getSpecialty() + ","
                    + doctor.getLicenseNumber() + "," + formatDate(doctor.getBirthDate()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNurse(Nurse nurse) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NURSES_FILE, true))) {
            writer.write(nurse.getFirstName() + "," + nurse.getLastName() + "," + nurse.getNurseId() + ","
                    + nurse.getDepartment() + "," + formatDate(nurse.getBirthDate()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Register patient method
    public void registerPatient(String firstName, String lastName, Date birthDate, String contactInfo,
                                String insuranceInfo, String pharmacyInfo, String password) {
        Patient patient = new Patient(firstName, lastName, birthDate);
        patient.setContactInfo(contactInfo);
        patient.setInsuranceInfo(insuranceInfo);
        patient.setPharmacyInfo(pharmacyInfo);
        savePatient(patient, password);
    }

    // Register doctor method
    public void registerDoctor(String firstName, String lastName, String specialty, String licenseNumber, Date birthDate) {
        Doctor doctor = new Doctor(firstName, lastName, specialty, licenseNumber, birthDate);
        saveDoctor(doctor);
    }

    // Register nurse method
    public void registerNurse(String firstName, String lastName, String nurseId, String department, Date birthDate) {
        Nurse nurse = new Nurse(firstName, lastName, nurseId, department, birthDate);
        saveNurse(nurse);
    }

    // Load methods
    public Patient loadPatient(String accountId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    Date birthDate = parseDate(parts[2]);
                    String contactInfo = parts[3];
                    String insuranceInfo = parts[4];
                    String pharmacyInfo = parts[5];
                    String password = parts[6];
                    Patient patient = new Patient(firstName, lastName, birthDate);
                    patient.setContactInfo(contactInfo);
                    patient.setInsuranceInfo(insuranceInfo);
                    patient.setPharmacyInfo(pharmacyInfo);
                    String generatedAccountId = generateAccountId(firstName, lastName, birthDate);
                    if (generatedAccountId.equals(accountId)) {
                        return patient;
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null; // Return null if no patient with the specified accountId is found
    }

    public List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTORS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) { // Ensure correct number of fields
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String specialty = parts[2];
                    String licenseNumber = parts[3];
                    Date birthDate = parseDate(parts[4]);
                    Doctor doctor = new Doctor(firstName, lastName, specialty, licenseNumber, birthDate);
                    doctors.add(doctor);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public List<Nurse> loadNurses() {
        List<Nurse> nurses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) { // Ensure correct number of fields
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String nurseId = parts[2];
                    String department = parts[3];
                    Date birthDate = parseDate(parts[4]);
                    Nurse nurse = new Nurse(firstName, lastName, nurseId, department, birthDate);
                    nurses.add(nurse);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    // Retrieve patient credentials
    public Map<String, String> getPatientCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    Date birthDate = parseDate(parts[2]);
                    String username = generateAccountId(firstName, lastName, birthDate);
                    String password = parts[6];
                    credentials.put(username, password);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    // Helper methods
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(date);
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.parse(dateString);
    }
    
    private String generateAccountId(String firstName, String lastName, Date birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        String formattedDate = dateFormat.format(birthDate);
        return firstName.toLowerCase() + lastName.toLowerCase() + formattedDate;
    }
}
