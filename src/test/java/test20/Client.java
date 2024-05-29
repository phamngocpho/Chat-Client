package test20;


import jnafilechooser.api.JnaFileChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final File[] fileToSend = new File[1];
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel fileNameLabel = new JLabel("Choose file: ");
        fileNameLabel.setFont(new Font("", Font.PLAIN, 20));
        fileNameLabel.setBorder(new EmptyBorder(50, 0, 0, 0));
        fileNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelButton = new JPanel();
        panelButton.setBorder(new EmptyBorder(75, 0, 10, 0));

        JButton sendButton = new JButton("Send File");
        sendButton.setPreferredSize(new Dimension(150, 75));
        sendButton.setFont(new Font("", Font.PLAIN, 20));
        sendButton.addActionListener(e -> {
            if (fileToSend[0] == null) {
                fileNameLabel.setText("No file chosen.");
            } else {
                try (FileInputStream in = new FileInputStream(fileToSend[0].getAbsolutePath());
                     Socket socket = new Socket("localhost", 1234);
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    String fileName = fileToSend[0].getName();
                    byte[] fileBytes = fileName.getBytes();
                    byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                    in.read(fileContentBytes);

                    out.writeInt(fileBytes.length);
                    out.write(fileBytes);
                    out.writeInt(fileContentBytes.length);
                    out.write(fileContentBytes);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton chooseButton = new JButton("Choose File");
        chooseButton.setPreferredSize(new Dimension(150, 75));
        chooseButton.setFont(new Font("", Font.PLAIN, 20));
        chooseButton.addActionListener(e -> {
            JnaFileChooser fileChooser = new JnaFileChooser();
            if (fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(chooseButton))) {
                fileToSend[0] = fileChooser.getSelectedFile();
                fileNameLabel.setText("The file: " + fileToSend[0].getName());
            }
        });

        panelButton.add(sendButton);
        panelButton.add(chooseButton);

        frame.add(fileNameLabel);
        frame.add(panelButton);
        frame.setVisible(true);
    }
}

