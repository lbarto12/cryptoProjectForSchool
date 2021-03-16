package com.company;

import javax.swing.*;
import java.awt.*;

public class TickerPanel extends JPanel {
    private JLabel name;

    public TickerPanel(){
        super();
        this.init();
    }

    private void init(){
        this.setBackground(Color.GRAY);

        this.setLayout(new GridBagLayout());

        this.setPreferredSize(new Dimension(90, 100));

        this.name = new JLabel(" N / A ");
        this.name.setFont(new Font(name.getName(), Font.BOLD, 20));

        this.add(this.name);
    }


    // Interface

    public void setTicker(Chart.Cryptos crypto){
        switch (crypto){
            case BitCoin -> this.name.setText("    BTC    ");
            case Ethereum -> this.name.setText("    ETH    ");
            case DogeCoin -> this.name.setText("   DOG    ");
        }
    }
}
