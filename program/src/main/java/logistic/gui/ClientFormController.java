package logistic.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logistic.facade.ClientFacade;
import logistic.models.Client;
import logistic.models.User;
import logistic.repositories.UsersRepository;

import javax.swing.*;

public class ClientFormController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button closeBtn;
    @FXML
    private Button entryBtn;


    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public ClientFormController() {
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
        UsersRepository repo = UsersRepository.getInstance();
        User currentUser = repo.getByEmailAndPassword(
                this.getEmail(),
                this.getPassword()
        );
        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "Ошибка входа. Неверные email или пароль.");
        } else {
            primaryStage.hide();
            JOptionPane.showMessageDialog(null, "Здравствуйте, " + currentUser.getName() + "!");
            if (currentUser instanceof Client) {
                repo.setCurrentUserObject(currentUser);
                ClientFacade clientFacade = new ClientFacade(this.mainClass, this.primaryStage);
                clientFacade.showMainWindow();
            }

        };
    }

    public void exit() {
        primaryStage.close();
    }
}