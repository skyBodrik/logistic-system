package logistic.facade;

import javafx.application.Application;
import javafx.stage.Stage;
import logistic.gui.CarrierForm;
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
        CarrierForm form = new CarrierForm(mainStage, mainClass);
        form.render();
    }

    public static List<Order> getMyOrder() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        return OrdersRepository.getInstance().getAllByCarrier(currentUser);
    }

    public static List<Calendar> getMyCalendar() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        List<Calendar> calendar = CalendarMapper.getAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        long startDate = System.currentTimeMillis();
        for (int i=0; i<30; i++) {
            String d = dateFormat.format(new java.util.Date(startDate));
            if (!calendar.stream().anyMatch(p -> p.getDate().equals(d))) {
                calendar.add(new Calendar(d, currentUser.getId(), 0));
            }
            startDate += 86400000;
        }
        return calendar.stream().sorted((l, p) -> l.getDate().compareTo(p.getDate())).collect(Collectors.toList());
    }
}
