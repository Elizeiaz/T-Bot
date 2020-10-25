package commands;


import core.FileWorker;
import core.Paths;


public class Start implements Command {
    @Override
    public String execute() {
        return getInfo();
    }

    private String getInfo() {
        Paths path = new Paths();
        FileWorker fileWorker = new FileWorker();
        return fileWorker.readFile(path.pathToStart);
    }
}
