package sites;

import webParser.*;

import java.util.HashMap;

public class Farfetch extends AbstructSite {

    public Farfetch(HashMap<String, CategoryEnum> urlsForParse) {
        super(urlsForParse);
    }

    @Override
    public String getSiteName() {
        return "Farfetch";
    }

    @Override
    public String getSiteUrl() {
        return "https://www.farfetch.com/";
    }

    @Override
    public boolean isJSONParser() {
        return false;
    }

    @Override
    public String setItemContainer() {
        return "li[class='_0a5d39 _d85b45']";
    }

    @Override
    public ParserSelector setParserSelector() {
        return new ParserSelector(
                new HtmlTag("_346238", HtmlSelectorEnum.CLASS),
                new HtmlTag("_d85b45", HtmlSelectorEnum.CLASS),
                new UrlTag("_d85b45", HtmlSelectorEnum.CLASS, "href"),
                new UrlTag("_d85b45", HtmlSelectorEnum.CLASS, "src"),
                new HtmlTag("_5cf853", HtmlSelectorEnum.CLASS),
                new HtmlTag("_5cf853", HtmlSelectorEnum.CLASS),
                new HtmlTag("_ad18db", HtmlSelectorEnum.CLASS),
                new HtmlTag("_ad18db", HtmlSelectorEnum.CLASS)
        );
    }

    @Override
    public String setNextPageIdentifyer() {
        return null;
    }
}
