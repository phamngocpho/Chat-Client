package Client.Panels;

import Client.Core.AddItem;
import Client.Core.ScrollAnimation;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import jnafilechooser.api.JnaFileChooser;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatWindowPanel extends JPanel {

    public ChatWindowPanel() {
        init();
    }
    private void init () {
        setLayout(new MigLayout("gap 0, insets 0", "[fill, 100%][grow]", "0[fill]0[fill, 100%]0[fill]0"));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Value.bolder_gray));
        JPanel subPanel1 = new JPanel(new MigLayout("gap 0, insets 15"));
        JPanel subPanel2 = new JPanel(new MigLayout("gap 0, insets 12, fill", "[center]", "[center]"));
        JPanel subPanel3 = new JPanel(new MigLayout("gap 0, insets 0, fill", "", "[center]"));
        JPanel subPanel4 = new JPanel(new MigLayout("gap 0, insets 0, fill", "", "[fill][grow]"));
        setBackground(Value.lighter_gray);
        subPanel1.setBackground(Value.dark_gray);
        subPanel2.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        subPanel2.setBackground(Value.bolder_gray);
        subPanel3.setBackground(Value.dark_gray);
        subPanel3.putClientProperty(FlatClientProperties.STYLE, "arc: 100");


        JLabel avatarLabel = new JLabel(new FlatSVGIcon("Pictures/Icon/Pics1/person.svg", 40, 40));
        JLabel nameLabel = new JLabel("Your Name");
        JLabel statusLabel = new JLabel("Last Seen Recently");
        avatarLabel.setBorder(new FlatRoundBorder());

        JLabel callButton = new JLabel();
        JLabel videoCallButton = new JLabel();
        JLabel imageButton = new JLabel();
        JLabel settingButton = new JLabel();


        scrollPane = new JScrollPane(chatBody());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollAnimation = new ScrollAnimation();
        scrollToBottom();
        scrollToBottomWithAnimation();

        callButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/call.svg", 25, 25));
        videoCallButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/video-call.svg", 25, 25));
        imageButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/image.svg", 25, 25));
        settingButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/setting.svg", 25, 25));

        subPanel2.add(callButton, "cell 0 0");
        subPanel2.add(videoCallButton, "cell 1 0");
        subPanel2.add(imageButton, "cell 2 0");
        subPanel2.add(settingButton,"cell 3 0");

        subPanel1.add(avatarLabel, "cell 0 0, span y 2");
        subPanel1.add(nameLabel, "cell 1 0, gap left 20, gap top 4");
        subPanel1.add(statusLabel, "cell 1 1, gap left 20, align left, push x");
        subPanel1.add(subPanel2, "cell 2 0,span y 2, wrap, w 160!, gap left 80");

        add(subPanel1, "wrap");
        subPanel4.add(scrollPane, "w 100%, h 100%");
        add(subPanel4, "wrap");
        addItem = new AddItem(scrollAnimation, scrollPane, body);
        add(sentMessagePanel());
    }

    private JPanel chatBody () {
        body = new JPanel(new MigLayout("fillx,ay bottom", "", "10[]10"));
        return body;
    }

    private JPanel sentMessagePanel () {
        JPanel panel = new JPanel(new MigLayout("insets 0, gap 0"));
        JPanel inputPanel = new JPanel(new MigLayout("fill, insets 0, gap 0", "[fill][grow 0]", "[fill]"));
        JLabel sentLabel = new JLabel();
        JTextField sentTextField = new JTextField();
        JButton emojiButton = new JButton(new FlatSVGIcon("Pictures/Icon/Pics1/emoji.svg", 22, 22));
        JButton attachButton = new JButton(new FlatSVGIcon("Pictures/Icon/Pics1/attach.svg", 25, 25));
        JToolBar toolBar = new JToolBar();
        attachButton.setMargin(new Insets(4, 4, 4, 4));
        emojiButton.setMargin(new Insets(4, 4, 4, 4));
        sentTextField.setMargin(new Insets(0, 18, 0, 0));
        toolBar.putClientProperty(FlatClientProperties.STYLE, "borderMargins: 0, 0, 0, 8");

        attachButton.addActionListener(e -> fileAction());

        emojiButton.addActionListener(e -> fileAction());

        toolBar.add(attachButton);
        toolBar.add(emojiButton);


        sentTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, toolBar);

        panel.setLayout(new MigLayout());

        sentLabel.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/sent.svg"));
        sentTextField.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
        sentTextField.setFont(new Font("", Font.PLAIN, 16));
//        sentTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Pictures/Icon/Pics1/call.svg"));
        sentLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sentLabelAction(sentTextField);
            }
        });
        sentTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sentLabelAction(sentTextField);
                }
            }
        });
        inputPanel.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
        inputPanel.add(sentTextField);

        panel.add(inputPanel, "w 100%, h 42!, left");
        panel.add(sentLabel, "right");
//        addItem();
        return panel;
    }

    private void fileAction () {
        JnaFileChooser fileChooser =  new JnaFileChooser();
        boolean action = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this));
        if (action) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, String.valueOf(fileChooser.getSelectedFile()));
        }
    }

    public void scrollToBottom() {
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }
    public void scrollToBottomWithAnimation() {
        SwingUtilities.invokeLater(() -> scrollAnimation.scrollToMax(scrollPane.getVerticalScrollBar()));
    }
    private void sentLabelAction (JTextField sentTextField) {
        String message = sentTextField.getText().trim();
        if (!message.isEmpty()) {
            addItem.addMessage(message, true);
            sentTextField.setText(null);
        }
    }
    private JPanel body;
    private JScrollPane scrollPane;
    private ScrollAnimation scrollAnimation;
    private AddItem addItem;
}
