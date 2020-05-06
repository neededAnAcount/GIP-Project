package Com.company.TetrisGIP.Database;

import java.sql.*;

public class selectDB {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite::Database:" +
                    getClass().getResource("/scoreboard.db"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public void selectAll() {
        String sql = "SELECT ID,username, score FROM easy_scoreboard";

        try (Connection conn = this.connect();
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
