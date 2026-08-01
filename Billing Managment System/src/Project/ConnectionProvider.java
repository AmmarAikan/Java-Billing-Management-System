package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

/**
 * Provides database connections using environment-based configuration.
 */
public class ConnectionProvider {
    private static String sessionPassword;

    public static Connection getCon() {
        String url = getConfiguration("BMS_DB_URL", "jdbc:mysql://localhost:6666/bms");
        String user = getConfiguration("BMS_DB_USER", "root");
        String configuredPassword = getConfiguration("BMS_DB_PASSWORD", null);
        String password = configuredPassword != null
                ? configuredPassword
                : requestSessionPassword();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception exception) {
            if (configuredPassword == null) {
                sessionPassword = null;
            }

            String detail = exception.getMessage() == null
                    ? exception.getClass().getSimpleName()
                    : exception.getMessage();
            throw new IllegalStateException(
                    "Database connection failed. Check MySQL, port, username, and password. Cause: "
                    + detail,
                    exception);
        }
    }

    private static String getConfiguration(String name, String defaultValue) {
        String value = System.getProperty(name);
        if (value == null || value.trim().isEmpty()) {
            value = System.getenv(name);
        }

        return value == null || value.trim().isEmpty()
                ? defaultValue
                : value;
    }

    private static synchronized String requestSessionPassword() {
        if (sessionPassword != null) {
            return sessionPassword;
        }

        JPasswordField passwordField = new JPasswordField(20);
        int selection = JOptionPane.showConfirmDialog(
                null,
                passwordField,
                "Enter MySQL password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        char[] passwordCharacters = passwordField.getPassword();
        try {
            if (selection != JOptionPane.OK_OPTION || passwordCharacters.length == 0) {
                throw new IllegalStateException(
                        "MySQL password is required. Set BMS_DB_PASSWORD or enter it when prompted.");
            }

            sessionPassword = new String(passwordCharacters);
            return sessionPassword;
        } finally {
            Arrays.fill(passwordCharacters, '\0');
        }
    }
}
