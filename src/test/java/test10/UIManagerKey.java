import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class UIManagerKey {
    public static void printUIManagerKeys(String fileName) throws IOException {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keysEnumeration = defaults.keys();
        ArrayList<Object> keysList = Collections.list(keysEnumeration);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Object key : keysList) {
                writer.println(key);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        printUIManagerKeys("src/test/java/UIManagerKey.txt");
    }
}
