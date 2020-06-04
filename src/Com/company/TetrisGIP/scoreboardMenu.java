package Com.company.TetrisGIP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Com.company.TetrisGIP.selectDB.connect;

public class scoreboardMenu extends JPanel {
    private JFrame frame;
    private JLabel info;
    private JButton scoreboardEasy;
    private JButton scoreboardHard;
    private JButton cancelButton;



    /**
     * this will load the menu for the player to chose which database to load and display
     */

    public scoreboardMenu() {
        //load the frame for the menu to show up on and set the settings for the frame
        frame = new JFrame("Scoreboardmenu");
        frame.setLayout(new GridBagLayout()); // sets the layout for the frame
        frame.setSize(Window.WIDTH, Window.HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //set constraints for the layout to make buttons display nicely
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; //sets the with of the layout grid
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);//set the space between components


        info = new JLabel("Select the gamemode of which you want to display the scoreboard"); // displays info to player
        frame.add(info, gbc);
        scoreboardEasy = new JButton("easy mode");
        scoreboardEasy.setSize(100, 100);//set the size of the component
        scoreboardEasy.setAlignmentX(Component.CENTER_ALIGNMENT); //aligns the component to the center of the frame
        frame.add(scoreboardEasy, gbc);
        scoreboardEasy.addActionListener(e -> {
            if (scoreboardEasy.isEnabled()) {
                //cleans the jframe of all its components
                //revalidate the jframe
                //then starts the process of displaying the scoreboard
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();

                DefaultTableModel model = new DefaultTableModel(new String[]{"Username", "Score"}, 0);
                String sql = "SELECT * FROM easy_scoreboard order by score desc ";


                try (Connection conn = connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    // loop through the result set
                    while (rs.next()) {
                        String usernameEasy = rs.getString("username");
                        String scoreEasy = rs.getString("score");
                        model.addRow(new Object[]{usernameEasy, scoreEasy});
                        System.out.println("this works");
                    }
                } catch (SQLException a) {
                    System.out.println(a.getMessage());
                }

                JTable TableEasy = new JTable();
                JScrollPane spTable = new JScrollPane(TableEasy);
                TableEasy.setModel(model);
                frame.add(spTable);
                frame.revalidate();
                frame.repaint();

            }
        });


        // https://www.google.com/search?client=avast&sxsrf=ALeKk00moeptvASkPu332jqUmv1hEijx3g%3A1591254167359&ei=l5zYXt7DFZHhkgW_wY_4DQ&q=jtable+show+data+from+database+sqlite&oq=jtable+show+data+from+database+sql&gs_lcp=CgZwc3ktYWIQAxgAMggIIRAWEB0QHjoECAAQRzoGCAAQFhAeOgcIIRAKEKABUM4LWIseYJgjaAFwAXgAgAGcAYgBqAWSAQMwLjWYAQCgAQGqAQdnd3Mtd2l6&sclient=psy-ab

        scoreboardHard = new JButton("hard mode");
        scoreboardHard.setSize(100, 100);//set the size of the component
        scoreboardHard.setAlignmentX(Component.CENTER_ALIGNMENT);//aligns the component to the center of the frame
        frame.add(scoreboardHard, gbc);
        scoreboardHard.addActionListener(e -> {
            if (scoreboardHard.isEnabled()) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();

                DefaultTableModel model = new DefaultTableModel(new String[]{"Username", "Score"}, 0);
                String sql = "SELECT * FROM hard_scoreboard order by score desc ";


                try (Connection conn = connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    // loop through the result set
                    while (rs.next()) {
                        String usernameHard = rs.getString("username");
                        String scoreHard = rs.getString("score");
                        model.addRow(new Object[]{usernameHard, scoreHard});
                    }
                } catch (SQLException a) {
                    System.out.println(a.getMessage());
                }

                JTable TableHard = new JTable();
                JScrollPane spTable = new JScrollPane(TableHard);
                TableHard.setModel(model);
                frame.add(spTable);
                frame.revalidate();
                frame.repaint();
            }
        });

        cancelButton = new JButton("main menu");
        cancelButton.setSize(100, 100);//set the size of the component


        frame.add(cancelButton, gbc);
        cancelButton.addActionListener(e -> {
            if (cancelButton.isEnabled()) {
                //closes the jframe and displays the main menu
                frame.dispose();
            }
        });

    }


}
