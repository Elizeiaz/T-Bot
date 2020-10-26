package core;

public class UserState {
    private String lastCommand;
//    Состояние 0 - команда выполнена;
//    Число отличное от 0 - Этап выполнения команды;
    private int userState;
    private String tmpUserInfo;

    public String getLastCommand() {
        return this.lastCommand;
    }

    public void setLastCommand(String value) {
        this.lastCommand = value;
    }

    public int getUserState() {
        return this.userState;
    }

    public void setUserState(int value) {
        this.userState = value;
    }

    public String getTmpUserInfo() {
        return this.tmpUserInfo;
    }

    public void setTmpUserInfo(String value) {
        this.tmpUserInfo = value;
    }
}
