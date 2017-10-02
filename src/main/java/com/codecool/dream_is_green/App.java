import com.codecool.dream_is_green.dao.DatabaseConnection;
import com.codecool.dream_is_green.controller.LoginPanelController;

public class App {

    public static void main(String[] args) {

        DatabaseConnection.getConnection();
        LoginPanelController loginPanel = new LoginPanelController();
        loginPanel.loginIntoSystem();  // admin/admin, mentor/mentor, student/student
        DatabaseConnection.closeConnection();
    }

}
