package Client.Panels;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ChatListPanel extends JPanel {
    public ChatListPanel() {
        init();
    }
    private void init () {
        setLayout(new MigLayout("wrap, insets 0", "[fill]", "[fill]"));
        JPanel subPanel1 = new JPanel(new MigLayout("fill, gap 0, insets 10"));
        JPanel subPanel2 = new JPanel(new MigLayout("wrap, insets 0, gap 0", "[fill]", "[grow 0]"));

        JButton searchButton = new JButton();
        JTextField searchTextField = new JTextField();
        JMenuBar menuBar = new JMenuBar();
        JMenu filterMenu = new JMenu("Categories");
        JScrollPane scrollPane = new JScrollPane(subPanel2);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        filterMenu.setHorizontalTextPosition(SwingConstants.LEFT);
        JMenuItem it1 = new JMenuItem("test test");
        JMenuItem it2 = new JMenuItem("test test");
        JMenuItem it3 = new JMenuItem("test test");
        filterMenu.add(it1);
        filterMenu.add(it2);
        filterMenu.add(it3);
        menuBar.add(filterMenu);
        filterMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                filterMenu.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/up-arrow.svg"));
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                filterMenu.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/down-arrow.svg"));
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        searchButton.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/search.svg"));
        filterMenu.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/down-arrow.svg"));

        searchButton.putClientProperty("JButton.buttonType", "borderless");
        searchButton.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        searchTextField.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        menuBar.putClientProperty(FlatClientProperties.STYLE, "selectionArc:50; borderColor: #1e1e1e; background: #1e1e1e");

        subPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:100");

        subPanel1.add(menuBar, "w 110!, h 35!");
        subPanel1.add(searchTextField, "w 200!, h 35!");
        subPanel1.add(searchButton, "w 40!, h 35!, right");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new MigLayout("wrap"));
        for (int i = 1; i < 20; ++i) {
            layeredPane.add(new ChatObject(i), 0);
        }
        subPanel2.add(layeredPane);
        add(subPanel1, "top, wrap");
        add(scrollPane, "w 100%, h 100%");

    }
}
