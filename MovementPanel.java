package com.company;

import javax.swing.*;
import java.awt.*;

public class MovementPanel extends JPanel {
    private JLabel direction;

    public MovementPanel(){
        super();
        this.init();
    }

    private void init(){
        this.setBackground(Color.GRAY);

        this.setLayout(new GridBagLayout());

        this.setPreferredSize(new Dimension(90, 100));

        this.direction = new JLabel("N / A");
        this.direction.setFont(new Font(direction.getName(), Font.BOLD, 20));

        this.add(this.direction);
    }

    public enum Dir {
        Up,
        Down
    }

    public void setDirText(Dir dir){
        switch (dir) {
            case Up -> {
                this.direction.setText("     UP     ");
                this.setBackground(Color.GREEN);
            }
            case Down -> {
                this.setBackground(Color.RED);
                this.direction.setText("  DOWN  ");
            }
        }
    }
}
