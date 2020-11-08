package webParser;

import core.JSONHandler;

import java.util.List;
import java.util.StringJoiner;

public class Item {
    JSONHandler jsonHandler = new JSONHandler();

    private String siteName = "";
    private String siteUrl = "";

    private ItemCategoryEnum category = ItemCategoryEnum.NULL;

    private String brand = "";
    private String itemName = "";
    private String itemUrl = "";
    private String photoUrl = "";
    private List<Float> sizes;
    private int price = 999999;
    private int discountPrice = this.price;

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
//                case "itemName":
//                    this.itemName = itemDict.get(value);
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
            String siteUrl,
            ItemCategoryEnum category
    ) {
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.category = category;
    }

    public Item(
            String siteName,
            String siteUrl,
            ItemCategoryEnum category,
            String brand,
            String itemName,
            String itemUrl,
            String photoUrl,
            List<Float> sizes,
            int price,
            int discountPrice
    ) {
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.category = category;
        this.brand = brand;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.photoUrl = photoUrl;
        this.sizes = sizes;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public ItemCategoryEnum getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public List<Float> getSizes() {
        return sizes;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public String itemToString() {
        StringJoiner outString = new StringJoiner("\n");
        StringJoiner sizeString = new StringJoiner("  ");
        Double discount =  100 - ((double) this.getDiscountPrice() / this.getPrice() * 100);
        for (Float size : sizes){
            String strSize = size.toString();
            if (strSize.charAt(strSize.length() - 1) == '0'){
                sizeString.add(String.valueOf((int) Math.floor(size)));
            } else {
                sizeString.add(String.format("%.1f", size));
            }
        }

        outString.add(this.brand);
        if (!this.brand.equals(this.itemName)) {outString.add("Модель: " + this.itemName);}
        if (this.price == this.discountPrice) {
            outString.add("Цена " + this.price);
            } else {
            outString.add("Старая цена: " + this.price + "руб");
            outString.add("Цена со скидкой: " + this.discountPrice + "руб");
            outString.add("Ваша скидка: " + String.format("%.0f", discount) + "%");
        }
        if (!sizeString.toString().equals("")) {outString.add("Доступные размеры: " + sizeString.toString());}
        outString.add("");
        outString.add("Сайт: " + this.siteName);
        outString.add("Ссылка: " + this.itemUrl);

        return outString.toString();
    }
}
