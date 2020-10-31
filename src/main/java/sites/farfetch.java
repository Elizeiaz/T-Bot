package sites;

import webParser.*;

import java.util.List;

public class farfetch extends AbstructSite {
    @Override
    public String siteUrl() {
        return null;
    }

    @Override
    public List<String> urlsForParse() {
        return null;
    }

    @Override
    public boolean isJSONParser() {
        return false;
    }

    @Override
    public String setItemContainer() {
        return null;
    }

    @Override
    public ParserSelector setParserSelector() {
        return new ParserSelector(
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS),
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS),
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS),
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS),
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS),
                new HtmlSelector("brand", HtmlSelectorEnum.CLASS)
        );
    }

    @Override
    public String nextPage() {
        return null;
    }
}
