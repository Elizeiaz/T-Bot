package webParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserSelector {
    private final HtmlTag brand;
    private final HtmlTag itemName;
    private final UrlTag itemUrl;
    private final UrlTag photoUrl;
    private final HtmlTag sizes;
    private final HtmlTag price;
    private final HtmlTag fullPrice;
    private final HtmlTag discountPrice;


    public ParserSelector(
            HtmlTag brand,
            HtmlTag itemName,
            UrlTag itemUrl,
            UrlTag photoUrl,
            HtmlTag sizes,
            HtmlTag price,
            HtmlTag fullPrice,
            HtmlTag discountPrice) {
        this.brand = brand;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.photoUrl = photoUrl;
        this.sizes = sizes;
        this.price = price;
        this.fullPrice = fullPrice;
        this.discountPrice = discountPrice;
    }


//    public List<HtmlTag> getIter() {
//        Field[] fields = ParserSelector.class.getDeclaredFields();
//
//        List<HtmlTag> selectors = new ArrayList<>();
//
//        for (Field field : fields) {
//            try {
//                selectors.add((HtmlTag) field.get(this)); // Ошибка каста
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return selectors;
//    }

    public HtmlTag getBrand() {
        return brand;
    }

    public HtmlTag getItemName() {
        return itemName;
    }

    public UrlTag getItemUrl() {
        return itemUrl;
    }

    public UrlTag getPhotoUrl() {
        return photoUrl;
    }

    public HtmlTag getSizes(){
        return sizes;
    }

    public HtmlTag getPrice() {
        return price;
    }

    public HtmlTag getFullPrice() {return fullPrice;}

    public HtmlTag getDiscountPrice() {
        return discountPrice;
    }

}

