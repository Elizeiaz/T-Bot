package сore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    //Надо сделать boolean
    public void writeFile(String path, String text) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            out.write(text.getBytes());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean writeFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()){
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFile(String path) {
        try {
            //Нужна проверка на существование
            FileReader read = new FileReader(path);
            Scanner scan = new Scanner(read);
            StringBuilder result = new StringBuilder();

            while (scan.hasNextLine()) {
                result.append(scan.nextLine());
            }

            read.close();

            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
