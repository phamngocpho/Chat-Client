package Client.Panels;

import Client.Component.Toggle.ToggleAdapter;
import Client.Component.Toggle.ToggleButton;
import Client.Features.Check;
import Client.Features.Notifications.popup.GlassPanePopup;
import Client.Features.Notifications.popup.component.SimplePopupBorder;
import Client.Features.Notifications.popup.component.SimplePopupBorderOption;
import Client.Security.Verification;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import raven.toast.Notifications;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import java.awt.*;

public class PersonaInformation extends JPanel {


    public PersonaInformation() {
        init();
    }

    public void init() {
        setLayout(new MigLayout("fill, insets 0, gap 0", "[fill]", "[][grow]"));
        JLabel title = new JLabel("Profile Information");
        title.setFont(new Font("", Font.BOLD, 23));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new MaterialTabbedUI());
        JPanel edit = new JPanel(new MigLayout("fill, wrap, insets 20", "[fill, center]", "20[top]0[fill]20"));
//        edit.setBackground(Color.decode("#2b2d30"));
        JPanel social = new JPanel();
//        social.setBackground(Color.decode("#2b2d30"));
        JPanel security = new JPanel(new MigLayout("fill, insets 0", "20[fill]40[]40[]0[]0", "20[top]0[fill]0"));
//        security.setBackground(Color.decode("#2b2d30"));

        JLabel label1 = new JLabel("Customize Avatar");
        label1.setFont(new Font("", Font.BOLD, 15));
        JLabel label2 = new JLabel("Update avatar following this format PNG,");
        label2.setFont(new Font("", Font.PLAIN, 14));
//        label2.setBackground(Color.decode("#e8ecf4"));
        JLabel label3 = new JLabel("JPG (max 800 x 800px)");
        label3.setFont(new Font("", Font.PLAIN, 14));
//        label3.setBackground(Color.decode("#e8ecf4"));
        JLabel label4 = new JLabel("Upload image click here");
        label4.setFont(new Font("", Font.PLAIN, 14));
//        label4.setBackground(Color.decode("#e8ecf4"));
        JLabel label5 = new JLabel("Or Drag & Drop");
        label5.setFont(new Font("", Font.PLAIN, 14));
//        label5.setBackground(Color.decode("#e8ecf4"));

        JButton avaButton = new JButton();
        avaButton.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        avaButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/person.svg", 90, 90));

        JPanel avatarPanel = new JPanel(new MigLayout(" fill, insets 10", "[fill]", "0[40!]0[30!, top]0"));
//        avatarPanel.setBackground(Color.decode("#2b2d30"));
        avatarPanel.add(label1,"cell 0 0, wrap");
        avatarPanel.add(label2, "cell 0 1, wrap");
        avatarPanel.add(label3," top");
        avatarPanel.add(avaButton,"cell 1 0, w 90!, h 90!, span y 3, top");
        avatarPanel.add(label4,"cell 2 0,bottom, wrap");
        avatarPanel.add(label5,"bottom, gap left 20");

        JPanel informationPanel = new JPanel(new MigLayout("fill, insets 20", "[fill]", "20[fill]40[]40[]0[]0"));
//        informationPanel.setBackground(Color.decode("#2b2d30"));

        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setFont(new Font("", Font.BOLD, 15));
//        fullNameLabel.setBackground(Color.decode("#5d6268"));

