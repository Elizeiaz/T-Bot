package сore;

import java.io.File;

public class Paths {
    public final String pathToInfo = "info";
    public final String pathToHelp = new File(this.pathToInfo, "help.txt").toString();
    public final String pathToStart = new File(this.pathToInfo, "start.txt").toString();
    public final String pathToUsers = "users";
    public final String pathToLog = new File("log").toString();
    public final String pathToTmp = new File(pathToLog, "tmp").toString();
    public final String pathToLogFile = new File(pathToTmp, "log_file.txt").toString();

    public String getPathToUser(String id){
        return new File(this.pathToUsers, id).toString();

    }
}
