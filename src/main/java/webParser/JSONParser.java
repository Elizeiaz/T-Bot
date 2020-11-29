package webParser;


import com.sun.tools.javac.Main;
import core.JSONHandler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.URIForParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class JSONParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private final ItemFactory itemFactory = new ItemFactory();

    abstract public String getSiteName();

    abstract public String getSiteURI();

    abstract public String getStartStringWithJSON();

    abstract public String getNameOfItemsContainer();

    abstract public JSONSelectorsForParse getJSONSelectorsForParse();

    @Override
    public boolean parse(List<URIForParse> urisForParse) {
        boolean isParsed = false;
        for (URIForParse uri : urisForParse) {
            while (true) {
                Document html = getHtmlPage(uri.getUri());
                if (html == null) {
                    break;
                }
                boolean curIsParsed = parseHTMLWithJSON(html, uri.getCategory());
                if (!curIsParsed) {
                    break;
                }
                isParsed = true;
                uri.setUri(toNextPage(uri.getUri()));
            }
        }

        return isParsed;
    }

    public boolean parseHTMLWithJSON(Document html, ItemCategoryEnum category) {
        Element elementWithAllJSON = selectTagWithJSONString(
                html, "script", getStartStringWithJSON());
        if (elementWithAllJSON == null) return false;

        String jsonWithItems = grepJSONStringWithItems(elementWithAllJSON, getNameOfItemsContainer());
        List<String> stringsOfItems = putInContainer(jsonWithItems);

        for (String stringOfItem : stringsOfItems) {
            HashMap<String, String> itemDict = converteJSONStringToDict(stringOfItem);
            logger.info(itemDict.toString());
            Item item = parseItem(itemDict, category);
//            saveItem(item);
        }
        return false;
    }

    public Item parseItem(HashMap<String, String> itemDict, ItemCategoryEnum category) {
        String strPrice = "0";
        String strDiscountPrice = "0";

        return itemFactory.createNewItem(
                getSiteName(),
                getSiteURI(),
                category,
                itemDict.get(getJSONSelectorsForParse().getBrand()),
                itemDict.get(getJSONSelectorsForParse().getItemModel()),
                itemDict.get(getJSONSelectorsForParse().getItemURL()),
                itemDict.get(getJSONSelectorsForParse().getPhotoURL()),
                itemDict.get(getJSONSelectorsForParse().getSizes()),
                itemDict.get("strPrice"),
                itemDict.get("strDiscountPrice")
        );
    }

    public HashMap<String, String> converteJSONStringToDict(String item) {
        JSONHandler jsonHandler = new JSONHandler();
        return jsonHandler.readJSON(item);
    }

    public List<String> putInContainer(String jsonWithItems) {
        List<String> items = new ArrayList<>();
        Pattern sizesRegExp = Pattern.compile("\\{.+?}");
        Matcher matcher = sizesRegExp.matcher(jsonWithItems);
        while (matcher.find()) {
            items.add(matcher.group());
        }
        return items;
    }

    public String grepJSONStringWithItems(Element element, String jsonSelector) {
        String elementData = element.toString();
        int startIndex = elementData.indexOf(jsonSelector);
        if (startIndex == -1) return null;
        int endIndex = elementData.indexOf("]", startIndex);
        return elementData.substring(startIndex, endIndex + 1);
    }

    public Element selectTagWithJSONString(Document htmlPage, String tag, String startStringWithJSON) {
        Elements elements = htmlPage.getElementsByTag(tag);
        for (Element element : elements) {
            if (element.data().contains(startStringWithJSON)) return element;
        }
        return null;
    }
}
