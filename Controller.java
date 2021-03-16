package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Controller extends JPanel {
    private SidePanel sidePanel;

    private final ArrayList<Double> data = new ArrayList<>();
    private final Main window;

    public Controller(Main window) throws IOException {
        this.window = window;
        this.init();

        this.window.pack();
        this.window.setVisible(true);
    }

    private void init() throws IOException {
        this.window.add(this);
        this.setBackground(Color.GRAY);

        this.setLayout(new GridBagLayout());

        // Create Chart & add to controller panel
        // Make sure these can't just be local
        Chart chart = new Chart();
        this.add(chart, new GridBagConstraints(
                1, 0, 2, 2,
                1.0, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 1000)
        );


        this.sidePanel = new SidePanel(chart);
        this.add(sidePanel, new GridBagConstraints(
                0, 0, 1, 3,
                .0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                sidePanel.save();
                System.exit(0);
            }
        });
    }
}
