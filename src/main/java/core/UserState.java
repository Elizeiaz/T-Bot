package core;

public class UserState {
    private String lastCommand;

    private String tmpUserInfo;

    public String getLastCommand() {
        return this.lastCommand;
    }

    public void setLastCommand(String value) {
        this.lastCommand = value;
    }

    public String getTmpUserInfo() {
        return this.tmpUserInfo;
    }

    public void setTmpUserInfo(String value) {
        this.tmpUserInfo = value;
    }
}
