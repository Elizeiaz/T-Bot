package sites;

import webParser.*;

public class Yoox extends HtmlParser {
    @Override
    public String getSiteName() {
        return "Yoox";
    }

    @Override
    public String getSiteUrl() {
        return "https://www.yoox.com/";
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
    public String setNextPageIdentifyer() {
        return "page=";
    }
}
