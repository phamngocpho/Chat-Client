package Client.Core;

import Client.Panels.ChatItem;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddItem {
    private final ScrollAnimation scrollAnimation;
    private final JScrollPane scrollPane;
    private final JPanel panel;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private static boolean isConnect = false;

    public AddItem (ScrollAnimation scrollAnimation, JScrollPane scrollPane, JPanel panel) {
        this.scrollAnimation = scrollAnimation;
        this.scrollPane = scrollPane;
        this.panel = panel;
        if (!isConnect) {
//            connectSocket();
            isConnect = true;
        }
    }

    public void addMessage(String message, boolean isSender) {
        if (isSender) {
            if (!message.trim().isEmpty()) {
                try {
                    outputStream.writeObject(message);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ChatItem item = new ChatItem("Apr 11, 9:03 PM", message);
        item.setBackground(isSender ? Value.message_right : Value.message_left);
        panel.add(item, "wrap, al " + (isSender ? "right" : "left") + " , w ::65%");
        panel.repaint();
        panel.revalidate();
        scrollToBottomWithAnimation();
    }

    public void deleteItem (ChatItem item) {
        item.removeAll();
        item.setLayout(new MigLayout("insets 12 10 12 10"));
        item.setBorder(new FlatRoundBorder());
        item.setBackground(null);
        item.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        JLabel label = new JLabel("You unsent a message");
        label.setForeground(Value.unsent_message);
        item.add(label);
        item.repaint();
        item.revalidate();
    }

    public void receiverMessage () {
        try {
            String message;
            while ((message = (String) inputStream.readObject()) != null) {
                addMessage(message, false);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addImage (Image image) {}

    public void addFile (File file) {}

    public void addEmoji (String emoji) {}


    public void scrollToBottomWithAnimation() {
        SwingUtilities.invokeLater(() -> scrollAnimation.scrollToMax(scrollPane.getVerticalScrollBar()));
    }
//    private void connectSocket () {
//        try {
//            Socket socket = new Socket(Value.SERVER_ADDRESS, Value.PORT);
//            outputStream = new ObjectOutputStream(socket.getOutputStream());
//            inputStream = new ObjectInputStream(socket.getInputStream());
//            Thread thread = new Thread(this::receiverMessage);
//            thread.start();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
