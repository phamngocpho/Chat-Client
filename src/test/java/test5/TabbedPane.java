package test5;

import javax.swing.*;

public class TabbedPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabbed Pane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Content for Panel 1"));
        tabbedPane.addTab("Tab 1", panel1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Content for Panel 2"));
        tabbedPane.addTab("Tab 2", panel2);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Content for Panel 3"));
        tabbedPane.addTab("Tab 3", panel3);

        frame.add(tabbedPane);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

