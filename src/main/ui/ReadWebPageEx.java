package main.ui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadWebPageEx {

    public static void printURL(TextArea displayedMessage) throws IOException {

        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = br.readLine()) != null) {

                displayedMessage.append(line);
                displayedMessage.append(System.lineSeparator());
            }

        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}