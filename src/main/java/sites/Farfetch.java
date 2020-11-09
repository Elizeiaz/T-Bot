package sites;

import webParser.*;

public class Farfetch extends HTMLParser {
    @Override
    public String getSiteName() {
        return "Farfetch";
    }

    @Override
    public String getSiteURI() {
        return "https://www.farfetch.com/";
    }

    @Override
    public String getItemContainerHTMLSelector() {
        return "li[class='_0a5d39 _d85b45']";
    }

    @Override
    public HTMLSelectorsForParse getHTMLSelectorsForParse() {
        return new HTMLSelectorsForParse(
                new HTMLTag("_346238", HTMLSelectorEnum.CLASS),
                new HTMLTag("_d85b45", HTMLSelectorEnum.CLASS),
                new UrlTag("_d85b45", HTMLSelectorEnum.CLASS, "href"),
                new UrlTag("_d85b45", HTMLSelectorEnum.CLASS, "src"),
                new HTMLTag("_5cf853", HTMLSelectorEnum.CLASS),
                new HTMLTag("_5cf853", HTMLSelectorEnum.CLASS),
                new HTMLTag("_ad18db", HTMLSelectorEnum.CLASS),
                new HTMLTag("_ad18db", HTMLSelectorEnum.CLASS)
        );
    }

    @Override
    public String getNextPageURISelector() {
        return null;
    }
}
