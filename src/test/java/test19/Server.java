package test15;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    private static JLabel label;
    public Server() {
        setSize(600, 600);
        setLayout(new MigLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Server");
        setResizable(false);
        label = new JLabel();
        label.setBounds(0, 0, 320, 180);
        add(label);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Server();
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("wait for client connection");
            Socket socket = serverSocket.accept();
            System.out.println("Connection accepted");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ImageIcon ic;
            while (true) {
                try {
                    ic = (ImageIcon) ois.readObject();
                    label.setIcon(ic);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
