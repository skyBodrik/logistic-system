package logistic.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class LoginFormController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button closeBtn;
    @FXML
    private Button entryBtn;


    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
/*        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());*/
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        //personTable.setItems(mainApp.getPersonData());
    }

    public void login() {
        JOptionPane.showMessageDialog(null, 'тест');
    }
}