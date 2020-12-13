package sites;

import webParser.ItemCategoryEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final Pattern numberRegExp = Pattern.compile("\\d+");
    public String toNextPage(String nextPageURISelector) {
        //todo parse query string
        try {
            int index = uri.indexOf(nextPageURISelector);
            if (index == -1) {
                return null;
            }

            String headUrl = uri.substring(0, index + nextPageURISelector.length());
            String tailUrl = "";
            Matcher matcher = numberRegExp.matcher(uri);
            int nextPage = 1;
            if (matcher.find(index + nextPageURISelector.length())) {
                tailUrl = uri.substring(index + nextPageURISelector.length() + matcher.group().length());
                nextPage = Integer.parseInt(matcher.group()) + 1;
            }
            return headUrl + nextPage + tailUrl;

        } catch (Exception e) {
            return null;
        }
    }
}
