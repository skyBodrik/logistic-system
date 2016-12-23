package logistic.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logistic.facade.CarrierFacade;
import logistic.facade.CityFacade;
import logistic.facade.UserFacade;
import logistic.models.Calendar;
import logistic.models.City;
import logistic.models.Order;
import logistic.models.User;

import javax.swing.*;
import java.util.List;

public class CarrierFormController {
    @FXML
    private TextField email;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phone;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private TextField length;
    @FXML
    private TextField maxweight;
    @FXML
    private TextField lengthNewOrder;
    @FXML
    private TextField widthNewOrder;
    @FXML
    private TextField heightNewOrder;
    @FXML
    private TextField weightNewOrder;
    @FXML
    private ComboBox cityListCalendar;
    @FXML
    private TextField addressNewOrder1;
    @FXML
    private TextField addressNewOrder2;
    @FXML
    private TextField recipientNameNewOrder;
    @FXML
    private TextField recipientPhoneNewOrder;
    @FXML
    private TableView<Calendar> calendarTable;
    @FXML
    private TableColumn<Calendar, String> dateCalendar;
    @FXML
    private TableColumn<Calendar, String> cityCalendar;
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
    private Button saveProfileBtn;
    @FXML
    private Button saveCalendarBtn;
    @FXML
    private Button changeStateToDeliveredBtn;


    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public CarrierFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        this.loadProfile();
        this.fillCitiesLists();
        this.showListOrders();
        this.showCalendar();

        calendarTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectCalendar(newValue)
        );

        listOrders.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectOrder(newValue)
        );

    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    public void fillCitiesLists() {
        ObservableList<City> obCities = FXCollections.observableArrayList();
        List<City> cities = CityFacade.getCities();
        obCities.add(0, new City());
        obCities.addAll(cities);
        this.cityListCalendar.setItems(obCities);
    }

    // Обработчики событий
    public void saveProfile() {
        User currentUser = UserFacade.getLoggedUser();
        currentUser.setEmail(email.getText());
        currentUser.setPhone(phone.getText());
        currentUser.setName(fullName.getText());
        currentUser.setMaxWeight(Double.parseDouble(maxweight.getText()));
        currentUser.setWidth(Double.parseDouble(width.getText()));
        currentUser.setHeight(Double.parseDouble(height.getText()));
        currentUser.setLength(Double.parseDouble(length.getText()));
        currentUser.save();
        JOptionPane.showMessageDialog(null, "Профиль сохранён");
    }

    public void loadProfile() {
        User currentUser = UserFacade.getLoggedUser();
        email.setText(currentUser.getEmail());
        fullName.setText(currentUser.getName());
        phone.setText(currentUser.getPhone());
        maxweight.setText(String.valueOf(currentUser.getMaxWeight()));
        width.setText(String.valueOf(currentUser.getWidth()));
        height.setText(String.valueOf(currentUser.getHeight()));
        length.setText(String.valueOf(currentUser.getLength()));
    }

    public void showCalendar() {
        ObservableList<Calendar> obCalendar = FXCollections.observableArrayList();
        obCalendar.addAll(CarrierFacade.getMyCalendar());
        calendarTable.setItems(obCalendar);
        dateCalendar.setCellValueFactory(new PropertyValueFactory<Calendar, String>("date"));
        cityCalendar.setCellValueFactory(new PropertyValueFactory<Calendar, String>("cityName"));
    }

    public void selectCalendar(Calendar calendar) {
        if (calendar.getCityId() == 0) {
            cityListCalendar.getSelectionModel().select(0);
        } else {
            City city = CityFacade.getCityById(calendar.getCityId());
            cityListCalendar.getSelectionModel().select(city);
        }
    }

    public void saveCalendar() {
        Calendar calendar = calendarTable.getSelectionModel().getSelectedItem();
        City city = (City)cityListCalendar.getSelectionModel().getSelectedItem();
        calendar.setCityId(city.getId());
        calendar.save();
        calendarTable.refresh();
    }

    public void showListOrders() {
        ObservableList<Order> obOrders = FXCollections.observableArrayList();
        obOrders.addAll(CarrierFacade.getMyOrder());
        listOrders.setItems(obOrders);
        numberOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Order, String>("dateCreate"));
        statusOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("statusName"));
        detailsOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("details"));
    }

    public void selectOrder(Order order) {
        if (order.getStatus() == Order.STATUS_IN_TRANSIT) {
            changeStateToDeliveredBtn.setDisable(false);
        } else {
            changeStateToDeliveredBtn.setDisable(true);
        }
    }

    public void changeStateToDelivered() {
        Order order = listOrders.getSelectionModel().getSelectedItem();
        order.setStatus(Order.STATUS_DELIVERED);
        order.save();
        listOrders.refresh();
        changeStateToDeliveredBtn.setDisable(true);
    }

    public void terminate() {
        primaryStage.close();
    }

}