package application.controllers;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import application.views.NurseView;
import application.views.DoctorView;
import application.views.PatientView;
import application.utils.AccessControl;

public class LoginController {
    private AccessControl accessControl;

    public LoginController() {
        this.accessControl = AccessControl.getInstance();
    }

    public boolean authenticate(String accountId, String password) {
        return accessControl.authenticate(accountId, password) != null;
    }

    public String getRole(String accountId) {
        return accessControl.getRole(accountId);
    }

    public void handleLogin(String accountId, String password, Stage stage, Label messageLabel) {
        String role = accessControl.authenticate(accountId, password);

        if (role != null) {
            switch (role) {
                case "NURSE":
                    stage.setScene(new Scene(new NurseView().getView(), 600, 400));
                    break;
                case "DOCTOR":
                    stage.setScene(new Scene(new DoctorView().getView(), 600, 400));
                    break;
                case "PATIENT":
                    stage.setScene(new Scene(new PatientView().getView(), 600, 400));
                    break;
                default:
                    messageLabel.setText("Unknown role: " + role);
                    break;
            }
        } else {
            messageLabel.setText("Invalid credentials");
        }
    }
}
