import java.io.IOException;
import java.lang.String;

import javafx.application.Application;
import javafx.stage.Stage;
import logistic.gui.LoginForm;
import logistic.models.Order;
import logistic.repositories.OrdersRepository;
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
        Order order = OrdersRepository.getInstance().getAll().stream().filter(p -> p.getId() == 1).findFirst().get();
        System.out.print(order.getDetails());
        //API.start();
        launch(args);
    }
}