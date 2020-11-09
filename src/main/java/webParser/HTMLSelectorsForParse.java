package webParser;

public class HTMLSelectorsForParse {
    private final HTMLTag brand;
    private final HTMLTag itemModel;
    private final UrlTag itemUrl;
    private final UrlTag photoUrl;
    private final HTMLTag sizes;
    private final HTMLTag price;
    private final HTMLTag fullPrice;
    private final HTMLTag discountPrice;


    public HTMLSelectorsForParse(
            HTMLTag brand,
            HTMLTag itemModel,
            UrlTag itemUrl,
            UrlTag photoUrl,
            HTMLTag sizes,
            HTMLTag price,
            HTMLTag fullPrice,
            HTMLTag discountPrice) {
        this.brand = brand;
        this.itemModel = itemModel;
        this.itemUrl = itemUrl;
        this.photoUrl = photoUrl;
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

    public UrlTag getItemUrl() {
        return itemUrl;
    }

    public UrlTag getPhotoUrl() {
        return photoUrl;
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

