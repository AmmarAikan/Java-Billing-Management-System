package Project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Provides database connections using environment-based configuration.
 */
public class ConnectionProvider {
    public static Connection getCon() {
        String url = System.getenv().getOrDefault("BMS_DB_URL", "jdbc:mysql://localhost:6666/bms");
        String user = System.getenv().getOrDefault("BMS_DB_USER", "root");
        String password = System.getenv().getOrDefault("BMS_DB_PASSWORD", "");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception exception) {
            return null;
        }
    }
}
