package Panels;

import Values.Value;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MediaPanel extends JPanel {

    public MediaPanel() {
        init();
    }
    public void init(){
        setLayout(new MigLayout("fill, insets 0", "[fill]"));
        setBackground(Value.dark_gray);
        JPanel pTop = new JPanel(new MigLayout("wrap, insets 0, gap 0", "[fill, 100%]", "0[fill]0[fill,100%]0[fill]0"));
        add(pTop);

        JPanel top = new JPanel(new MigLayout("wrap, fill, insets 0, gap 0", "[fill]", "[fill]"));
        top.setBackground(Value.dark_gray);
        top.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Value.bolder_gray));
        pTop.add(top, "h 225!, top");

        JPanel avtPanel = new JPanel(new MigLayout("fill, insets 0", "[center]", "[fill]"));
        avtPanel.setBackground(Value.dark_gray);

        JLabel avtLabel = new JLabel();
        avtPanel.add(avtLabel, "wrap, h 60!, w 60!");

        JLabel nameLabel = new JLabel("Quang Vinh");
        nameLabel.setFont(new Font("", Font.BOLD, 15));
        avtPanel.add(nameLabel);

        top.add(avtPanel);


        JLabel icon1 = new JLabel();
        icon1.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/facebook.svg"));
        JLabel icon2 = new JLabel();
        icon2.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/instagram.svg"));
        JLabel icon3 = new JLabel();
        icon3.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/twitter.svg"));
        JLabel icon4 = new JLabel();
        icon4.setIcon(new FlatSVGIcon("Pictures/Icon/Pics1/tiktok.svg"));
        JPanel hop = new JPanel(new MigLayout("insets 5, gap 0"));
        hop.putClientProperty(FlatClientProperties.STYLE, "arc:100");
        hop.setBackground(Value.bolder_gray);


        hop.add(icon1);
        hop.add(icon2);
        hop.add(icon3);
        hop.add(icon4);
        top.add(hop, "h 40!, w 130!, center");

        JPanel center = new JPanel(new MigLayout("wrap, insets 0, fill"));
        center.setBackground(Value.dark_gray);
        center.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Value.bolder_gray));
        int num = 5;
        JLabel mediaLabel = new JLabel("Media" + "(" + num + ")");
        mediaLabel.setFont(new Font("", Font.BOLD, 25));
        mediaLabel.setForeground(Color.WHITE);
        center.add(mediaLabel, "gap 10");
        JPanel pictPanel = new JPanel(new MigLayout("fill, wrap 3", "[fill, center]", "fill"));
        pictPanel.setBackground(Value.dark_gray);
        for (int i = 0; i < 6; i++) {
            pictPanel.add(new JLabel(new FlatSVGIcon("Pictures/Icon/Pics1/image.svg", 80, 80)));
        }
        center.add(pictPanel, "center");


        JButton allPictButton = new JButton("Load More Image");

        allPictButton.setFont(new Font("", Font.BOLD, 13));
        allPictButton.setForeground(Color.WHITE);
        center.add(allPictButton, "h 40!,gap y 5, center");


        pTop.add(center);


        JPanel bottom = new JPanel(new MigLayout("fill, wrap, insets 20", "[fill]", "15[fill]15"));
        bottom.setBackground(Value.dark_gray);

        JLabel categoryLabel = new JLabel("Category Label");
        categoryLabel.setFont(new Font("", Font.BOLD, 22));
        bottom.add(categoryLabel);

        JCheckBox importCheckbox = new JCheckBox("Important");
        importCheckbox.setFont(new Font("", Font.BOLD, 17));

        JCheckBox cusCheckbox = new JCheckBox("Customer");
        cusCheckbox.setFont(new Font("", Font.BOLD, 17));

        JCheckBox friendCheckbox = new JCheckBox("Friends");
        friendCheckbox.setFont(new Font("", Font.BOLD, 17));

        JCheckBox familyCheckbox = new JCheckBox("Family");
        familyCheckbox.setFont(new Font("", Font.BOLD, 17));

        bottom.add(importCheckbox, "gap left 20");
        bottom.add(cusCheckbox, "gap left 20");
        bottom.add(friendCheckbox, "gap left 20");
        bottom.add(familyCheckbox, "gap left 20");

        pTop.add(bottom);
        JButton button = new JButton("Add Category Label");
        button.setFont(new Font("", Font.BOLD, 13));
        bottom.add(button, "h 45!, center");
    }
}
