package sites;

import webParser.*;

public class Yoox extends HTMLParser {
    @Override
    public String getSiteName() {
        return "Yoox";
    }

    @Override
    public String getSiteURI() {
        return "https://www.yoox.com/";
    }

    @Override
    public String getItemContainerHTMLSelector() {
        return "div[class='col-8-24']";
    }

    @Override
    public HTMLSelectorsForParse getHTMLSelectorsForParse() {
        return new HTMLSelectorsForParse(
                new HTMLTag("brand font-bold text-uppercase", HTMLSelectorEnum.CLASS),
                new HTMLTag("title", HTMLSelectorEnum.CLASS),
                new UrlTag("a", HTMLSelectorEnum.TAG, "href"),
                new UrlTag("front imgFormat_20_f js-lazy-load flipside", HTMLSelectorEnum.CLASS, "data-original"),
                new HTMLTag("size text-light", HTMLSelectorEnum.CLASS),
                new HTMLTag("fullprice font-bold", HTMLSelectorEnum.CLASS),
                new HTMLTag("oldprice text-linethrough text-light", HTMLSelectorEnum.CLASS),
                new HTMLTag("newprice font-bold", HTMLSelectorEnum.CLASS)
        );
    }

    @Override
    public String getNextPageURISelector() {
        return "page=";
    }
}
