package сore;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    public void writeFile(String path, String text) throws IOException {
        FileOutputStream out = new FileOutputStream(path);
        out.write(text.getBytes());
        out.close();
    }

    public String readFile(String path) throws IOException {
        FileReader read = new FileReader(path);
        Scanner scan = new Scanner(read);
        String result = "";

        while(scan.hasNextLine()){
            result += scan.nextLine();
        }

        read.close();
        return result;
    }
}
