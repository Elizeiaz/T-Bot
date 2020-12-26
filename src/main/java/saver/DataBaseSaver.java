package saver;

import core.User;
import webParser.Item;

public class DataBaseSaver implements InfoSaver{
    @Override
    public boolean saveInfo(User user) {
        return false;
    }

    @Override
    public boolean saveInfo(Item item) {
        return false;
    }
}
