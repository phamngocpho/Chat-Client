package Client.Panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SettingPopup extends JPopupMenu {
    private final JMenu personInfo;
    private final JMenu setting;
    private final JMenu notification;
    private final JMenu intro;
    private final JMenu status;
    private final JMenuItem  tool;
    private final JMenuItem language;

    public JMenu getPersonInfo() {
        return personInfo;
    }

    public JMenu getSetting() {
        return setting;
    }

    public JMenu getNotification() {
        return notification;
    }

    public JMenu getIntro() {
        return intro;
    }

    public JMenu getStatus() {
        return status;
    }

    public JMenuItem getTool() {
        return tool;
    }

    public JMenuItem getLanguage() {
        return language;
    }

    public SettingPopup () {
        setLayout(new MigLayout("wrap", "[fill]", "10[center]10"));
        personInfo = new JMenu("Personal Information");
        personInfo.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/person.svg",25,25));

        setting = new JMenu("Setting");
        setting.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/setting.svg",25,25));
        tool = new JMenuItem("Tools");
        language = new JMenuItem("Language");

        setting.add(tool);
        setting.add(language);

        notification = new JMenu("Notification");
        notification.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/notification.svg", 25, 25));

        intro =  new JMenu("About");
        intro.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/about.svg", 25, 25));

        status = new JMenu("Active Status");
        status.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/status.svg", 25, 25));


        add(personInfo);
        add(setting);
        add(new JSeparator(), "h 10!");
        add(status);
        add(notification);
        add(intro);

    }
}

