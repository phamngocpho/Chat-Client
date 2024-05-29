package Client.Panels;

import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatObject extends JPanel {
    private final int index;
    private int UserID;

    public int getUserID() {
        return UserID;
    }

    public void setID(int UserID) {
        this.UserID = UserID;
    }

    public ChatObject (int index) {
        this.index = index;
        init();
    }
    private void init () {
        setLayout(new MigLayout("gap 0, insets 0, fill", "10[60!, center]10[fill, 260!]0", "10[fill, 50%]10[fill, 50%, center]2"));
        JButton avatarButton = new JButton();
        avatarButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/person.svg", 30, 30));
        avatarButton.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        JLabel nameLabel = new JLabel("Full Name " + index);
        JLabel currentMessageLabel = new JLabel("Current Message " + index);
        add(avatarButton, "cell 0 0, span y 2");
        add(nameLabel, "cell 1 0, left");
        add(currentMessageLabel, "cell 1 1, left, wrap");
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Value.bolder_gray));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(Value.dark_gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(null);
            }
        });
        repaint();
        revalidate();
    }
}
