import java.io.IOException;
import java.lang.String;

import javafx.application.Application;
import javafx.stage.Stage;
import logistic.gui.LoginForm;
import logistic.services.API;

public class Main extends Application {
    protected Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        LoginForm loginForm = new LoginForm(primaryStage, this);
        loginForm.render();
    }

    public static void main(String[] args) throws IOException
    {
        API.start();
        launch(args);
    }
}