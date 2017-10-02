import com.codecool.dream_is_green.dao.DatabaseConnection;
import com.codecool.dream_is_green.dao.LoginDAO;

public class App {

    public static void main(String[] args) {

        DatabaseConnection.getConnection();
        LoginDAO.createDefaultUsers();
        LoginDAO.loginIntoSystem();  // admin/admin, mentor/mentor, student/student
        DatabaseConnection.closeConnection();
    }

}
