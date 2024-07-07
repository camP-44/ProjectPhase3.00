package application.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView {
    private VBox view;
    private Button patientButton;
    private Button nurseButton;
    private Button doctorButton;

    private Scene patientLoginScene;
    private Scene nurseLoginScene;
    private Scene doctorLoginScene;

    public MainMenuView(Stage stage) {
        view = new VBox();
        patientButton = new Button("Patient");
        nurseButton = new Button("Nurse");
        doctorButton = new Button("Doctor");

        view.setSpacing(10);
        view.setPadding(new Insets(20));
        view.getChildren().addAll(patientButton, nurseButton, doctorButton);

        patientButton.setOnAction(event -> {
            if (patientLoginScene == null) {
                LoginView patientLoginView = new LoginView(stage, "PATIENT");
                patientLoginScene = new Scene(patientLoginView.getView(), 800, 600);
            }
            stage.setScene(patientLoginScene);
        });

        nurseButton.setOnAction(event -> {
            if (nurseLoginScene == null) {
                LoginView nurseLoginView = new LoginView(stage, "NURSE");
                nurseLoginScene = new Scene(nurseLoginView.getView(), 800, 600);
            }
            stage.setScene(nurseLoginScene);
        });

        doctorButton.setOnAction(event -> {
            if (doctorLoginScene == null) {
                LoginView doctorLoginView = new LoginView(stage, "DOCTOR");
                doctorLoginScene = new Scene(doctorLoginView.getView(), 800, 600);
            }
            stage.setScene(doctorLoginScene);
        });
    }

    public VBox getView() {
        return view;
    }
}
