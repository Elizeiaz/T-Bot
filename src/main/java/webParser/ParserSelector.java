package webParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParserSelector {
    private HtmlSelector brand;
    private HtmlSelector itemName;
    private HtmlSelector itemUrl;
    private HtmlSelector photoUrl;
    private HtmlSelector price;
    private HtmlSelector discountPrice;


    public ParserSelector(
            HtmlSelector brand,
            HtmlSelector itemName,
            HtmlSelector itemUrl,
            HtmlSelector photoUrl,
            HtmlSelector price,
            HtmlSelector discountPrice) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.photoUrl = photoUrl;
        this.price = price;
        this.discountPrice = discountPrice;
    }


    public List<HtmlSelector> getIter() {
        Field[] fields = ParserSelector.class.getDeclaredFields();

        List<HtmlSelector> selectors = new ArrayList<>();

        for (Field field : fields) {
            try {
                selectors.add((HtmlSelector) field.get(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return selectors;
    }

    public HtmlSelector getBrand() {
        return brand;
    }

    public HtmlSelector getItemName() {
        return itemName;
    }

    public HtmlSelector getItemUrl() {
        return itemUrl;
    }

    public HtmlSelector getPhotoUrl() {
        return photoUrl;
    }

    public HtmlSelector getPrice() {
        return price;
    }

    public HtmlSelector getDiscountPrice() {
        return discountPrice;
    }

}

