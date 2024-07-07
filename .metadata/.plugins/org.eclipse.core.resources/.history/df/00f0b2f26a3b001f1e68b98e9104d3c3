package application;

import application.views.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Office Automation System");
        MainMenuView mainMenuView = new MainMenuView(primaryStage);
        primaryStage.setScene(new Scene(mainMenuView.getView(), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
