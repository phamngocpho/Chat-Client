import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DualPanelClickEffectExample {
    private static JPanel panel1;
    private static JPanel panel2;
    private static boolean panel1Clicked = false;
    private static boolean panel2Clicked = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Dual Panel Click Effect Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Tạo panel 1
                panel1 = new JPanel();
                panel1.setPreferredSize(new Dimension(200, 100));
                panel1.setBackground(Color.GREEN);
                panel1.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (!panel1Clicked) {
                            panel1.setBackground(Color.CYAN);
                        }
                    }

                    public void mouseExited(MouseEvent e) {
                        if (!panel1Clicked) {
                            panel1.setBackground(Color.WHITE);
                        }
                    }

                    public void mousePressed(MouseEvent e) {
                        panel1Clicked = !panel1Clicked;
                        if (panel1Clicked) {
                            panel1.setBackground(Color.RED);
                        } else {
                            panel1.setBackground(Color.CYAN);
                        }
                    }
                });

                // Tạo panel 2
                panel2 = new JPanel();
                panel2.setPreferredSize(new Dimension(200, 100));
                panel2.setBackground(Color.GREEN);
                panel2.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (!panel2Clicked) {
                            panel2.setBackground(Color.CYAN);
                        }
                    }

                    public void mouseExited(MouseEvent e) {
                        if (!panel2Clicked) {
                            panel2.setBackground(Color.WHITE);
                        }
                    }

                    public void mousePressed(MouseEvent e) {
                        panel2Clicked = !panel2Clicked;
                        if (panel2Clicked) {
                            panel2.setBackground(Color.RED);
                        } else {
                            panel2.setBackground(Color.CYAN);
                        }
                    }
                });

                // Thêm panel vào frame
                frame.getContentPane().setLayout(new GridLayout(1, 2));
                frame.getContentPane().add(panel1);
                frame.getContentPane().add(panel2);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
