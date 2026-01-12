import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import java.sql.Connection;

public class DBConnection {
    Dotenv dotenv = Dotenv.load();
    String jdbcURL
            = dotenv.get("DB_URL");
    String username = dotenv.get("DB_USERNAME");
    String password = dotenv.get("DB_PASSWORD");



    {
        try {
            Connection connection = connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connnexion establish successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}