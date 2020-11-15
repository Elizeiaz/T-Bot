package webParser;

import com.sun.tools.javac.Main;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class Item {
    private String siteName = "";
    private String siteURL = "";
    private ItemCategoryEnum category = ItemCategoryEnum.NULL;
    private String brand = "";
    private String itemModel = "";
    private String itemURL = "";
    private String photoURL = "";
    private final List<Float> sizes;
    private final int price;
    private final int discountPrice;
    private int discount = 0;

//    public Item(HashMap<String, String> itemDict) {
//        for (String value : itemDict.keySet()) {
//            switch (value) {
//                case "siteName":
//                    this.siteName = itemDict.get(value);
//                case "siteUrl":
//                    this.siteUrl = itemDict.get(value);
//                case "category":
//                    this.category = itemDict.get(value);
//                case "brand":
//                    this.brand = itemDict.get(value);
//                case "itemModel":
//                    this.itemModel = itemDict.get(value);
//                case "url":
//                    this.url = itemDict.get(value);
//                case "photoUrl":
//                    this.photoUrl = itemDict.get(value);
//                case "price":
//                    this.price = Integer.parseInt(itemDict.get(value));
//                case "discountPrice":
//                    this.discountPrice = Integer.parseInt(itemDict.get(value));
//            }
//        }
//    }


    public Item(
            String siteName,
            String siteURL,
            ItemCategoryEnum category,
            String brand,
            String itemModel,
            String itemURL,
            String photoURL,
            List<Float> sizes,
            int price,
            int discountPrice
    ) {
        this.siteName = siteName;
        this.siteURL = siteURL;
        this.category = category;
        this.brand = brand;
        this.itemModel = itemModel;
        this.itemURL = itemURL;
        this.photoURL = photoURL;
        this.sizes = sizes;
        this.price = price;
        this.discountPrice = discountPrice;

        normalize();
    }

    public void normalize() {
        this.discount = (int) (100 - ((double) this.getDiscountPrice() / this.getPrice() * 100));

//        //Normalize sizes
//        for (Float size : sizes){
//            sizes.indexOf()
//        }
//        sizes.set(1, (float) 2);
    }

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public LinkedHashMap<String, String> getFieldsValue() {
        LinkedHashMap<String, String> fieldsDict = new LinkedHashMap<>();
        Field[] fields = Item.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                fieldsDict.put(field.getName(), field.get(this).toString());
            } catch (Exception ignored) {
            }
        }

        return fieldsDict;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public String getSiteURL() {
        return this.siteURL;
    }

    public ItemCategoryEnum getCategory() {
        return this.category;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getItemModel() {
        return this.itemModel;
    }

    public String getItemURL() {
        return this.itemURL;
    }

    public String getPhotoURL() {
        return this.photoURL;
    }

    public List<Float> getSizes() {
        return this.sizes;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDiscountPrice() {
        return this.discountPrice;
    }

    public int getDiscount() {
        return this.discount;
    }

    public String itemToString() {
        StringJoiner outString = new StringJoiner("\n");
        StringJoiner sizeString = new StringJoiner("  ");
        for (Float size : sizes) {
            String strSize = size.toString();
            if (strSize.charAt(strSize.length() - 1) == '0') {
                sizeString.add(String.valueOf((int) Math.floor(size)));
            } else {
                sizeString.add(String.format("%.1f", size));
            }
        }

        outString.add(this.brand);
        if (!this.brand.equals(this.itemModel)) {
            outString.add("Модель: " + this.itemModel);
        }
        if (this.price == this.discountPrice) {
            outString.add("Цена " + this.price);
        } else {
            outString.add("Старая цена: " + this.price + "руб");
            outString.add("Цена со скидкой: " + this.discountPrice + "руб");
            outString.add("Ваша скидка: " + this.discount + "%");
        }
        if (!sizeString.toString().equals("")) {
            outString.add("Доступные размеры: " + sizeString.toString());
        }
        outString.add("");
        outString.add("Сайт: " + this.siteName);
        outString.add("Ссылка: " + this.itemURL);

        return outString.toString();
    }
}
