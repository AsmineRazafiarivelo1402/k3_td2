import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import java.sql.Connection;

public class DBConnection {
    Dotenv dotenv = Dotenv.load();
    String jdbcURL
            = dotenv.get("DB_URL");
    String username = dotenv.get("DB_USERNAME");
    String password = dotenv.get("DB_PASSWORD");

    public DBConnection(Dotenv dotenv, String jdbcURL, String password, String username) {
        this.dotenv = dotenv;
        this.jdbcURL = jdbcURL;
        this.password = password;
        this.username = username;
    }

    public DBConnection() {
    }

    public Dotenv getDotenv() {
        return dotenv;
    }

    public void setDotenv(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    {
        try {
            Connection connection = connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connnexion establish successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}