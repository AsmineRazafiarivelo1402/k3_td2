import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String am=  dotenv.get("DB_USERNAME");
        System.out.println(am);
    DBConnection dbConnection = new DBConnection();


    }
}
