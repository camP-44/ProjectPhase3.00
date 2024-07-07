package application.controllers;

import application.models.Patient;
import application.utils.FileSystemHandler;

public class DoctorController {
    private FileSystemHandler fileSystemHandler;

    public DoctorController() {
        this.fileSystemHandler = FileSystemHandler.getInstance();
    }

    public Patient loadPatient(String accountId) {
        return fileSystemHandler.loadPatient(accountId);
    }

    public void savePatient(Patient patient, String password) {
        fileSystemHandler.savePatient(patient, password);
    }
}
