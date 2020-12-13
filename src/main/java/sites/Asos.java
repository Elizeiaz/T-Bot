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
        return new JSONSelectorsForParse();
    }

    @Override
    public String getNextPageURISelector() {
        return "page=";
    }
}