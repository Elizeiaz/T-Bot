package сore;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWorker {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    //Надо сделать boolean
    public void writeFile(String path, String text) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            out.write(text.getBytes());
            out.close();
        } catch (IOException e){
            logger.log(Level.WARNING, "IOException: ", e);
        }

    }
    public boolean writeFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()){
                return true;
            }
            return false;
        } catch (IOException e){
            logger.log(Level.WARNING, "IOException: ", e);
            return false;
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
        } catch (IOException e){
            logger.log(Level.WARNING, "IOException: ", e);
            return null;
        }
    }
}
