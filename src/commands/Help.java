package commands;


import сore.FileWorker;
import сore.Paths;

public class Help implements Command {
    @Override
    public String execute() {
        return getInfo();
    }

    private String getInfo() {
        Paths path = new Paths();
        FileWorker fileWorker = new FileWorker();
        return fileWorker.readFile(path.pathToHelp);
    }
}
