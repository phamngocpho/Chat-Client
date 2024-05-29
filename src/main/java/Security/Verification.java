package Client.Security;

import Client.Core.TextFieldLimit;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Verification extends JPanel {

    public Verification(){
        init();
    }
    public void init(){
        setLayout(new MigLayout("wrap, insets 0 80 0 80, gap 0, center", "", "0[]5"));
        JLabel l1 = new JLabel("Two-factor");
        l1.setFont(new Font("",Font.BOLD,25));
        JLabel l2 = new JLabel("Authentication");
        l2.setFont(new Font("",Font.BOLD,25));
        JLabel l3 = new JLabel("<html><center>Enter the code displayed in your authentication app</center></html>");
        l3.setHorizontalTextPosition(SwingConstants.CENTER);
        l3.setFont(new Font("",Font.PLAIN,15));

        JTextField t = new JTextField();
        t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Code");
        t.setFont(new Font("",Font.PLAIN,16));
        t.setBackground(null);
        t.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Value.dark_gray));
        t.setDocument(new TextFieldLimit(6));
        add(l1, "center");
        add(l2, "center");
        add(l3, "gap top 15");
        add(t,"w 230!, h 40!, gap 0 0 10 5");
    }
}

