package sites;

import org.jsoup.nodes.Document;
import webParser.ItemCategoryEnum;
import webParser.JSONParser;
import webParser.JSONSelectorsForParse;

public class Asos extends JSONParser {
    @Override
    public String getSiteName() {
        return "Asos";
    }

    @Override
    public String getSiteURI() {
        return "https://www.asos.com/";
    }

    @Override
    public String getStartStringWithJSON() {
        return "asos=window.asos||{};window.asos.plp={};";
    }

    @Override
    public String getNameOfItemsContainer() {
        return "\"products\":";
    }

    @Override
    public JSONSelectorsForParse getJSONSelectorsForParse() {
        return null;
//        return new JSONSelectorsForParse();
    }

    @Override
    public String getNextPageURISelector() {
        return "page=";
    }

    public String getHtml() {
        Document doc = getHtmlPage(
                "https://www.asos.com/ru/men/tufli-botinki-i-krossovki/krossovki/cat/?cid=5775&nlid=mw|%D0%BE%D0%B1%D1%83%D0%B2%D1%8C|%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D1%8C+%D0%BF%D0%BE+%D1%82%D0%B8%D0%BF%D1%83+%D0%BF%D1%80%D0%BE%D0%B4%D1%83%D0%BA%D1%82%D0%B0");
        parseHTMLWithJSON(doc, ItemCategoryEnum.PERFUME);
        return "";
    }
}