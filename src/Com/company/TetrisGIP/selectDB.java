package Com.company.TetrisGIP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class selectDB extends scoreboardMenu {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    public static Connection connect() {
        // SQLite connection string
        Connection conn = null;
        String url = "jdbc:sqlite:scoreboard.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
