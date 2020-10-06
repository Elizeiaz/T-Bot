package сore;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    public void writeFile(String path, String text) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(path);
            out.write(text.getBytes());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