        JLabel nikNameLabel = new JLabel("Nick Name");
        nikNameLabel.setFont(new Font("", Font.BOLD, 15));
        nikNameLabel.setBackground(Color.decode("#5d6268"));
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("", Font.BOLD, 15));
        emailLabel.setBackground(Color.decode("#5d6268"));
        JLabel emailLabel2 = new JLabel("Thanks for verify your email.");
        emailLabel2.setFont(new Font("", Font.PLAIN, 13));
        emailLabel2.setBackground(Color.decode("#5d6268"));

        JTextField charlene = new JTextField();
        charlene.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        JTextField read = new JTextField();
        read.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        JTextField nickName = new JTextField();
        nickName.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        JTextField email = new JTextField();
        email.putClientProperty(FlatClientProperties.STYLE, "arc: 13");

        informationPanel.add(fullNameLabel, "cell 0 0");
        informationPanel.add(charlene, " h 40!,cell 1 0");
        informationPanel.add(read, " h 40!, cell 2 0");
        informationPanel.add(nikNameLabel, "cell 0 1");
        informationPanel.add(nickName, " h 40!, cell 1 1 , span x 2,");
        informationPanel.add(emailLabel, " cell 0 2");
        informationPanel.add(emailLabel2," cell 0 3");
        informationPanel.add(email, "h 40! ,cell 1 2, span x 2, span y 2");

        edit.add(avatarPanel,"wrap");
        edit.add(new JSeparator(), "wrap, w ::90%");
        edit.add(informationPanel);

        JLabel label01 = new JLabel("Two-factor Authentication");
        label01.setFont(new Font("", Font.BOLD, 16));
        JLabel label03 = new JLabel("Change Password");
        label03.setFont(new Font("", Font.BOLD, 16));
        JLabel label04 = new JLabel("Current Password");
        JLabel label05 = new JLabel("New Password");
        JLabel matchLabel = new JLabel();
        JLabel label06 = new JLabel("Confirm New Password");

        JPasswordField currentPass;
        currentPass = new JPasswordField();
        currentPass.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        JPasswordField newPass = new JPasswordField();
        newPass.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        JPasswordField confirmPass = new JPasswordField();
        confirmPass.putClientProperty(FlatClientProperties.STYLE, "arc: 13");

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");
        Check check = new Check();

        cancel.putClientProperty(FlatClientProperties.STYLE, "arc: 13");
        cancel.addActionListener(e -> GlassPanePopup.closePopup(0));

        check.isMatch(newPass, confirmPass, matchLabel);
        save.putClientProperty(FlatClientProperties.STYLE, "arc: 13");







        security.add(label01, "wrap");
        security.add(toggleButton(), "wrap, gap top 7, gap bottom 7");
        security.add(label03, "wrap, gap y 10");
        security.add(label04,"top, wrap, gap y 10");
        security.add(currentPass,"h 40!, wrap, gap y 4");
        security.add(label05,"wrap, gap y 10");
        security.add(newPass,"wrap, gap y 4");
        security.add(label06,"wrap, gap y 15");
        security.add(confirmPass,"wrap, top");
        security.add(matchLabel,"wrap, gap y 5");
        security.add(cancel, "w 100!,h 35!, gap left 300, gap y 20, gap bottom 15");
        security.add(save, "w 100!, h 35!, gap y 20, gap bottom 15");

        tabbedPane.add("Edit Profile", edit);
        tabbedPane.add("Social Media", social);
        tabbedPane.add("Client/Security", security);




