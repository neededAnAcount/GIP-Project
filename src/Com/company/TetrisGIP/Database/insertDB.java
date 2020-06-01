package Com.company.TetrisGIP.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertDB {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:scoreboard.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * used to insert the username of the player
     * and to insert the score the player got
     * into the easy_scoreboard tabe of the database
     *
     * @param username
     * @param score
     */
    public void insert(String username, double score) {
        String sql = "INSERT INTO easy_scoreboard(username,score) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setDouble(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * used to insert the username of the player
     * and to insert the score the player got
     * into the Hard_scoreboard tabe of the database
     *
     * @param username
     * @param score
     */
    public void insertHard(String username, double score) {
        String sql = "INSERT INTO hard_scoreboard(username,score) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setDouble(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
