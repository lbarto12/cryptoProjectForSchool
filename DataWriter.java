package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataWriter {
    public <T> DataWriter(ArrayList<T> data, String filename) throws IOException {
        var out = new PrintWriter(filename);

        for (int i = 0; i < data.size(); ++i){
            out.println(data.get(i));
        }
        out.close();
    }
}