//        add(title, "gap bottom 10, gap left 10, wrap, center");
        add(tabbedPane, "gap top 20, grow, push");

        setVisible(true);
    }
    private Component toggleButton () {
        JPanel panel = new JPanel(new MigLayout("insets 0, gap 0"));
//        panel.setBackground(Color.decode("#2b2d30"));
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setOpaque(false);
//        toggleButton.setSelected(true);
        JLabel label = new JLabel("Enable or disable two factor authentication");
        label.setFont(new Font("", Font.PLAIN, 14));
        panel.add(toggleButton, "h 30!, gap top 7, w 52!");
        panel.add(label);

        toggleButton.addToggleListener(new ToggleAdapter() {
            @Override
            public void onSelected(boolean selected) {
                if (selected) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Selected");
                    String [] actions = {"Cancel", "Next"};
                    GlassPanePopup.showPopup(new SimplePopupBorder(new Verification(), null, new SimplePopupBorderOption().setWidth(350), actions, ((controller, action) -> {
                        if (action == 1) {
                            String [] actions1 = {"Back", "Finish"};
                            GlassPanePopup.push(new SimplePopupBorder(new Verification(), null, actions1, (controller1, action1) -> {
                                if (action1 == 0) {
                                    GlassPanePopup.pop("pop");
                                } else {
                                    controller1.closePopup();
                                }
                            }), "pop");
                        } else {
                            controller.closePopup();
                        }
                    })), "pop");
                } else {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Deselected");
                }
            }
        });
        return panel;
    }
    public class MaterialTabbedUI extends MetalTabbedPaneUI {
        private Animator animator;

        public void setCurrentRectangle(Rectangle currentRectangle) {
            this.currentRectangle = currentRectangle;
            repaint();
        }

        private Rectangle currentRectangle;
        private TimingTarget target;
        public MaterialTabbedUI () {
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            animator = new Animator(500);
            animator.setResolution(0);
            tabPane.addChangeListener(e -> {
                int selected = tabPane.getSelectedIndex();
                if (selected != -1) {
                    if (currentRectangle != null) {
                        if (animator.isRunning()) {
                            animator.stop();
                        }
                        animator.removeTarget(target);
                        target = new PropertySetter(MaterialTabbedUI.this, "currentRectangle", currentRectangle, getTabBounds(selected, calcRect));
                        animator.addTarget(target);
                        animator.start();
                    }
                }
            });
        }
        @Override
        protected Insets getTabInsets(int i, int i1) {
            return new Insets(10, 10, 10, 10);
        }

        @Override
        protected void paintTabBorder(Graphics graphics, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(3, 155, 216));
            if (currentRectangle == null || !animator.isRunning()) {
                if (isSelected) {
                    currentRectangle = new Rectangle(x, y, w, h);
                }
            }
            if (currentRectangle != null) {
                if (tabPlacement == TOP) {
                    g2.fillRect(currentRectangle.x, currentRectangle.y + currentRectangle.height - 3, currentRectangle.width, 3);
                } else if (tabPlacement == BOTTOM) {
                    g2.fillRect(currentRectangle.x, currentRectangle.y, currentRectangle.width, 3);
                } else if (tabPlacement == LEFT) {
                    g2.fillRect(currentRectangle.x + currentRectangle.width - 3, currentRectangle.y, 3, currentRectangle.height);
                } else if (tabPlacement == RIGHT) {
                    g2.fillRect(currentRectangle.x, currentRectangle.y, 3, currentRectangle.height);
                }
            }
            g2.dispose();
        }

        @Override
        protected void paintContentBorder(Graphics graphics, int tabPlacement, int selectedIndex) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(200, 200, 200));
            Insets insets = getTabAreaInsets(tabPlacement);
            int width = tabPane.getWidth();
            int height = tabPane.getHeight();
            if (tabPlacement == TOP) {
                int tabHeight = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                g2.drawLine(insets.left, tabHeight, width - insets.right - 1, tabHeight);
            } else if (tabPlacement == BOTTOM) {
                int tabHeight = height - calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                g2.drawLine(insets.left, tabHeight, width - insets.right - 1, tabHeight);
            } else if (tabPlacement == LEFT) {
                int tabWidth = calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
                g2.drawLine(tabWidth, insets.top, tabWidth, height - insets.bottom - 1);
            } else if (tabPlacement == RIGHT) {
                int tabWidth = width - calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth) - 1;
                g2.drawLine(tabWidth, insets.top, tabWidth, height - insets.bottom - 1);
            }
            g2.dispose();
        }

        @Override
        protected void paintFocusIndicator(Graphics graphics, int i, Rectangle[] rectangles, int i1, Rectangle rectangle, Rectangle rectangle1, boolean bln) {
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            if (tabPane.isOpaque()) {
                super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
            }
        }
    }
}

