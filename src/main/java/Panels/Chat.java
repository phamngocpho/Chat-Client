package Client.Panels;

import Client.Features.FormsManager;
import Client.Features.Notifications.popup.GlassPanePopup;
import Client.Features.Notifications.popup.component.SimplePopupBorder;
import Client.Features.Notifications.popup.component.SimplePopupBorderOption;
import Values.Value;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Chat extends JPanel {
    private JButton homeButton;
    private JButton messageButton;
    private JButton settingButton;
    private JButton logoutButton;
    private SettingPopup settingPopup;
    private ChatWindowPanel chatWindowPanel;
    private MediaPanel mediaPanel;
    private JPanel messagePanel;
    private JPanel miniMessagePanel;
    private JPanel cardMessagePanel;
    private CardLayout cardLayout;
    public Chat() {
        init();
    }
    private void init () {
        ChatListPanel chatListPanel = new ChatListPanel();
        chatWindowPanel = new ChatWindowPanel();
        mediaPanel = new MediaPanel();
        setLayout(new BorderLayout());
        add(featuresPanel(), BorderLayout.WEST);

        JPanel homePanel = new JPanel(new MigLayout("debug"));
        homePanel.add(new ChatObject(56));

        messagePanel = new JPanel(new MigLayout("gap 0, insets 0", "[fill][fill,70%][fill, 40%]", "[fill, grow]"));
        messagePanel.add(chatListPanel);
        messagePanel.add(chatWindowPanel);
        messagePanel.add(mediaPanel, "right");

        miniMessagePanel = new JPanel(new MigLayout("fill, insets 0, gap 0"));
        miniMessagePanel.add(chatWindowPanel);

        cardMessagePanel = new JPanel(new CardLayout());
        cardMessagePanel.add(messagePanel, "messagePanel");
        cardMessagePanel.add(homePanel, "homePanel");
        cardMessagePanel.add(miniMessagePanel, "miniMessagePanel");

        cardLayout = (CardLayout) cardMessagePanel.getLayout();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeAction();
            }
        });

        if (logoutButton != null) {
            logoutButton.addActionListener(e -> FormsManager.getInstance().showForm(new Login()));
        }
        if (messageButton != null) {
            messageButton.addActionListener(e -> {
                resizeAction();
            });
        }
        if (homeButton != null) {
            homeButton.addActionListener(e -> {
                cardLayout.show(cardMessagePanel, "homePanel");
            });
        }

        add(cardMessagePanel);
    }

    private void resizeAction () {
        double size = getWidth();
        double screen = Value.dimension.getWidth();
        if (size < screen * 0.525) {
            miniMessagePanel.add(chatWindowPanel);
            cardLayout.show(cardMessagePanel, "miniMessagePanel");
        } else {
            messagePanel.remove(mediaPanel);
            messagePanel.add(chatWindowPanel);
            messagePanel.add(mediaPanel, "right");
            cardLayout.show(cardMessagePanel, "messagePanel");
        }
    }
    private JPanel featuresPanel () {
        JPanel panel = new JPanel(new MigLayout("wrap, fill, insets 0, gap 0", "[left]", "[fill]"));
        JPanel subPanel = new JPanel(new MigLayout("wrap,fill", "[center]", "[center]"));
        panel.setBackground(Value.bolder_gray);
        subPanel.setBackground(Value.bolder_gray);

        List<JButton> listButton = new ArrayList<>();
        homeButton = new JButton();
        JButton saveButton = new JButton();
        messageButton = new JButton();
        JButton insightButton = new JButton();
        settingButton = new JButton();
        logoutButton = new JButton();

        listButton.add(homeButton);
        listButton.add(saveButton);
        listButton.add(messageButton);
        listButton.add(insightButton);

        settingPopup = new SettingPopup();

        messageButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Value.deep_blue));

        homeButton.putClientProperty("JButton.buttonType", "borderless");
        saveButton.putClientProperty("JButton.buttonType", "borderless");
        messageButton.putClientProperty("JButton.buttonType", "borderless");
        insightButton.putClientProperty("JButton.buttonType", "borderless");
        settingButton.putClientProperty("JButton.buttonType", "borderless");
        logoutButton.putClientProperty("JButton.buttonType", "borderless");

        homeButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/home.svg"));
        saveButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/save.svg"));
        messageButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/message.svg"));
        insightButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/insight.svg"));
        settingButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/setting.svg"));
        logoutButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/logout.svg"));
        settingButton.addActionListener(e -> settingPopup.show(settingButton, settingButton.getWidth() + 5, settingButton.getHeight() - 58));
        String [] actions = {};
        settingPopup.getPersonInfo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getWidth() > 700 && getHeight() > Value.dimension.getHeight() * 3 / 5 && e.getClickCount() == 1) {
                    GlassPanePopup.showPopup(new SimplePopupBorder(new PersonaInformation(), "Profile Information", new SimplePopupBorderOption().setWidth(700), actions, (controller, action) -> {}), "pop");
                }
            }
        });

        subPanel.add(homeButton, "w 58!, h 58!");
        subPanel.add(saveButton, "w 58!, h 58!");
        subPanel.add(messageButton, "w 58!, h 58!");
        subPanel.add(insightButton, "w 58!, h 58!");
        subPanel.add(settingButton, "w 58!, h 58!");
        panel.add(subPanel, "top, h ::380, gap 0 0 1 0");
        panel.add(logoutButton, "w 58!, h 48!, bottom, center");
        buttonAction(listButton);
        return panel;
    }

    private void buttonAction (List <JButton> listButton) {
        for (JButton button : listButton) {
            button.addActionListener(e -> {
                JButton bt = (JButton) e.getSource();
                for (JButton b : listButton) {
                    if (b != bt) {
                        b.setBorder(null);
                    }
                }
                bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.decode("#2a84ff")));
            });
        }
    }
}
