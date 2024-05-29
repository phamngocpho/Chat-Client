import Features.Notifications.popup.GlassPanePopup;
import Features.Notifications.popup.component.GlassPaneChild;
import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class mess extends GlassPaneChild {
    public mess () {
        init();
        setOpaque(false);
    }
    private void init() {
        setLayout(new MigLayout());
        JPanel panel = new JPanel(new MigLayout("fill", "[fill,1360]", "[fill,760]"));
        panel.putClientProperty(FlatClientProperties.STYLE, " " +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");
        add(panel);
    }
}
