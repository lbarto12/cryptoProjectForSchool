package com.company;

import org.jsoup.Jsoup;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataFetcher extends Thread {
    ArrayList<Double> data;
    ArrayList<Double> times;
    String link;
    Chart chart;

    public DataFetcher(ArrayList<Double> data, ArrayList<Double> times, String link, Chart chart){
        this.data = data;
        this.times = times;
        this.link = link;
        this.chart = chart;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            try {
                //updateCurrency();
                String str = Jsoup.connect(link).get().toString();
                int ind = str.indexOf("<span class=\"udYkAW2UrhZln2Iv62EYb\">");
                String searchStr = str.substring(ind, ind + 100);
                int dollarIndex = searchStr.indexOf("$");
                String parsed = searchStr.substring(
                        dollarIndex+1,dollarIndex+10
                ).replace(
                        "<", ""
                ).replace(
                        "/", ""
                ).replace(
                        ",", ""
                ).replace(
                        "s", ""
                ).replace(
                        "p", ""
                ).replace(
                        "a", ""
                );
                double newDataPoint = Double.parseDouble(parsed);
                if (data.size() == 0 || newDataPoint != data.get(data.size() - 1)) {
                    data.add(newDataPoint);
                    times.add((double)System.currentTimeMillis());
                    chart.repaint();
                }
                Thread.sleep(chart.getTickSpeed());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
