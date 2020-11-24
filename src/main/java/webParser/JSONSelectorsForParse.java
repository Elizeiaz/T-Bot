package webParser;

public class JSONSelectorsForParse {
    private final String brand;
    private final String itemModel;
    private final String itemURL;
    private final String photoURL;
    private final String sizes;
    private final String price;
    private final String fullPrice;
    private final String discountPrice;


    public JSONSelectorsForParse(
            String brand,
            String itemModel,
            String itemURL,
            String photoURL,
            String sizes,
            String price,
            String fullPrice,
            String discountPrice) {
        this.brand = brand;
        this.itemModel = itemModel;
        this.itemURL = itemURL;
        this.photoURL = photoURL;
        this.sizes = sizes;
        this.price = price;
        this.fullPrice = fullPrice;
        this.discountPrice = discountPrice;
    }

    public String getBrand() {
        return brand;
    }

    public String getItemModel() {
        return itemModel;
    }

    public String getItemURL() {
        return itemURL;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getSizes() {
        return sizes;
    }

    public String getPrice() {
        return price;
    }

    public String getFullPrice() {
        return fullPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }
}
