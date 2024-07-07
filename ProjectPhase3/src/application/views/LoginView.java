package application.views;

import application.controllers.LoginController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoginView {
    private VBox view;
    private TextField accountIdField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button signUpButton;
    private Label messageLabel;

    private LoginController loginController;
    private String role;

    public LoginView(Stage stage, String role) {
        this.role = role;
        view = new VBox();
        loginController = new LoginController();

        accountIdField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        signUpButton = new Button("Sign Up");
        messageLabel = new Label();

        view.setSpacing(10);
        view.setPadding(new Insets(20));

        view.getChildren().addAll(
                new Label("Username:"),
                accountIdField,
                new Label("Password:"),
                passwordField,
                loginButton,
                signUpButton,
                messageLabel
        );

        loginButton.setOnAction(event -> {
            String accountId = accountIdField.getText().trim();
            String password = passwordField.getText().trim();

            boolean loggedIn = loginController.authenticate(accountId, password);

            if (loggedIn) {
                messageLabel.setText("Login successful!");

                switch (role) {
                    case "DOCTOR":
                        stage.setScene(new Scene(new DoctorView().getView(), 800, 600));
                        break;
                    case "NURSE":
                        stage.setScene(new Scene(new NurseView().getView(), 800, 600));
                        break;
                    case "PATIENT":
                        stage.setScene(new Scene(new PatientView().getView(), 800, 600));
                        break;
                    default:
                        messageLabel.setText("Unknown role: " + role);
                        break;
                }

                clearFields();
            } else {
                messageLabel.setText("Invalid credentials. Please try again.");
            }
        });

        signUpButton.setOnAction(event -> {
            PatientRegistrationView patientRegistrationView = new PatientRegistrationView(stage, stage.getScene());
            stage.setScene(new Scene(patientRegistrationView.getView(), 800, 600));
        });
    }

    private void clearFields() {
        accountIdField.clear();
        passwordField.clear();
    }

    public VBox getView() {
        return view;
    }
}
