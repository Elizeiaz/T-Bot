package saver;

import core.User;
import webParser.Item;

public interface InfoSaver {
    public boolean saveInfo(User user);
    public boolean saveInfo(Item item);
}
