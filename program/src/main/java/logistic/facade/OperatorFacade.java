package logistic.facade;

import javafx.application.Application;
import javafx.stage.Stage;
import logistic.gui.OperatorForm;
import logistic.mappers.CalendarMapper;
import logistic.models.Calendar;
import logistic.models.Order;
import logistic.models.User;
import logistic.repositories.OrdersRepository;
import logistic.repositories.UsersRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OperatorFacade {

    /**
     * Вызывает главное окно перевозчика
     */
    public void showMainWindow(Application mainClass, Stage mainStage) {
        OperatorForm form = new OperatorForm(mainStage, mainClass);
        form.render();
    }

    public static List<Order> getOrders() {
        return OrdersRepository.getInstance().getAll();
    }

    public static List<Calendar> getCalendar() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        List<Calendar> calendar = CalendarMapper.getAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        long startDate = System.currentTimeMillis();
        String d = dateFormat.format(new java.util.Date(startDate));
        return calendar.stream().sorted((l, p) -> l.getDate().compareTo(p.getDate())).filter(p -> p.getDate().compareTo(d) >= 0).collect(Collectors.toList());
    }

    public static List<User> getCarriers() {
        return UsersRepository.getInstance().getAll(User.TYPE_CARRIER);
    }
}
