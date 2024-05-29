package d94j;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {
    private JLabel l1, l2, l3 , result;
    private JTextField t1, t2;
    private JButton b1;
    private static DataInputStream in;
    private static DataOutputStream out;
    private double a,b;
    private static Socket s;

    public Client() {
        component();
    }

    public void component() {
        l1 = new JLabel("Nhập số thứ nhất:");
        l1.setBounds(0, 20, 200, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 18));

        l2 = new JLabel("Nhập số thứ hai:");
        l2.setBounds(0, 60, 200, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 18));

        l3 = new JLabel("Kết quả:");
        l3.setBounds(0, 100, 200, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 18));

        t1 = new JTextField();
        t1.setBounds(220, 20, 150, 30);

        t2 = new JTextField();
        t2.setBounds(220, 60, 150, 30);

        result = new JLabel("...");
        result.setBounds(220, 100, 150, 30);

        b1 = new JButton("OK");
        b1.setBounds(20, 180, 65, 30);
        b1.addActionListener((e) ->{
            try{
                a = Double.parseDouble(t1.getText());
                b = Double.parseDouble(t2.getText());
                out.writeDouble(a);
                out.writeDouble(b);
                double kq = in.readDouble();
                result.setText(String.valueOf(kq));
                s.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



        add(l1);
        add(l2);
        add(l3);
        add(t1);
        add(t2);
        add(result);
        add(b1);

        setTitle("Socket");
        setSize(420, 260);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }



    public static void main(String[] args) throws IOException {
        s = new Socket("localhost", 1234);
        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
        Client client = new Client();


    }
}