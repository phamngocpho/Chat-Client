package Client.Panels;
import Client.Features.FormsManager;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel {

    public Login() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 0, gap 0", "[center]", "[center]"));
        username = new JTextField();
        password = new JPasswordField();
        JCheckBox chRmb = new JCheckBox("Remember me");
        JButton btLogin = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap,fill,insets 35 45 30 45", "fill,300:336"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");
        btLogin.putClientProperty(FlatClientProperties.STYLE,
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        btLogin.addActionListener(this::btLoginAction);

        username.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter username or email");
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter password");

        JLabel lbTitle = new JLabel("Welcome back!");
        JLabel description = new JLabel("Nice to meet you. Please fill in your credentials");
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Username or email"), "gap y 8");
        panel.add(username);
        panel.add(new JLabel("Password"), "gap y 8");
        panel.add(password);
        panel.add(chRmb, "grow 0");
        panel.add(btLogin, "gap y 10");
        panel.add(createSignupLabel(), "gap y 10");
        add(panel);
    }

    private Component createSignupLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "background:null");
        JButton btRegister = new JButton("Sign up");
        btRegister.setForeground(Color.decode("#38bdf8"));
        btRegister.putClientProperty(FlatClientProperties.STYLE,
                "border:3,3,3,3");
        btRegister.setContentAreaFilled(false);
        btRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btRegister.addActionListener(e -> FormsManager.getInstance().showForm(new Register()));
        JLabel lb = new JLabel("Don't have an account ?");
        lb.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" + "[dark]foreground:darken(@foreground,30%)");
        panel.add(lb);
        panel.add(btRegister);
        return panel;
    }
    private void btLoginAction (ActionEvent e) {
        String phoneOrMail = username.getText();
        char [] passes = password.getPassword();
        String passWord = new String(passes);
        checkLogin(phoneOrMail, passWord);
    }
    private void checkLogin(String phoneOrMail, String password) {
//        if (phoneOrMail.equals("admin") && password.equals("admin")) {
            FormsManager.getInstance().showForm(new Chat());
//            GlassPanePopup.showPopup(new EmailConfirmation("phamngocpho1606@gmail.com"), "pop");
//            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Successful login as administrator");
//        } else {
//            Check check = new Check();
//            String sql;
////            if (check.isEmail(phoneOrMail)) {
////                sql = "SELECT * FROM Person WHERE Email = ? AND Password = ?";
////            } else {
////                sql = "SELECT * FROM Person WHERE Username = ? AND Password = ?";
////            }
//            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(Value.LOGIN)) {
//                statement.setString(1, phoneOrMail);
//                statement.setString(2, password);
//                try (ResultSet rs = statement.executeQuery()) {
//                    if (rs.next()) {
//                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Successful login");
//                        FormsManager.getInstance().showForm(new Chat());
//                    } else {
//                        MessageAlerts.getInstance().showMessage("Error", "Wrong username or password!", MessageAlerts.MessageType.ERROR);
//                    }
//                }
//            } catch (SQLException e) {
//                Notifications.getInstance().show(Notifications.Type.WARNING, "Error retrieving data");
//                System.out.println(e.getMessage());
//            }
//        }
    }


    private JTextField username;
    private JPasswordField password;
}
