package webParser;


import java.util.List;
import java.util.StringJoiner;


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

        calculateDiscount();
    }

    public void calculateDiscount() {
        this.discount = (int) (100 - ((double) this.getDiscountPrice() / this.getPrice() * 100));
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

    public String getSizesString() {
        StringJoiner sizeString = new StringJoiner("  ");
        for (Float size : sizes) {
            String strSize = size.toString();
            if (strSize.charAt(strSize.length() - 1) == '0') {
                sizeString.add(String.valueOf((int) Math.floor(size)));
            } else {
                sizeString.add(String.format("%.1f", size));
            }
        }

        return sizeString.toString();
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
}
