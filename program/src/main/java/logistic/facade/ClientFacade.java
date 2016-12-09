package logistic.facade;

import javafx.application.Application;
import javafx.stage.Stage;
import logistic.gui.ClientForm;
import logistic.models.City;
import logistic.models.Order;
import logistic.models.User;
import logistic.repositories.OrdersRepository;
import logistic.repositories.UsersRepository;
import logistic.services.Acquiring;


import java.util.List;

public class ClientFacade {


    /**
     * Вызывает главное окно клиента
     */
    public void showMainWindow(Application mainClass, Stage mainStage) {
        ClientForm clientForm = new ClientForm(mainStage, mainClass);
        clientForm.render();
    }

    public static void createOrder(
            double weight,
            double width,
            double height,
            double length,
            City fromCity,
            String fromAddress,
            City toCity,
            String toAddress,
            String recipientName,
            String recipientPhone
    ) {
        User client = UsersRepository.getInstance().getCurrentUserObject();
        Order order = new Order(weight, width, height, length, fromCity.getId(), fromAddress, toCity.getId(), toAddress, recipientName, recipientPhone, client.getId());
    }

    public static List<Order> getMyOrder() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        return OrdersRepository.getInstance().getAllByClient(currentUser);
    }

    public static boolean toPay(Order order) {
        Acquiring ac = new Acquiring();
        if (ac.toPay()) {
            order.setStatus(Order.STATUS_PAID_WAITING_TO_DISPATCH);
            order.save();
            return true;
        }
        return false;
    }
}
