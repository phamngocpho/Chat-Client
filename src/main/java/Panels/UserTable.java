package Client.Panels;

import com.formdev.flatlaf.FlatClientProperties;
import jnafilechooser.api.JnaFileChooser;
import net.miginfocom.swing.MigLayout;
import Client.Features.Notifications.drawer.Drawer;

import javax.swing.*;
import java.awt.*;

public class UserTable extends JPanel {
    public UserTable (int isAdmin, int slRow, JTable table) {
        init(isAdmin, slRow, table);
    }
    private void init (int isAdmin, int slRow, JTable table) {
//        JFrame frame =  new JFrame();
        setLayout(new MigLayout("fill,insets 0"));
        JPanel panel = new JPanel(new MigLayout("insets 50 100 50 100", "[fill, 1360]", "[fill, 960]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:0;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");
//        JPanel avatarPanel = new JPanel(new MigLayout("wrap, insets 0"));
//        avatarPanel.putClientProperty(FlatClientProperties.STYLE, "" +
//                "arc:0;" +
//                "[light]background:darken(@background,3%);" +
//                "[dark]background:lighten(@background,3%)");
        JButton btUpload = new JButton("Upload");

        JLabel lbAvatar = new JLabel();
//        lbAvatar.setIcon(new AvatarIcon(getClass().getResource("/Pictures/Avatar/avatar.png"), 200, 200, 999));
//        avatarPanel.add(lbAvatar);//1000 150
//        avatarPanel.add(btUpload, "center");
        btUpload.addActionListener(e -> {
            JnaFileChooser fileChooser = new JnaFileChooser();
            fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this));
            if (fileChooser.getSelectedFile() != null) {
//                lbAvatar.setIcon(new AvatarIcon(fileChooser.getSelectedFile().toString(), 200, 200, 999));
            }
        });

        JLabel lbDescription = new JLabel("Member");
        lbDescription.setFont(new Font("Arial", Font.BOLD, 36));
        JSeparator jSeparator1 = new JSeparator();

        JLabel lbID = new JLabel("ID: " + table.getValueAt(slRow, 0));
        lbID.setFont(new Font("Arial", Font.BOLD, 21));
//        JTextField tfID = new JTextField(table.getValueAt(slRow, 0).toString());
//        tfID.setEditable(false);

        JLabel lbFullName = new JLabel("Full Name");
        lbFullName.setFont(new Font("Arial", Font.BOLD, 21));
        JTextField tfFullName = new JTextField(table.getValueAt(slRow, 1).toString() + " " + table.getValueAt(slRow, 2).toString());

        JLabel lbGender = new JLabel("Gender");
        lbGender.setFont(new Font("Arial", Font.BOLD, 21));
        JTextField tfShowGender = new JTextField(table.getValueAt(slRow, 3).toString());

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(new Font("Arial", Font.BOLD, 21));
        JTextField tfEmail = new JTextField(table.getValueAt(slRow, 4).toString());

        JLabel lbUsername = new JLabel("Username");
        lbUsername.setFont(new Font("Arial", Font.BOLD, 21));
        JTextField tfUsername = new JTextField(table.getValueAt(slRow, 5).toString());

        JButton btCancel = new JButton("Cancel");
        btCancel.setBackground(Color.decode("#475569"));

        JButton btSave = new JButton("Save");
        btSave.setBackground(Color.decode("#0ea5e9"));

        JSeparator jSeparator2 = new JSeparator();
        JLabel lbChangePassword = new JLabel("Change Password");
        lbChangePassword.setFont(new Font("Arial", Font.BOLD, 32));
        JPasswordField pfCurrentPassword = new JPasswordField();
        pfCurrentPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Current Password");
        JPasswordField pfNewPassword = new JPasswordField();
        pfNewPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "New Password");
        JPasswordField pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirm Password");
        JButton btBack = new JButton("Back");
        btBack.addActionListener(e -> {
            Drawer.getInstance().showDrawer();
        });
        if (isAdmin == 0) {
            tfFullName.setEnabled(false);
            tfShowGender.setEnabled(false);
            tfEmail.setEnabled(false);
            tfUsername.setEnabled(false);
        }
        JLabel lbSpace = new JLabel();
        JLabel lbBio = new JLabel("Bio");
        lbBio.setFont(new Font("Arial", Font.BOLD, 21));
        JTextArea taBio = new JTextArea();

        panel.add(lbDescription, "wrap");
        panel.add(jSeparator1, "wrap");
        panel.add(lbID, "wrap");
//        panel.add(tfID, "gapy 10");
        panel.add(lbBio, "wrap");
        panel.add(taBio, "wrap");
        panel.add(lbAvatar, "gapleft 300, cell 0 4, wrap");
        panel.add(lbSpace, "gapleft 680");
        panel.add(btUpload, "gapright 360, cell 0 5, wrap");
        panel.add(lbFullName, "wrap");
        panel.add(tfFullName, "wrap");
        panel.add(lbGender, "wrap");
//        panel.add(tfShowGender, "wrap");
        panel.add(createGenderPanel(table,slRow), "gapy 5, wrap");
        panel.add(lbEmail, "gapy 5, wrap");
        panel.add(tfEmail, "wrap");
        panel.add(lbUsername, "wrap");
        panel.add(tfUsername, "wrap");
        panel.add(btCancel, "cell 0 14");
        panel.add(btSave, "cell 0 14, gapright 1025, wrap");
//        panel.add(lbSpace, "cell 0 4");
//        panel.add(btUpload, "cell 0 4");
//        add(avatarPanel, "split 2");
        panel.add(jSeparator2, "wrap");
        panel.add(lbChangePassword, "wrap");
        panel.add(pfCurrentPassword, "wrap");
        panel.add(pfNewPassword, "wrap");
        panel.add(pfConfirmPassword, "wrap");
        panel.add(btBack, "wrap");
        add(panel);
    }
    private void updateToDatabase () {

    }
    private void getImageFromDatabase () {

    }
    private byte [] convertPathToBinary () {
        return null;
    }
    private Component createGenderPanel(JTable table, int slRow) {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JRadioButton jrMale = new JRadioButton("Male");
        JRadioButton jrFemale = new JRadioButton("Female");
        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(jrMale);
        groupGender.add(jrFemale);
        if (table.getValueAt(slRow, 0).equals("Male")) {
            jrMale.setSelected(true);
        } else {
            jrFemale.setSelected(true);
        }
        panel.add(jrMale);
        panel.add(jrFemale);
        return panel;
    }
//    private BufferedImage
}
