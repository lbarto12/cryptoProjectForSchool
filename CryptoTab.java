package com.company;

import javax.swing.*;
import java.awt.*;

public class CryptoTab extends JPanel {
    public JButton BitCoin, Ethereum, DogeCoin;

    public CryptoTab() {
        super();
        this.init();
    }

    private void init(){
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.lightGray);

        this.setPreferredSize(new Dimension(90, 100));

        this.BitCoin = new JButton("  BTC  ");

        this.Ethereum = new JButton("  ETH  ");

        this.DogeCoin = new JButton(" DOG  ");

        this.add(new JLabel(), new GridBagConstraints(
                0, 0, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(BitCoin, new GridBagConstraints(
                0, 1, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 2, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(Ethereum, new GridBagConstraints(
                0, 3, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 4, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(DogeCoin, new GridBagConstraints(
                0, 5, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 6, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );
    }
}
