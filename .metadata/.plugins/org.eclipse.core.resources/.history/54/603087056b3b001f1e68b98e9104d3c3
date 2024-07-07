package application.controllers;

import application.models.Patient;
import application.utils.FileSystemHandler;

public class NurseController {
    private FileSystemHandler fileSystemHandler;

    public NurseController() {
        this.fileSystemHandler = FileSystemHandler.getInstance();
    }

    public Patient loadPatient(String accountId) {
        return fileSystemHandler.loadPatient(accountId);
    }

    public void savePatient(Patient patient, String password) {
        fileSystemHandler.savePatient(patient, password);
    }
}
