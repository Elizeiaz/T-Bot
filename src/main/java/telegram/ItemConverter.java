package telegram;

import com.sun.tools.javac.Main;
import webParser.Item;

import java.util.StringJoiner;
import java.util.logging.Logger;

public class ItemConverter {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public String itemToString(Item item) {
        StringJoiner outString = new StringJoiner("\n");


        outString.add("<b>" + item.getBrand() + "</b>");
        if (!item.getBrand().equals(item.getItemModel())) {
            outString.add("Модель: " + item.getItemModel());
        }
        if (item.getDiscountPrice() == 0) {
            outString.add("Цена: " + item.getPrice());
        } else {
            outString.add("Старая цена: " + item.getPrice() + "руб");
            outString.add("Цена со скидкой: " + item.getDiscountPrice() + "руб");
            outString.add("Ваша скидка: " + item.getDiscount() + "%");
        }
        if (!item.getSizesString().equals("")) {
            outString.add("Доступные размеры: " + item.getSizesString());
        }

        outString.add("");
        outString.add("Сайт: " + item.getSiteName());
        outString.add("Ссылка: " + item.getItemURL());

        return outString.toString();
    }
}
