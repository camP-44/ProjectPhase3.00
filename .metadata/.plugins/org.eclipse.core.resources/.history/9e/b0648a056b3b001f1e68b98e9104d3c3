package application.views;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import application.models.Patient;
import application.controllers.NurseController;

public class NurseView {
    private VBox view;
    private TextField patientIdField;
    private Button loadPatientButton;
    private Label patientInfoLabel;

    public NurseView() {
        view = new VBox();
        patientIdField = new TextField();
        loadPatientButton = new Button("Load Patient");
        patientInfoLabel = new Label();
        view.getChildren().addAll(new Label("Patient ID:"), patientIdField, loadPatientButton, patientInfoLabel);
        loadPatientButton.setOnAction(event -> {
            NurseController nurseController = new NurseController();
            Patient patient = nurseController.loadPatient(patientIdField.getText());
            patientInfoLabel.setText(patient != null ? patient.toString() : "Patient not found");
        });
    }

    public VBox getView() {
        return view;
    }
}
