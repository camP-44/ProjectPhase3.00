package application.views;

import application.utils.FileSystemHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Date;

public class PatientRegistrationView {
    private VBox view;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField birthDateField;
    private TextField contactInfoField;
    private TextField insuranceInfoField;
    private TextField pharmacyInfoField;
    private PasswordField passwordField;
    private Button registerButton;
    private Button backButton;
    private Label messageLabel;

    private Stage stage;
    private Scene previousScene;

    public PatientRegistrationView(Stage stage, Scene previousScene) {
        this.stage = stage;
        this.previousScene = previousScene;

        view = new VBox();

        firstNameField = new TextField();
        lastNameField = new TextField();
        birthDateField = new TextField();
        contactInfoField = new TextField();
        insuranceInfoField = new TextField();
        pharmacyInfoField = new TextField();
        passwordField = new PasswordField();
        registerButton = new Button("Register");
        backButton = new Button("Back");
        messageLabel = new Label();

        view.getChildren().addAll(
                new Label("First Name:"),
                firstNameField,
                new Label("Last Name:"),
                lastNameField,
                new Label("Birth Date (MM-dd-yyyy):"),
                birthDateField,
                new Label("Contact Info:"),
                contactInfoField,
                new Label("Insurance Info:"),
                insuranceInfoField,
                new Label("Pharmacy Info:"),
                pharmacyInfoField,
                new Label("Choose Password:"),
                passwordField,
                registerButton,
                backButton,
                messageLabel
        );

        registerButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String birthDateString = birthDateField.getText().trim();
            Date birthDate = parseDate(birthDateString);
            String contactInfo = contactInfoField.getText().trim();
            String insuranceInfo = insuranceInfoField.getText().trim();
            String pharmacyInfo = pharmacyInfoField.getText().trim();
            String password = passwordField.getText();

            if (birthDate == null) {
                messageLabel.setText("Invalid birth date format. Use MM-dd-yyyy.");
                return;
            }

            String username = generateUsername(firstName, lastName, birthDate);

            boolean saved = savePatientInfo(username, password, firstName, lastName, birthDate, contactInfo, insuranceInfo, pharmacyInfo);

            if (saved) {
                messageLabel.setText("Registration successful! Username: " + username);
                clearFields();
            } else {
                messageLabel.setText("Failed to register. Please try again.");
            }
        });

        backButton.setOnAction(event -> stage.setScene(previousScene));
    }

    private boolean savePatientInfo(String username, String password, String firstName, String lastName, Date birthDate, String contactInfo, String insuranceInfo, String pharmacyInfo) {
        try {
            FileSystemHandler.getInstance().registerPatient(firstName, lastName, birthDate, contactInfo,
                    insuranceInfo, pharmacyInfo, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Date parseDate(String dateString) {
        try {
            String[] parts = dateString.split("-");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Date(year - 1900, month - 1, day);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateUsername(String firstName, String lastName, Date birthDate) {
        return firstName.toLowerCase() + lastName.toLowerCase() + birthDate.getTime();
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        birthDateField.clear();
        contactInfoField.clear();
        insuranceInfoField.clear();
        pharmacyInfoField.clear();
        passwordField.clear();
    }

    public VBox getView() {
        return view;
    }
}
