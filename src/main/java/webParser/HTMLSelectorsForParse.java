package webParser;

public class HTMLSelectorsForParse {
    private final HTMLTag brand;
    private final HTMLTag itemModel;
    private final URLTag itemURL;
    private final URLTag photoURL;
    private final HTMLTag sizes;
    private final HTMLTag price;
    private final HTMLTag fullPrice;
    private final HTMLTag discountPrice;


    public HTMLSelectorsForParse(
            HTMLTag brand,
            HTMLTag itemModel,
            URLTag itemURL,
            URLTag photoURL,
            HTMLTag sizes,
            HTMLTag price,
            HTMLTag fullPrice,
            HTMLTag discountPrice) {
        this.brand = brand;
        this.itemModel = itemModel;
        this.itemURL = itemURL;
        this.photoURL = photoURL;
        this.sizes = sizes;
        this.price = price;
        this.fullPrice = fullPrice;
        this.discountPrice = discountPrice;
    }

    public HTMLTag getBrand() {
        return brand;
    }

    public HTMLTag getItemModel() {
        return itemModel;
    }

    public URLTag getItemURL() {
        return itemURL;
    }

    public URLTag getPhotoURL() {
        return photoURL;
    }

    public HTMLTag getSizes() {
        return sizes;
    }

    public HTMLTag getPrice() {
        return price;
    }

    public HTMLTag getFullPrice() {
        return fullPrice;
    }

    public HTMLTag getDiscountPrice() {
        return discountPrice;
    }

}

