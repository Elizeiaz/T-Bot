package сommands;

import сore.FileHandler;
import сore.Paths;

public class Start implements Command{
    @Override
    public String execute() {
        return getInfo();
    }

    private String getInfo() {
        Paths path = new Paths();
        FileHandler fileHandler = new FileHandler();
        return fileHandler.readFile(path.pathToStart);
    }
}
