package Client.Panels;

import Client.Features.FormsManager;
import Client.Features.Notifications.popup.component.GlassPaneChild;
import Client.Features.Notifications.popup.component.PopupController;
import Client.Core.TextFieldLimit;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;

public class EmailConfirmation extends GlassPaneChild {
    private final String email;
    public EmailConfirmation (String email) {
        this.email = email;
        init();
    }
    private void init () {
        setLayout(new MigLayout("fill, insets 0, gap 0"));
        JPanel panel = new JPanel(new MigLayout("fill, insets 30, gap 0, wrap", "[center, 500]", "20[fill]0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");
        JLabel emailLabel = new JLabel();
        JLabel headlineLabel = new JLabel("Check your email");
        JLabel subLabel = new JLabel("<html><center>Enter the 6-digit code we just sent to <b>" + email + "</b> to continue.</center></html>");
        JTextField otpTextField = new JTextField();
        otpTextField.setDocument(new TextFieldLimit(6));
        JButton continueButton = new JButton("Continue");


        otpTextField.setHorizontalAlignment(SwingConstants.CENTER);

        continueButton.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        headlineLabel.setFont(new Font("", Font.BOLD, 15));
        emailLabel.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/email.svg", 0.8F));

        continueButton.addActionListener(e -> {
            if (isCorrect(otpTextField.getText().trim())) {
                FormsManager.getInstance().showForm(new Chat());
                PopupController action = createController();
                action.closePopup();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid verification code");
            }
        });

        panel.add(emailLabel, "wrap");
        panel.add(headlineLabel, "wrap");
        panel.add(subLabel, "w 320!");
        panel.add(otpTextField, "w 200!");
        panel.add(continueButton, "w 250!");
        panel.add(createResendLabel());
        add(panel);
    }

    private Component createResendLabel () {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null");
        JLabel receivedLabel = new JLabel("Didn't get a code?");
        JButton resendButton = new JButton("Click to resend");
        resendButton.setContentAreaFilled(false);
        resendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resendButton.setForeground(Value.sky_blue);
        panel.add(receivedLabel);
        panel.add(resendButton);
        return panel;
    }
    boolean isCorrect (String otp) {
        return !otp.isEmpty();
    }
}
