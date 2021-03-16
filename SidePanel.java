package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

public class SidePanel extends JPanel {
    private TickerPanel ticker;
    private MovementPanel moveText;
    private SpeedPanel speedPanel;
    private CryptoTab cryptos;
    private ResetPanel reset;
    private final Chart chart;

    public SidePanel(Chart chart) throws IOException {
        super();
        this.chart = chart;
        this.init();
    }

    private void init() {
        this.load();

        this.setBackground(Color.GRAY);
        this.setLayout(new GridBagLayout());

        this.ticker = new TickerPanel();
        this.add(ticker, new GridBagConstraints(
                0, 0, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.moveText = new MovementPanel();
        this.add(moveText, new GridBagConstraints(
                0, 1, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.speedPanel = new SpeedPanel();
        this.add(speedPanel, new GridBagConstraints(
                0, 2, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.cryptos = new CryptoTab();
        this.add(cryptos, new GridBagConstraints(
                0, 3, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.reset = new ResetPanel();
        this.add(reset, new GridBagConstraints(
                0, 4, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.linkButtons();
        this.linkDisplays();
    }

    private void linkButtons(){
        // Increase / decrease refresh time
        speedPanel.plusButton.addActionListener(e -> {
            speedPanel.tickUp(); chart.setTickSpeed(speedPanel.getTickSpeed());
        });

        speedPanel.minusButton.addActionListener(e -> {
            speedPanel.tickDown(); chart.setTickSpeed(speedPanel.getTickSpeed());
        });


        // crypto tabs
        cryptos.BitCoin.addActionListener(e -> this.changeTab(Chart.Cryptos.BitCoin));

        cryptos.Ethereum.addActionListener(e -> this.changeTab(Chart.Cryptos.Ethereum));

        cryptos.DogeCoin.addActionListener(e -> this.changeTab(Chart.Cryptos.DogeCoin));


        // Add Reset
        reset.reset.addActionListener(e -> chart.clearCurrent());
    }

    private void linkDisplays(){
        new Thread(() -> {
            while (true){
                try {
                    this.moveText.setDirText(this.chart.getTickDir());
                    this.ticker.setTicker(this.chart.getCurrentCrypto());
                    Thread.sleep(speedPanel.getTickSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Just for a bit of abstraction:
    private void changeTab(Chart.Cryptos to){
        this.chart.changeChart(to);
        this.moveText.setDirText(this.chart.getTickDir());
        this.ticker.setTicker(this.chart.getCurrentCrypto());
    }


    // Exit Process / Save
    public void save(){
        this.chart.save("data.csv");
    }

    public void load(){
        this.chart.load(Path.of("data.csv"));
    }
}
