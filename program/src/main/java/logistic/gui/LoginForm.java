package logistic.gui;

/**
 * Created by bodrik on 24.11.16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Application mainClass;

    public LoginFormController(Stage primaryStage, Application mainClass) {
        this.primaryStage = primaryStage;
        this.mainClass = mainClass;
    }

    public void render() {
        this.primaryStage.setTitle("Форма входа");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.mainClass.getClass().getResource("views/LoginForm.fxml"));
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
