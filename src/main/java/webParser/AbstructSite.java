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

    private ParserSelector parserSelector = setParserSelector();
    private HashMap<String, CategoryEnum> urlsDict = getUrlsForParse();
    private WebParser webParser = new WebParser();
    Pattern numberRegExp = Pattern.compile("\\d*[., ]\\d*");
    Pattern sizesRegExp = Pattern.compile("\\d*[.,]\\d*");

    private List<Item> itemList = new ArrayList<>();

    abstract public String getSiteName();

    abstract public String getSiteUrl();

    abstract public HashMap<String, CategoryEnum> getUrlsForParse();

    abstract public boolean isJSONParser();

    abstract public String setItemContainer();

    abstract public ParserSelector setParserSelector();

    abstract public String getNextPage();


    public boolean startParse() {
        if (isJSONParser()) {
            return jsonParser();
        } else {
            htmlParser();
            for (String item : itemsToJSON()){
                logger.info(item);
            }
            return false;
        }
    }

    public boolean jsonParser() {
        return false;
    }

    public boolean htmlParser() {
        for (String urlForParse : getUrlsForParse().keySet()) {
            Document html = webParser.getHtml(urlForParse);
            if (html == null) {
                continue;
            }

            Elements itemContainers = webParser.putInContainer(html, setItemContainer());
            for (Element itemContainer : itemContainers) {
                CategoryEnum category = getUrlsForParse().get(urlForParse);

                String brand = webParser.grepHtmlEmenent(itemContainer,
                        parserSelector.getBrand().getTagName(),
                        parserSelector.getBrand().getHtmlTag());
                if (brand.equals("")) {
                    brand = "BRAND";
                }

                String itemName = webParser.grepHtmlEmenent(itemContainer,
                        parserSelector.getItemName().getTagName(),
                        parserSelector.getItemName().getHtmlTag());
                if (itemName.equals("")) {
                    itemName = brand;
                }

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

                //grep sizes
                List<Float> sizes = new ArrayList<>();
                Matcher matcherForSizes = sizesRegExp.matcher(
                        webParser.grepHtmlEmenent(itemContainer,
                                parserSelector.getSizes().getTagName(),
                                parserSelector.getSizes().getHtmlTag())
                );

                while (matcherForSizes.find()) {
                    sizes.add(Float.valueOf(matcherForSizes.group()));
                }

                //grep Price
                Matcher matcherForPrice = numberRegExp.matcher(
                        webParser.grepHtmlEmenent(itemContainer,
                                parserSelector.getPrice().getTagName(),
                                parserSelector.getPrice().getHtmlTag())
                );


                int price = 0;
                if (matcherForPrice.find()) {
                    price = Integer.parseInt(matcherForPrice.group().replaceAll("[ ,.]", ""));
                }

                //grep fullPrice
                Matcher matcherForFullPrice = numberRegExp.matcher(
                        webParser.grepHtmlEmenent(itemContainer,
                                parserSelector.getFullPrice().getTagName(),
                                parserSelector.getFullPrice().getHtmlTag())
                );


                int fullPrice = 0;
                if (matcherForFullPrice.find()) {
                    price = Integer.parseInt(matcherForFullPrice.group().replaceAll("[ ,.]", ""));
                }

                //No price - no item
                if (price == 0 && fullPrice == 0){
                    continue;
                } else if (price == 0){
                    price = fullPrice;
                }

                //grep discount price
                Matcher matcherForDiscountPrice = numberRegExp.matcher(
                        webParser.grepHtmlEmenent(itemContainer,
                                parserSelector.getDiscountPrice().getTagName(),
                                parserSelector.getDiscountPrice().getHtmlTag()));

                int discountPrice = price;
                if (matcherForDiscountPrice.find()) {
                    discountPrice = Integer.parseInt(matcherForDiscountPrice.group().replaceAll("[ ,.]", ""));
                }

                //create item
                Item item = new Item(
                        getSiteName(),
                        getSiteUrl(),
                        category,
                        brand,
                        itemName,
                        itemUrl,
                        photoUrl,
                        sizes,
                        price,
                        discountPrice
                );

                saveItem(item);
            }
        }
        return false;
    }

    private boolean saveItem(Item item) {
        //todo saveItems in db
        return itemList.add(item);
    }

    public List<String> itemsToJSON(){
        JSONHandler jsonHandler = new JSONHandler();
        List<String> jsonList = new ArrayList<>();

        for (Item item : itemList){

            jsonList.add(jsonHandler.objectToJson(item));
        }

        return jsonList;
    }
}
