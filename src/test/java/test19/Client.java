package test15;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamStreamer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends JFrame {
    private static JLabel label;
    public Client() {
        setSize(420, 280);
        setLayout(new MigLayout("fill"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.CYAN);
        label.setBounds(0, 0, 320, 180);
        add(label);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Client();
        try (Socket socket = new Socket("localhost", 9999)){
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ImageIcon imageIcon;
            BufferedImage bufferedImage;
            Webcam webcam = Webcam.getDefault();
            Dimension frameSize = new Dimension(960, 540);
            webcam.setCustomViewSizes(frameSize);
            webcam.setViewSize(frameSize);
            webcam.open();

            while (true) {
                bufferedImage = webcam.getImage();
                imageIcon = new ImageIcon(bufferedImage);
                outputStream.writeObject(imageIcon);
                outputStream.flush();
                label.setIcon(imageIcon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
