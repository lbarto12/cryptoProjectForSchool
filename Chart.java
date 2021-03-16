package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

public class Chart extends JPanel {
    private final ArrayList<Double> btcdata = new ArrayList<>();
    private final ArrayList<Double> ethdata = new ArrayList<>();
    private final ArrayList<Double> dogdata = new ArrayList<>();

    private final ArrayList<Double> btcTimePoints = new ArrayList<>();
    private final ArrayList<Double> ethTimePoints = new ArrayList<>();
    private final ArrayList<Double> dogTimePoints = new ArrayList<>();

    private final ArrayList<ArrayList<Double>> allData = new ArrayList<>();

    private Axis xAxis;
    private Axis yAxis;
    private JPanel graph;

    public Chart(){
        super();
        this.init();
    }

    private void init(){
        new DataFetcher(this.btcdata, this.btcTimePoints, "https://robinhood.com/crypto/BTC", this).start();
        new DataFetcher(this.ethdata, this.ethTimePoints, "https://robinhood.com/crypto/ETH", this).start();
        new DataFetcher(this.dogdata, this.dogTimePoints, "https://robinhood.com/crypto/DOGE", this).start();

        this.createComponents();

        this.setLayout(new GridBagLayout());

        this.add(graph, new GridBagConstraints(
                1, 0, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 1000)
        );

        this.yAxis.setBackground(Color.GRAY);
        this.add(yAxis, new GridBagConstraints(
                0, 0, 1, 2,
                .0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 1000)
        );

        this.xAxis.setBackground(Color.GRAY);
        this.add(xAxis, new GridBagConstraints(
                1, 1, 1, 1,
                1.0, .0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.allData.add(this.btcdata);
        this.allData.add(this.btcTimePoints);
        this.allData.add(this.ethdata);
        this.allData.add(this.ethTimePoints);
        this.allData.add(this.dogdata);
        this.allData.add(this.dogTimePoints);
    }

    private void createComponents() {
        this.graph = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                switch (currentCrypto){
                    case BitCoin -> drawCrypto(g, btcdata);
                    case Ethereum -> drawCrypto(g, ethdata);
                    case DogeCoin -> drawCrypto(g, dogdata);
                }
            }
        };
        this.xAxis = new Axis(Axis.AxisType.x) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                switch (currentCrypto){
                    case BitCoin -> this.updateDataSet(btcTimePoints);
                    case Ethereum -> this.updateDataSet(ethTimePoints);
                    case DogeCoin -> this.updateDataSet(dogTimePoints);
                }
            }
        };
        this.yAxis = new Axis(Axis.AxisType.y) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                switch (currentCrypto){
                    case BitCoin -> this.updateDataSet(btcdata);
                    case Ethereum -> this.updateDataSet(ethdata);
                    case DogeCoin -> this.updateDataSet(dogdata);
                }
            }
        };
    }

    public enum Cryptos {
        BitCoin,
        Ethereum,
        DogeCoin
    }

    private Cryptos currentCrypto = Cryptos.BitCoin;

    private int tickSpeed = 500;

    private void drawCrypto(Graphics g, ArrayList<Double> data){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, graph.getWidth(), graph.getHeight());

        // Grid Lines
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 10; ++i){
            // Vertical
            g.drawLine(
                    i * (graph.getWidth() / 10), 0,
                    i * (graph.getWidth() / 10), this.graph.getHeight()
            );

            // Horizontal
            g.drawLine(
                    0, i * (graph.getHeight() / 10),
                    graph.getWidth(), i * (graph.getHeight() / 10)
            );
        }

        g.setColor(Color.ORANGE);
        if(data.size() > 0) {
            double _max = Collections.max(data), _min = Collections.min(data);
            double max = _max + ((_max - _min) * .1f);
            double min = _min - ((_max - _min) * .1f);
            double xStep = (double) graph.getWidth() / ((double) data.size() - 1);
            double yStep = (double) graph.getHeight() / (max - min);
            for (int i = 1; i < data.size(); i++) {
                if (i == data.size() - 1) g.setColor(Color.GREEN);
                g.drawLine((int) (xStep * (i - 1)), (int) (yStep * (max - data.get(i - 1))),
                        (int) (xStep * i), (int) (yStep * (max - data.get(i))));
            }
        }
    }

    // Interface

    public void setTickSpeed(int speed){
        this.tickSpeed = speed;
    }

    public int getTickSpeed() {
        return this.tickSpeed;
    }

    public MovementPanel.Dir getTickDir() {
        ArrayList<Double> data = null;

        switch (currentCrypto) {
            case BitCoin -> data = this.btcdata;
            case Ethereum -> data = this.ethdata;
            case DogeCoin -> data = this.dogdata;
        }

        // in case it draws on a null element (i.e. -1)
        try{
            if (data.size() > 1 && data.get(data.size() - 2) < data.get(data.size() - 1))
                return MovementPanel.Dir.Up;
            else
                return MovementPanel.Dir.Down;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MovementPanel.Dir.Up;
    }


    public void changeChart(Cryptos to){
        this.currentCrypto = to;
        this.repaint();
    }

    public Cryptos getCurrentCrypto(){
        return this.currentCrypto;
    }

    public void clearCurrent() {
        switch (this.currentCrypto){
            case BitCoin -> { this.btcdata.clear(); this.btcTimePoints.clear(); }
            case Ethereum -> { this.ethdata.clear(); this.ethTimePoints.clear(); }
            case DogeCoin -> { this.dogdata.clear(); this.dogTimePoints.clear(); }
        }
    }

    public void save(String file) {
        try (var out = new BufferedWriter(new FileWriter(file));) {
            for (var i : this.allData){
                for (var j : i)
                    out.append(String.valueOf(j)).append(",");
                out.append("\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(Path file) {

        try (var in = Files.newInputStream(file);
             var reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;

            for (var i : this.allData){
                line = reader.readLine();
                for (var j : line.split(","))
                    i.add(Double.parseDouble(j));
            }

        } catch (IOException x) {
            System.out.println("No data file found! One will be generated upon closing the app!");
        }
    }
}
