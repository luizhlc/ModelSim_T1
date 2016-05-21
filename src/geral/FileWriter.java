package geral;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by decio on 21/05/16.
 */
public class FileWriter {

    public static void generateTxt(String s) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Relatório.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.print(s);
        writer.close();
    }
}
