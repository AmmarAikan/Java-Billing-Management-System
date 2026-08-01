package billing.managment.system;

import Project.ConnectionProvider;
import java.sql.Connection;

/**
 * Small database connection smoke test for the billing application.
 */
public class BillingManagmentSystem {
    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionProvider.getCon()) {
            System.out.println("Database connection successful: " + !connection.isClosed());
        }
    }
}
