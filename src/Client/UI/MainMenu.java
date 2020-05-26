package Client.UI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public  MainMenu() {

        setTitle("CodeGym Library");
        setLayout(new BorderLayout(5, 5));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        Image img = Toolkit.getDefaultToolkit().getImage("icon.png");
        setIconImage(img);

        //Create Left Panel
        JPanel leftPanel = new JPanel(new GridLayout(05,0, 0, 0));
        leftPanel.setPreferredSize(new Dimension(100, 700));
        leftPanel.setMaximumSize(leftPanel.getPreferredSize());

        JButton userButton = new JButton("User");
        userButton.setPreferredSize(new Dimension(100, 100));

        JButton javaScriptButton = new JButton("JavaScript");
        javaScriptButton.setPreferredSize(new Dimension(100, 100));

        JButton javaButton = new JButton("Java");
        javaButton.setPreferredSize(new Dimension(100, 100));


        JButton phpButton = new JButton("PHP");
        phpButton.setPreferredSize(new Dimension(100, 100));

        JButton softwareButton = new JButton("Software");
        softwareButton.setPreferredSize(new Dimension(100, 100));

        leftPanel.add(userButton);
        leftPanel.add(javaScriptButton);
        leftPanel.add(javaButton);
        leftPanel.add(phpButton);
        leftPanel.add(softwareButton);

        leftPanel.setBackground(new Color(111, 111, 111));

        //Create right panel using JTable
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        rightPanel.setPreferredSize(new Dimension(1100,  700));
        rightPanel.setMinimumSize(rightPanel.getPreferredSize());

        String[][] rec = {
                { "1", "Steve", "AUS" },
                { "2", "Virat", "IND" },
                { "3", "Kane", "NZ" },
                { "4", "David", "AUS" },
                { "5", "Ben", "ENG" },
                { "6", "Eion", "ENG" },
        };

        String[] header = { "Lesson", "Size", "Download" };
        JTable table = new JTable(rec, header);

        rightPanel.add(new JScrollPane(table),BorderLayout.CENTER);

        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainMenu myMain=new MainMenu();
        myMain.setVisible(true);
    }
}
