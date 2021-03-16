package com.company;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Axis extends JPanel {
    ArrayList<JLabel> points = new ArrayList<>();

    private final AxisType type;

    public Axis(AxisType type) {
        super();
        this.type = type;
        this.init();
    }

    private void init(){
        this.setLayout(new GridBagLayout());
        for (int i = 0; i < 10; ++i){
            points.add(new JLabel("Wait..."));

            if (type == AxisType.y){
                this.add(points.get(i), new GridBagConstraints(
                        0, i, 1, 1,
                        .0, 1.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 0), 0, 0)
                );
            }
            else if (type == AxisType.x) {
                points.get(i).setFont(points.get(i).getFont().deriveFont(10.f));
                this.add(points.get(i), new GridBagConstraints(
                        i, 0, 1, 1,
                        1.0, .0,
                        GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0)
                );
            }
        }
    }

    // Interface:

    public enum AxisType {
        x, // Time
        y  // Value
    }

    private void xUpdate(ArrayList<Double> data){
        if (data.size() > 1) {
            double max = Collections.max(data);
            double min = Collections.min(data);
            double step = (max - min) / 10;
            //int step = data.size() / 10;

            for (int i = 0; i < 10; ++i) {
                this.points.get(i).setText(
                        new SimpleDateFormat("MMM dd, hh:mm").format(min + step * i)
                );
            }
        }
        else {
            for (var i: this.points){
                i.setText("Wait...");
            }
        }
    }

    private void yUpdate(ArrayList<Double> data) {
        if (data.size() > 1) {
            double max = Collections.max(data);
            double min = Collections.min(data);
            double step = (max - min) / 10;

            for (int i = 0; i < 10; ++i) {
                this.points.get(i).setText("$" +
                        Double.toString(
                                BigDecimal.valueOf(
                                        max - (step * i)
                                ).setScale(
                                        5, RoundingMode.HALF_UP
                                ).doubleValue()
                        ) + " "
                );
            }
        } else {
            for (var i : this.points) {
                i.setText("Wait...");
            }
        }
    }

    public void updateDataSet(ArrayList<Double> data) {
        switch (this.type) {
            case x -> this.xUpdate(data);
            case y -> this.yUpdate(data);
            default -> throw new IllegalStateException("Unexpected value: " + this.type);
        }
    }
}
