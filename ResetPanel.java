package com.company;

import javax.swing.*;
import java.awt.*;

public class ResetPanel extends JPanel {
    JButton reset;

    public ResetPanel(){
        super();
        this.init();
    }

    private void init(){
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.lightGray);

        this.add(new JLabel(), new GridBagConstraints(
                0, 3, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.reset = new JButton("   RESET  ");
        this.add(reset, new GridBagConstraints(
                0, 4, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 5, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );
    }
}
