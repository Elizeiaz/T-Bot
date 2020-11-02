package webParser;

import com.sun.tools.javac.Main;
import core.JSONHandler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstructSite {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final ParserSelector parserSelector = setParserSelector();
    private final HashMap<String, CategoryEnum> urlsDict;
    private final WebParser webParser = new WebParser();
    private final ItemFactory itemFactory = new ItemFactory();
    private final Pattern sizesRegExp = Pattern.compile("\\d+");

    public AbstructSite(HashMap<String, CategoryEnum> urlsForParse) {
        this.urlsDict = urlsForParse;
    }

    private List<Item> itemList = new ArrayList<>();

    abstract public String getSiteName();

    abstract public String getSiteUrl();

    abstract public boolean isJSONParser();

    abstract public String setItemContainer();

    abstract public ParserSelector setParserSelector();

    abstract public String setNextPageIdentifyer();


    public String toNextPage(String url) {

        try {
            String identifyer = setNextPageIdentifyer();

            int index = url.indexOf(identifyer);
            if (index == -1) {
                return null;
            }

            String headUrl = url.substring(0, index + identifyer.length());

            Matcher matcher = sizesRegExp.matcher(url);
            int nextPage = 1;
            if (matcher.find(index + identifyer.length())){
                nextPage = Integer.parseInt(matcher.group()) + 1;
            }

            String tailUrl = url.substring(index + identifyer.length() + String.valueOf(nextPage).length());

            return headUrl + String.valueOf(nextPage) + tailUrl;

        } catch (Exception e) {
            return null;
        }
    }



    public int startParse() {
        int newItemsCount = 0;

        for (String urlForParse : urlsDict.keySet()) {
                Document html = webParser.getHtml(urlForParse);
                if (html == null) {
                    continue;
                }

                int currentNewItemsCount;
                if (isJSONParser()) {
                    currentNewItemsCount = jsonParser(html, urlsDict.get(urlForParse));
                } else {
                    currentNewItemsCount = htmlParser(html, urlsDict.get(urlForParse));
                }

            //TODO ссылки меняются, но не сождержимое
//            while (true) {
//                logger.info(urlForParse);
//                Document html = webParser.getHtml(urlForParse);
//                logger.info(String.valueOf(html.text()));
//                if (html == null) {
//                    continue;
//                }
//
//                int currentNewItemsCount;
//                if (isJSONParser()) {
//                    currentNewItemsCount = jsonParser(html, urlsDict.get(urlForParse));
//                } else {
//                    currentNewItemsCount = htmlParser(html, urlsDict.get(urlForParse));
//                }
//                if (currentNewItemsCount == 0) {
//                    continue;
//                }
//                newItemsCount += currentNewItemsCount;
//                urlForParse = toNextPage(urlForParse);
//            }
        }

        return newItemsCount;
    }

    public int jsonParser(Document html, CategoryEnum category) {
        return 0;
    }

    public int htmlParser(Document html, CategoryEnum category) {
        int countNewItems = 0;
        Elements itemContainers = webParser.putInContainer(html, setItemContainer());

        if (itemContainers.size() == 0) {
            return 0;
        }

        for (Element itemContainer : itemContainers) {
            String brand = webParser.grepHtmlEmenent(itemContainer,
                    parserSelector.getBrand().getTagName(),
                    parserSelector.getBrand().getHtmlTag());

            String itemName = webParser.grepHtmlEmenent(itemContainer,
                    parserSelector.getItemName().getTagName(),
                    parserSelector.getItemName().getHtmlTag());

            String itemUrl = getSiteUrl() + webParser.grepUrl(
                    itemContainer,
                    parserSelector.getItemUrl().getAttrName(),
                    parserSelector.getItemUrl().getSelectorEnum(),
                    parserSelector.getItemUrl().getUrlAttribName()
            );

            String photoUrl = webParser.grepUrl(
                    itemContainer,
                    parserSelector.getPhotoUrl().getAttrName(),
                    parserSelector.getPhotoUrl().getSelectorEnum(),
                    parserSelector.getPhotoUrl().getUrlAttribName()
            );

            String sizes = webParser.grepHtmlEmenent(itemContainer,
                    parserSelector.getSizes().getTagName(),
                    parserSelector.getSizes().getHtmlTag()
            );

            String strPrice = webParser.grepHtmlEmenent(
                    itemContainer,
                    parserSelector.getPrice().getTagName(),
                    parserSelector.getPrice().getHtmlTag()
            );

            String strFullPrice = webParser.grepHtmlEmenent(
                    itemContainer,
                    parserSelector.getFullPrice().getTagName(),
                    parserSelector.getFullPrice().getHtmlTag()
            );


            //No price - no item
            if (strPrice.equals("") && strFullPrice.equals("")) {
                continue;
            } else if (strPrice.equals("")) {
                strPrice = strFullPrice;
            }

            String strDiscountPrice = webParser.grepHtmlEmenent(
                    itemContainer,
                    parserSelector.getDiscountPrice().getTagName(),
                    parserSelector.getDiscountPrice().getHtmlTag()
            );

            //create item
            Item item = itemFactory.createNewItem(
                    getSiteName(),
                    getSiteUrl(),
                    category,
                    brand,
                    itemName,
                    itemUrl,
                    photoUrl,
                    sizes,
                    strPrice,
                    strDiscountPrice
            );
            saveItem(item);
            countNewItems++;
        }

        return countNewItems;
    }

    private boolean saveItem(Item item) {
        //todo saveItems in db
        return itemList.add(item);
    }

    public List<String> itemsToJSON() {
        JSONHandler jsonHandler = new JSONHandler();
        List<String> jsonList = new ArrayList<>();

        for (Item item : itemList) {
            jsonList.add(jsonHandler.objectToJson(item));
        }

        return jsonList;
    }

    public int itemCount() {
        return itemList.size();
    }

    public Item getItem(int itemPos) {
        return itemList.get(itemPos);
    }
}
