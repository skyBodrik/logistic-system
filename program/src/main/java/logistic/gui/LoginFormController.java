package logistic.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logistic.facade.CarrierFacade;
import logistic.facade.ClientFacade;
import logistic.facade.OperatorFacade;
import logistic.facade.UserFacade;
import logistic.models.Carrier;
import logistic.models.Client;
import logistic.models.Operator;
import logistic.models.User;

import javax.swing.*;

public class LoginFormController {
    @FXML
    private TabPane mainPaneTabs;
    @FXML
    private Tab tabLogin;
    @FXML
    private Tab tabReg;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button closeBtn;
    @FXML
    private Button entryBtn;

    // New user
    @FXML
    private RadioButton clientRole;
    @FXML
    private RadioButton carrierRole;
    @FXML
    private RadioButton operatorRole;
    @FXML
    private TextField nameNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField phoneNew;
    @FXML
    private PasswordField passwordNew;
    @FXML
    private PasswordField repeatPasswordNew;
    @FXML
    private Button createUserBtn;



    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public LoginFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    // Геттеры и сеттеры

    public String getEmail() {
        return email.getText();
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    // Обработчики событий

    public void login() {
        User currentUser = UserFacade.login(
                this.getEmail(),
                this.getPassword()
        );
        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "Ошибка входа. Неверные email или пароль.");
        } else {
            primaryStage.hide();
            JOptionPane.showMessageDialog(null, "Здравствуйте, " + currentUser.getName() + "!");
            UserFacade.setLoggedUser(currentUser);
            if (currentUser instanceof Client) {
                ClientFacade clientFacade = new ClientFacade();
                clientFacade.showMainWindow(this.mainClass, this.primaryStage);
            } else if (currentUser instanceof Carrier) {
                CarrierFacade carrierFacade = new CarrierFacade();
                carrierFacade.showMainWindow(this.mainClass, this.primaryStage);
            } else if (currentUser instanceof Operator) {
                OperatorFacade operatorFacade = new OperatorFacade();
                operatorFacade.showMainWindow(this.mainClass, this.primaryStage);
            }
        };
    }

    public void exit() {
        primaryStage.close();
    }

    public void createNewUser() {
        if (!passwordNew.getText().equals(repeatPasswordNew.getText())) {
            JOptionPane.showMessageDialog(null, "Пароли не совпадают!");
            return;
        }
        if (nameNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Неверно введенёно имя");
            return;
        }
        if (emailNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Неверно введенён email");
            return;
        }
        if (phoneNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Не введён номер телефона");
            return;
        }
        if (passwordNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Не введён пароль");
            return;
        }
        if (this.clientRole.selectedProperty().getValue()) {   // Создаём клиента
            UserFacade.createClient(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        } else if (this.carrierRole.selectedProperty().getValue()) {    // Создаём перевозчика
            UserFacade.createCarrier(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        } else if (this.operatorRole.selectedProperty().getValue()) {    // Создаём оператора
            UserFacade.createOperator(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        }
        JOptionPane.showMessageDialog(null, "Пользователь успешно создан. Войдите в систему.");
        this.mainPaneTabs.getSelectionModel().select(0);
        this.tabReg.setDisable(true);
    }
}