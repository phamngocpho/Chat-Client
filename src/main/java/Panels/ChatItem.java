package Client.Panels;

import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class ChatItem extends JPanel {
    private String time;

    private static ChatItem instance;

    public ChatItem () {};

    public static ChatItem getInstance () {
        if (instance == null) {
            instance = new ChatItem();
        }
        return instance;
    }

    public ChatItem (String time, String message) {
        this.time = time;
        initMessage(message);
        putClientProperty(FlatClientProperties.STYLE, "arc:50");
    }

    public ChatItem (String time, Image image) {
        this.time = time;
        initImage();
        putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    public ChatItem (String time, File file) {
        this.time = time;
        initFile();
        putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    private void initMessage(String message) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel messageLabel = new JLabel() {
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(0, -25);
            }
        };
        messageLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        messageLabel.setFont(new Font("", Font.PLAIN, 16));
        messageLabel.setHorizontalTextPosition(JLabel.CENTER);
        messageLabel.setToolTipText(time);
        messageLabel.setText("<html>" + message + "</html>");
        UIManager.put("ToolTip.background", Value.bolder_gray);
        add(messageLabel);
    }


    private void initImage () {}
    private void initFile () {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    private void initEmoji () {}
}
