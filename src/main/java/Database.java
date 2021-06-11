
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static Connection connection;
    public static void connect() {
        try {
            insertStatements.checkPath();
            String url = "jdbc:ucanaccess://"+ insertStatements.path;
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to access has been established.");
            System.out.println();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            connection.close();

            System.out.println("Connection closed");
            System.out.println();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
