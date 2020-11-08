package sites;

import webParser.ItemCategoryEnum;

import java.util.HashMap;

public class UrlsForParse {
    HashMap<String, ItemCategoryEnum> yoox = new HashMap<>();

    public UrlsForParse() {
        setYoox();
    }

    public void setYoox() {
        this.yoox.put(
                "https://www.yoox.com/ru/%D0%B4%D0%BB%D1%8F%20%D0%BC%D1%83%D0%B6%D1%87%D0%B8%D0%BD/%D0%BE%D0%B1%D1%83%D0%B2%D1%8C%20sale/shoponline#/dept=shoesmensl&gender=U&page=1&attributes=%7b%27ctgr%27%3a%5b%27snkrs5%27%5d%7d&season=X",
                ItemCategoryEnum.SNEAKERS
        );
    }

    public HashMap<String, ItemCategoryEnum> getYoox() {
        return this.yoox;
    }
}
