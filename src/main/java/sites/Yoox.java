package sites;

import webParser.*;

import java.util.HashMap;

public class Yoox extends AbstructSite {
    @Override
    public String getSiteName() {
        return "Yoox";
    }

    @Override
    public String getSiteUrl() {
        return "https://www.yoox.com/";
    }

    @Override
    public HashMap<String, CategoryEnum> getUrlsForParse() {
        HashMap<String, CategoryEnum> urlsDict = new HashMap<>();
        urlsDict.put("https://www.yoox.com/ru/%D0%B4%D0%BB%D1%8F%20%D0%BC%D1%83%D0%B6%D1%87%D0%B8%D0%BD/shoponline?dept=salemen#/dept=salemen&gender=U&page=1&attributes=%7b%27ctgr%27%3a%5b%27snkrs5%27%5d%7d&season=X", CategoryEnum.SNEAKERS);
        return urlsDict;
    }

    @Override
    public boolean isJSONParser() {
        return false;
    }

    @Override
    public String setItemContainer() {
        return "div[class='col-8-24']";
    }

    @Override
    public ParserSelector setParserSelector() {
        return new ParserSelector(
                new HtmlTag("brand font-bold text-uppercase", HtmlSelectorEnum.CLASS),
                new HtmlTag("title", HtmlSelectorEnum.CLASS),
                new UrlTag("a", HtmlSelectorEnum.TAG, "href"),
                new UrlTag("front imgFormat_20_f js-lazy-load flipside", HtmlSelectorEnum.CLASS, "data-original"),
                new HtmlTag("size text-light", HtmlSelectorEnum.CLASS),
                new HtmlTag("fullprice font-bold", HtmlSelectorEnum.CLASS),
                new HtmlTag("oldprice text-linethrough text-light", HtmlSelectorEnum.CLASS),
                new HtmlTag("newprice font-bold", HtmlSelectorEnum.CLASS)
        );
    }

    @Override
    public String getNextPage() {
        return null;
    }
}
