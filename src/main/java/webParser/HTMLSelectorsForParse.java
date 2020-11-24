package webParser;

public class HTMLSelectorsForParse {
    private final HTMLTag brand;
    private final HTMLTag itemModel;
    private final URLHTMLTag itemURL;
    private final URLHTMLTag photoURL;
    private final HTMLTag sizes;
    private final HTMLTag price;
    private final HTMLTag fullPrice;
    private final HTMLTag discountPrice;


    public HTMLSelectorsForParse(
            HTMLTag brand,
            HTMLTag itemModel,
            URLHTMLTag itemURL,
            URLHTMLTag photoURL,
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

    public URLHTMLTag getItemURL() {
        return itemURL;
    }

    public URLHTMLTag getPhotoURL() {
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

