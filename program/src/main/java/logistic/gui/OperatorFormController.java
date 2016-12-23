package logistic.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logistic.facade.OperatorFacade;
import logistic.facade.UserFacade;
import logistic.models.Calendar;
import logistic.models.Order;
import logistic.models.User;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OperatorFormController {
    @FXML
    private TextField email;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phone;
    @FXML
    private TextField costOrder;
    @FXML
    private TableView<Calendar> calendarTable;
    @FXML
    private TableColumn<Calendar, String> dateCalendar;
    @FXML
    private TableColumn<Calendar, String> detailsCalendar;
    @FXML
    private TableView<Order> listOrders;
    @FXML
    private TableColumn<Order, String> numberOrder;
    @FXML
    private TableColumn<Order, String> creationDate;
    @FXML
    private TableColumn<Order, String> statusOrder;
    @FXML
    private TableColumn<Order, String> detailsOrder;
    @FXML
    private ComboBox statusesList;
    @FXML
    private ComboBox carrierList;

    @FXML
    private Button saveProfileBtn;
    @FXML
    private Button saveCalendarBtn;
    @FXML
    private Button saveOrderBtn;


    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public OperatorFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        this.loadProfile();
        this.showListOrders();
        this.showCalendar();
        this.fillStatusesLists();
        this.fillCarriersLists();

        calendarTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectCalendar(newValue)
        );

        listOrders.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectOrder(newValue)
        );

        statusesList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectStatus(newValue)
        );

        carrierList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectCarrier((User) newValue)
        );

    }

    public void fillStatusesLists() {
        ObservableList<String> obStatuses = FXCollections.observableArrayList();
        Collections.addAll(obStatuses, Order.statusNames);
        this.statusesList.setItems(obStatuses);
    }

    public void fillCarriersLists() {
        ObservableList<User> obCities = FXCollections.observableArrayList();
        List<User> users = OperatorFacade.getCarriers();
        obCities.addAll(users);
        this.carrierList.setItems(obCities);
    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    // Обработчики событий
    public void saveProfile() {
        User currentUser = UserFacade.getLoggedUser();
        currentUser.setEmail(email.getText());
        currentUser.setPhone(phone.getText());
        currentUser.setName(fullName.getText());
        currentUser.save();
        JOptionPane.showMessageDialog(null, "Профиль сохранён");
    }

    public void loadProfile() {
        User currentUser = UserFacade.getLoggedUser();
        email.setText(currentUser.getEmail());
        fullName.setText(currentUser.getName());
        phone.setText(currentUser.getPhone());
    }

    public void showCalendar() {
        ObservableList<Calendar> obCalendar = FXCollections.observableArrayList();
        obCalendar.addAll(OperatorFacade.getCalendar());
        calendarTable.setItems(obCalendar);
        dateCalendar.setCellValueFactory(new PropertyValueFactory<Calendar, String>("date"));
        detailsCalendar.setCellValueFactory(new PropertyValueFactory<Calendar, String>("details"));
    }

    public void selectCalendar(Calendar calendar) {

    }

    public void showListOrders() {
        ObservableList<Order> obOrders = FXCollections.observableArrayList();
        obOrders.addAll(OperatorFacade.getOrders());
        listOrders.setItems(obOrders);
        numberOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Order, String>("dateCreate"));
        statusOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("statusName"));
        detailsOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("details"));
    }

    public void selectOrder(Order order) {
        statusesList.getSelectionModel().select(order.getStatus());
        carrierList.getSelectionModel().select(UserFacade.getById(order.getCarrierId()));
        if (order.getCost() == 0) {
            costOrder.setText("0.00");
        } else {
            costOrder.setText(String.valueOf(order.getCost()));
        }
        saveOrderBtn.setDisable(true);
    }

    public void saveOrder() {
        Order order = listOrders.getSelectionModel().getSelectedItem();
        User user = (User)Optional.ofNullable(carrierList.getSelectionModel().getSelectedItem()).orElse(new User());
        order.setStatus(statusesList.getSelectionModel().getSelectedIndex());
        order.setCost(Double.parseDouble(costOrder.getText()));
        order.setCarrierId(user.getId());
        order.save();
        listOrders.refresh();
        saveOrderBtn.setDisable(true);
    }

    public void selectStatus(Object status) {
        saveOrderBtn.setDisable(false);
    }

    public void selectCarrier(User user) {
        saveOrderBtn.setDisable(false);
    }

    public void changeCostOrder() {
        saveOrderBtn.setDisable(false);
    }


    public void terminate() {
        primaryStage.close();
    }

}