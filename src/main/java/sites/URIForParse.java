package sites;

import webParser.ItemCategoryEnum;

public class URIForParse {
    private String uri;
    private ItemCategoryEnum category;

    public URIForParse(
            String uri,
            ItemCategoryEnum category
    ){
        this.uri = uri;
        this.category = category;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ItemCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryEnum category) {
        this.category = category;
    }
}
