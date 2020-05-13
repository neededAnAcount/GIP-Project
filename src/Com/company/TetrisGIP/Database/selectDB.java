package Com.company.TetrisGIP.Database;

import java.sql.*;

public class selectDB {

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


    /**
     * select all rows in the hard_scoreboard table
     */
    public static void selectAll() {
        String sql = "SELECT * FROM hard_scoreboard";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getDouble("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
