package application.utils;

import java.util.HashMap;
import java.util.Map;

public class AccessControl {
    private static AccessControl instance;
    private Map<String, String> userCredentials;
    private Map<String, String> userRoles;

    private AccessControl() {
        this.userCredentials = new HashMap<>();
        this.userRoles = new HashMap<>();
    }

    public static AccessControl getInstance() {
        if (instance == null) {
            instance = new AccessControl();
        }
        return instance;
    }

    // Add user credentials for Patient
    public void addPatient(String accountId, String password) {
        userCredentials.put(accountId, password);
        userRoles.put(accountId, "PATIENT");
    }

    // Add user credentials for Doctor
    public void addDoctor(String accountId, String password) {
        userCredentials.put(accountId, password);
        userRoles.put(accountId, "DOCTOR");
    }

    // Add user credentials for Nurse
    public void addNurse(String accountId, String password) {
        userCredentials.put(accountId, password);
        userRoles.put(accountId, "NURSE");
    }

    // Authenticate user based on accountId and password
    public String authenticate(String accountId, String password) {
        if (userCredentials.containsKey(accountId) && userCredentials.get(accountId).equals(password)) {
            return userRoles.get(accountId);
        }
        return null; // Authentication failed
    }

    // Check if accountId already exists
    public boolean accountIdExists(String accountId) {
        return userCredentials.containsKey(accountId);
    }

    // Retrieve role associated with accountId
    public String getRole(String accountId) {
        return userRoles.get(accountId);
    }
}
