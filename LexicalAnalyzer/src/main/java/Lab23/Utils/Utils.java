package Lab23.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * The function of reading an input file
     * separating the input file after the separator ' '
     * @param fileName - name of input file
     * @return list of strings
     */
    public static List<String> readFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.length() > 0) {
                        list.add(word);
                    }
                }
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * The function of writing elements to a list in an output file
     * @param filename - name of output file
     * @param array - string list
     */
    public static void writeFile(String filename, ArrayList<?> array) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Object object : array) {
                bufferedWriter.write(object.toString());
                bufferedWriter.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
