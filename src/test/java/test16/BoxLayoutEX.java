package test16;

import javax.swing.*;
import java.awt.*;

public class BoxLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Tạo một JPanel với BoxLayout theo chiều dọc
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel));

        // Tạo các thành phần
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);

        // Thêm các thành phần vào mainPanel
        mainPanel.add(panel1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Vùng không gian cố định
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        // Ẩn panel1
        panel1.setVisible(false);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
