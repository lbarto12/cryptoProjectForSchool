package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public  Main(){
        this.setTitle("Crypto App");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, 700));
        var temp = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(temp.width / 10, temp.height / 10);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    public static void main(String[] args) throws Exception {
        new Controller(new Main());
    }
}
