package billing.managment.system;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Small database connection smoke test for the billing application.
 */
public class BillingManagmentSystem {
    public static void main(String[] args) throws Exception {
        String url = System.getenv().getOrDefault("BMS_DB_URL", "jdbc:mysql://localhost:6666/bms");
        String user = System.getenv().getOrDefault("BMS_DB_USER", "root");
        String password = System.getenv().getOrDefault("BMS_DB_PASSWORD", "");

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database connection successful: " + !connection.isClosed());
        }
    }
}
