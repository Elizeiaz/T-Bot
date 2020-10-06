package сore;

import java.io.File;

public class Paths {
    public final String curDir = new File("").getAbsolutePath();
    public final String pathToInfo = new File(this.curDir, "info").toString();
    public final String pathToHelp = new File(this.pathToInfo, "help.txt").toString();
    public final String pathToStart = new File(this.pathToInfo, "start.txt").toString();
    public final String pathToUsers = new File(this.pathToInfo, "users").toString();

    public String getPathToUser(String id){
        System.out.println(new File(this.pathToUsers, id).toString());
        return new File(this.pathToUsers, id).toString();

    }
}
