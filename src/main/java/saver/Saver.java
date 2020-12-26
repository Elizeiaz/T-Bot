package saver;

import core.User;
import webParser.Item;

public class Saver {
    InfoSaver infoSaver;
    void setInfoSaver(InfoSaver infoSaver){
        this.infoSaver = infoSaver;
    }

    public boolean saveInfo(User user){
        return infoSaver.saveInfo(user);
    }

    public boolean saveInfo(Item item){
        return infoSaver.saveInfo(item);
    }
}
