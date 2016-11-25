package logistic.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logistic.facade.ClientFacade;
import logistic.models.City;
import logistic.models.Order;
import logistic.models.User;
import logistic.repositories.CitiesRepository;
import logistic.repositories.UsersRepository;

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
    private TextField lengthNewOrder;
    @FXML
    private TextField widthNewOrder;
    @FXML
    private TextField heightNewOrder;
    @FXML
    private TextField weightNewOrder;
    @FXML
    private ComboBox cityNewOrder1;
    @FXML
    private ComboBox cityNewOrder2;
    @FXML
    private TextField addressNewOrder1;
    @FXML
    private TextField addressNewOrder2;
    @FXML
    private TextField recipientNameNewOrder;
    @FXML
    private TextField recipientPhoneNewOrder;
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
    private Button newOrderBtn;


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
    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    public void fillCitiesLists() {
        ObservableList<City> obCities = FXCollections.observableArrayList();
        List<City> cities = CitiesRepository.getInstance().getAll();
        obCities.addAll(cities);
        this.cityNewOrder1.setItems(obCities);
        this.cityNewOrder2.setItems(obCities);
    }

    // Обработчики событий
    public void saveProfile() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        currentUser.setEmail(email.getText());
        currentUser.setPhone(phone.getText());
        currentUser.setName(fullName.getText());
        currentUser.save();
        JOptionPane.showMessageDialog(null, "Данные сохранены");
    }

    public void loadProfile() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        email.setText(currentUser.getEmail());
        fullName.setText(currentUser.getName());
        phone.setText(currentUser.getPhone());
    }

    public void createOrder() {
        ClientFacade.createOrder(
                Double.parseDouble(weightNewOrder.getText()),
                Double.parseDouble(widthNewOrder.getText()),
                Double.parseDouble(heightNewOrder.getText()),
                Double.parseDouble(lengthNewOrder.getText()),
                (City) cityNewOrder1.getValue(),
                addressNewOrder1.getText(),
                (City) cityNewOrder2.getValue(),
                addressNewOrder2.getText(),
                recipientNameNewOrder.getText(),
                recipientPhoneNewOrder.getText()
        );
        JOptionPane.showMessageDialog(null, "Создан новый заказ");
    }

    public void showListOrders() {
        ObservableList<Order> obOrders = FXCollections.observableArrayList();
        obOrders.addAll(ClientFacade.getMyOrder());
        listOrders.setItems(obOrders);
        numberOrder.setCellValueFactory(new PropertyValueFactory<Order,String>("id"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Order, String>("dateCreate"));
        statusOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("statusName"));
        detailsOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("details"));
    }

    public void terminate() {
        primaryStage.close();
    }

}