package test11;

import javax.swing.*;
import java.awt.*;

public class PinInput {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PIN Input Example");
        JPanel panel = new JPanel(new GridLayout(1, 4));

        JTextField[] pinFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            pinFields[i] = new JTextField(1); // Số ký tự cho mỗi ô
            panel.add(pinFields[i]);
        }

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

