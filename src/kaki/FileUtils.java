package kaki;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    //reads file, code from week8 
    public static String readFile(String filePath) {
        Path path = Paths.get(filePath);
        String content = new String();
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String lineContent;
            while ((lineContent = reader.readLine()) != null) {
                content += lineContent + ",";
            }
            reader.close(); // must close!

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return content;
    }

}
