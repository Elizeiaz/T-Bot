package webParser;

import core.JSONHandler;

import java.util.List;

public class Item {
    JSONHandler jsonHandler = new JSONHandler();

    public String siteName = "";
    public String siteUrl = "";

    public CategoryEnum category = CategoryEnum.NULL;

    public String brand = "";
    public String itemName = "";
    public String itemUrl = "";
    public String photoUrl = "";
    public int price = 999999;
    public int discountPrice = this.price;

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
            CategoryEnum category,
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
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public Item(
            String siteName,
            String siteUrl,
            CategoryEnum category
    ) {
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.category = category;
    }

    public boolean setField(String fieldName, String setValue){
        return false;
    }

}
