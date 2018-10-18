package main.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Loadable {
    ArrayList<Item> loadList(String fileName) throws IOException, ParseException;
}
