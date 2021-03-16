package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpeedPanel extends JPanel {
    private JLabel currentSpeedLabel;
    public JButton plusButton, minusButton;

    public SpeedPanel(){
        super();
        this.init();
    }

    private ArrayList<Integer> speeds;
    private int tickSpeed, speedIndex;

    private void init(){
        this.setBackground(Color.lightGray);

        this.setLayout(new GridBagLayout());

        this.setPreferredSize(new Dimension(90, 100));

        this.currentSpeedLabel = new JLabel("    500    ");

        this.plusButton = new JButton("+");

        this.minusButton = new JButton("-");


        this.add(new JLabel(), new GridBagConstraints(
                0, 0, 2, 2,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(currentSpeedLabel, new GridBagConstraints(
                0, 1, 2, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 3, 2, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(plusButton, new GridBagConstraints(
                1, 4, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(minusButton, new GridBagConstraints(
                0, 4, 1, 1,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        this.add(new JLabel(), new GridBagConstraints(
                0, 5, 2, 2,
                1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0)
        );

        // Streamline this
        this.speeds = new ArrayList<>();
        this.speeds.add(100);
        this.speeds.add(250);
        this.speeds.add(500);
        this.speeds.add(1000);
        this.speeds.add(5000);
        this.speeds.add(10000);

        this.tickSpeed = speeds.get(2);
        this.speedIndex = 2;
    }

    // To interface:

    public void tickUp(){
        if (speedIndex < speeds.size() - 1){
            speedIndex++;
            tickSpeed = speeds.get(speedIndex);
            currentSpeedLabel.setText(String.valueOf(tickSpeed));
        }
    }

    public void tickDown(){
        if (speedIndex > 0) {
            speedIndex--;
            tickSpeed = speeds.get(speedIndex);
            currentSpeedLabel.setText(String.valueOf(tickSpeed));
        }
    }

    public int getTickSpeed(){
        return this.tickSpeed;
    }
}
