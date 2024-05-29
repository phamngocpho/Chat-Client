package test20;

import jnafilechooser.api.JnaFileChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<MyFile> myFiles = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        int fileID = 0;
        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel title = new JLabel("Receiver");
        title.setFont(new Font("", Font.PLAIN, 20));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(title);
        frame.add(scrollPane);

        frame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());

                int fileNameLength = in.readInt();

                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];
                    in.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);

                    int fileContentLength = in.readInt();
                    if (fileContentLength > 0) {
                        byte[] fileContentBytes = new byte[fileContentLength];
                        in.readFully(fileContentBytes, 0, fileContentBytes.length);

                        JPanel panelFileRow = new JPanel();
                        panelFileRow.setLayout(new BoxLayout(panelFileRow, BoxLayout.Y_AXIS));

                        JLabel fileNameLabel = new JLabel(fileName);
                        fileNameLabel.setFont(new Font("", Font.PLAIN, 20));
                        fileNameLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

                        panelFileRow.setName(String.valueOf(fileID));
                        panelFileRow.addMouseListener(getMyMouseListener());
                        panelFileRow.add(fileNameLabel);
                        panel.add(panelFileRow);
                        frame.validate();

                        myFiles.add(new MyFile(fileID, fileName, fileContentBytes, getFileExtension(fileName)));
                        fileID++;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static MouseListener getMyMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                int fileID = Integer.parseInt(panel.getName());

                for (MyFile myfile : myFiles) {
                    if (myfile.getId() == fileID) {
                        JFrame fPreview = createFrame(myfile.getName(), myfile.getData(), myfile.getFileExtension());
                        fPreview.setVisible(true);
                    }
                }
            }
        };
    }

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
        JFrame frame = new JFrame("Download File");
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("", Font.PLAIN, 20));
        yesButton.setPreferredSize(new Dimension(150, 75));

        JButton noButton = new JButton("No");
        noButton.setFont(new Font("", Font.PLAIN, 20));
        noButton.setPreferredSize(new Dimension(150, 75));

        JLabel fileContentLabel = new JLabel("Do you want to download the file?");
        fileContentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelButton = new JPanel();
        panelButton.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelButton.add(yesButton);
        panelButton.add(noButton);

        yesButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a directory to save the file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File directoryToSave = fileChooser.getSelectedFile();
                File fileToSave = new File(directoryToSave, fileName);

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    out.write(fileData);
                    JOptionPane.showMessageDialog(null, "File has been saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, "Error saving the file: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            frame.dispose();
        });

        noButton.addActionListener(e -> frame.dispose());

        panel.add(fileContentLabel);
        panel.add(panelButton);
        frame.add(panel);
        return frame;
    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            return fileName.substring(i + 1);
        } else {
            return "No Extension";
        }
    }
}

