package Client.Panels;
import Client.Database.DatabaseConnection;
import Client.Features.Check;
import Client.Features.FormsManager;
import Client.Security.Encryption;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Register extends JPanel {

    public Register() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        JLabel lbMatch = new JLabel();
        firstName = new JTextField();
        lastName = new JTextField();
        datePicker = new DatePicker();
        phoneNumber = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        JButton btRegister = new JButton("Sign Up");
        check = new Check();

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 30 40 30 40", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" + "[light]background:darken(@background,3%);" + "[dark]background:lighten(@background,3%)");
        //Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "OK");

        firstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");
        lastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last name");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your email");
        phoneNumber.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your phone number");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Re-enter your password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");

        btRegister.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" + "[dark]background:lighten(@background,10%);" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0");
        datePicker.addDateSelectionListener(e -> {
            LocalDate date = datePicker.getSelectedDate();
            if (date != null) {
                dob = datePicker.getSelectedDate();
            }
        });
        datePicker.now();
        JFormattedTextField editor = new JFormattedTextField();
        datePicker.setEditor(editor);
        datePicker.setCloseAfterSelected(true);

        btRegister.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String fName = firstName.getText().trim();
            String lName = lastName.getText().trim();
            short gender = 0;
            String phone = phoneNumber.getText().trim();
            char [] pass = txtPassword.getPassword();
            char [] rePass = txtConfirmPassword.getPassword();
            String pas = new String(pass);
            String rePas = new String(rePass);
            if (!fName.isEmpty() && !lName.isEmpty()) {
                LocalDate currentDate = LocalDate.now();
                if (dob.plusYears(16).isBefore(currentDate) || dob.plusYears(16).equals(currentDate)) {
                    if (check.isEmail(email)) {
                        if (!isExists(email)) {
                            if (!phone.isEmpty()) {
                                if (check.isPhoneNumber(phone)) {
                                    if (!pas.isEmpty() && !rePas.isEmpty()) {
                                        if (check.isMatch(txtPassword, txtConfirmPassword)) {
                                            Encryption encryption = new Encryption();
                                            String password = encryption.hashPassword(pas);
                                            if (jrMale.isSelected()) gender = 1;
                                            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(Value.REGISTER)) {
                                                statement.setString(1, fName + ' ' + lName);
                                                statement.setShort(2, gender);
                                                statement.setDate(3, Date.valueOf(dob));
                                                statement.setString(4, email);
                                                statement.setBoolean(5, false);
                                                statement.setString(6, phone);
                                                statement.setString(7, password);
                                                statement.setString(8, null);
                                                statement.executeUpdate();
                                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Successful registration");
                                                FormsManager.getInstance().showForm(new Login());
                                            } catch (SQLException ex) {Notifications.getInstance().show(Notifications.Type.ERROR, "Data update error in SQL Server");}
                                        } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Passwords don't match. Try again!");}
                                    } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Passwords and Re-enter password mustn't be empty!");}
                                } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Wrong phone number format");}
                            } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Phone number mustn't be empty");}
                        } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Email already exists");}
                    } else {Notifications.getInstance().show(Notifications.Type.ERROR, "Wrong email format");}
                } else {Notifications.getInstance().show(Notifications.Type.ERROR, "You must be at least 16 years old to use the app");}
            } else {Notifications.getInstance().show(Notifications.Type.ERROR, "First and Last Name mustn't be empty");}
        });
        JLabel lbTitle = new JLabel("Welcome to our Application");
        JLabel description = new JLabel("Join us to play, connect, and make new friends. Sign up now and start playing!");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" + "[dark]foreground:darken(@foreground,30%)");

        check.initPasswordField(txtPassword);

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Full Name"), "gap y 10");
        panel.add(firstName, "split 2");
        panel.add(lastName);
        panel.add(editor, "gap y 8, h 35!");
        panel.add(new JLabel("Gender"), "gap y 8");
        panel.add(createGenderPanel());
        panel.add(new JSeparator(), "gap y 5 5");
        panel.add(new JLabel("Email"), "gap y 8");
        panel.add(txtEmail);
        panel.add(new JLabel("Phone"), "gap y 8");
        panel.add(phoneNumber);
        panel.add(new JLabel("Password"), "gap y 8");
        panel.add(txtPassword);
        panel.add(check,"gap y 0");
        panel.add(new JLabel("Confirm Password"), "gap y 1");
        panel.add(txtConfirmPassword);
        check.isMatch(txtPassword, txtConfirmPassword, lbMatch);
        panel.add(lbMatch, "gap y 7");
        panel.add(btRegister, "gap y 5");
        panel.add(createLoginLabel(), "gap y 10");
        add(panel);
    }

    private Component createGenderPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null");
        jrMale = new JRadioButton("Male");
        JRadioButton jrFemale = new JRadioButton("Female");
        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(jrMale);
        groupGender.add(jrFemale);
        jrMale.setSelected(true);
        panel.add(jrMale);
        panel.add(jrFemale);
        return panel;
    }

    private Component createLoginLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null");
        JButton btLogin = new JButton("Sign in here");
        btLogin.setForeground(Value.sky_blue);
        btLogin.putClientProperty(FlatClientProperties.STYLE, "border:3,3,3,3");
        btLogin.setContentAreaFilled(false);
        btLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btLogin.addActionListener(e -> FormsManager.getInstance().showForm(new Login()));
        JLabel label = new JLabel("Already have an account ?");
        label.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" + "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(btLogin);
        return panel;
    }
    private boolean isExists (String email) {
        try {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(Value.EMAIL_EXIST)) {
                statement.setString(1, email);
                if (statement.executeQuery().next()) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error Checking");
        }
        return false;
    }

    private JTextField firstName;
    private JTextField lastName;
    private JRadioButton jrMale;
    private JTextField phoneNumber;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private Check check;
    private DatePicker datePicker;
    private LocalDate dob;
}
