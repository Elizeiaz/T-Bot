package webParser;

import com.sun.tools.javac.Main;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.URIForParse;

import java.util.List;
import java.util.logging.Logger;

public abstract class HtmlParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final ParserSelector parserSelector = setParserSelector();
    private final ItemFactory itemFactory = new ItemFactory();

    abstract public String getSiteName();

    abstract public String getSiteUrl();

    abstract public String setItemContainer();

    abstract public ParserSelector setParserSelector();

    abstract public String setNextPageIdentifyer();

    @Override
    public boolean startParse(List<URIForParse> urisForParse) {
        boolean isParsed = false;
        for (URIForParse uri : urisForParse) {
            while (true) {
                logger.info(uri.getUri());
                logger.info(Integer.toString(itemCount()));
                Document html = getHtmlPage(uri.getUri());
                if (html == null){
                    break;
                }
                boolean curIsParsed = htmlParser(html, uri.getCategory());
                if (!curIsParsed) {
                    break;
                }
                isParsed = true;
                uri.setUri(toNextPage(uri.getUri()));

            }
        }

        return isParsed;
    }

    public boolean htmlParser(Document html, ItemCategoryEnum category) {
        logger.fine("Start parsing " + html.location());

        boolean isItemParsed = false;
        Elements htmlItemContainers = putInContainer(html, setItemContainer());

        if (htmlItemContainers.size() == 0) {
            return false;
        }

        for (Element htmlItemContainer : htmlItemContainers) {
            Item item = parseItem(htmlItemContainer, category);
            //Some important item fields not found
            if (item == null) {
                continue;
            }
            saveItem(item);
            isItemParsed = true;
        }

        return isItemParsed;
    }

    public Item parseItem(Element htmlItemContainer, ItemCategoryEnum category) {
        String brand = parseBrand(htmlItemContainer);
        String itemName = parseItemName(htmlItemContainer);
        String itemUrl = parseItemUrl(htmlItemContainer);
        String photoUrl = parsePhotoUrl(htmlItemContainer);
        String sizes = parseSizes(htmlItemContainer);
        String strPrice = parsePrice(htmlItemContainer);
        String strFullPrice = parseFullPrice(htmlItemContainer);
        String strDiscountPrice = parseDiscountPrice(htmlItemContainer);

        //No price - no item
        if (strPrice.equals("") && strFullPrice.equals("")) {
            return null;
        } else if (strPrice.equals("")) {
            strPrice = strFullPrice;
        }

        //return new item
        return itemFactory.createNewItem(
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
    }

    public String parseBrand(Element htmlItemContainer) {
        return grepHtmlEmenent(htmlItemContainer,
                parserSelector.getBrand().getTagName(),
                parserSelector.getBrand().getHtmlTag());
    }

    public String parseItemName(Element htmlItemContainer) {
        return grepHtmlEmenent(htmlItemContainer,
                parserSelector.getItemName().getTagName(),
                parserSelector.getItemName().getHtmlTag());
    }

    public String parseItemUrl(Element htmlItemContainer) {
        return getSiteUrl() + grepUrl(
                htmlItemContainer,
                parserSelector.getItemUrl().getAttrName(),
                parserSelector.getItemUrl().getSelectorEnum(),
                parserSelector.getItemUrl().getUrlAttribName()
        );
    }

    public String parsePhotoUrl(Element htmlItemContainer) {
        return grepUrl(
                htmlItemContainer,
                parserSelector.getPhotoUrl().getAttrName(),
                parserSelector.getPhotoUrl().getSelectorEnum(),
                parserSelector.getPhotoUrl().getUrlAttribName()
        );
    }

    public String parseSizes(Element htmlItemContainer) {
        return grepHtmlEmenent(htmlItemContainer,
                parserSelector.getSizes().getTagName(),
                parserSelector.getSizes().getHtmlTag()
        );
    }

    public String parsePrice(Element htmlItemContainer) {
        return grepHtmlEmenent(
                htmlItemContainer,
                parserSelector.getPrice().getTagName(),
                parserSelector.getPrice().getHtmlTag()
        );
    }

    public String parseFullPrice(Element htmlItemContainer) {
        return grepHtmlEmenent(
                htmlItemContainer,
                parserSelector.getFullPrice().getTagName(),
                parserSelector.getFullPrice().getHtmlTag()
        );
    }

    public String parseDiscountPrice(Element htmlItemContainer) {
        return grepHtmlEmenent(
                htmlItemContainer,
                parserSelector.getDiscountPrice().getTagName(),
                parserSelector.getDiscountPrice().getHtmlTag()
        );
    }

    public Elements putInContainer(Document htmlPage, String cssQuery) {
        return htmlPage.select(cssQuery);
    }

    public String grepHtmlByClass(Element htmlPage, String className) {
        return htmlPage.getElementsByClass(className).text();
    }

    public String grepHtmlByTag(Element htmlPage, String tagName) {
        return htmlPage.getElementsByTag(tagName).text();
    }

    public String grepHtmlByID(Element htmlPage, String idName) {
        return htmlPage.getElementById(idName).text();
    }

    public String grepHtmlByAttribute(Element htmlPage, String attributeName) {
        return htmlPage.getElementsByAttribute(attributeName).text();
    }

    public String grepHtmlEmenent(Element htmlPage, String elementName, HtmlSelectorEnum htmlSelector) {
        return switch (htmlSelector) {
            case CLASS -> grepHtmlByClass(htmlPage, elementName);
            case ID -> grepHtmlByID(htmlPage, elementName);
            case TAG -> grepHtmlByTag(htmlPage, elementName);
            case ATTRIB -> grepHtmlByAttribute(htmlPage, elementName);
        };
    }

    public String grepUrl(Element htmlPage, String attrName, HtmlSelectorEnum selectorEnum, String urlAttrName) {
        Element element = switch (selectorEnum) {
            case CLASS -> htmlPage.getElementsByClass(attrName).first();
            case ID -> htmlPage.getElementById(attrName);
            case TAG -> htmlPage.getElementsByTag(attrName).first();
            case ATTRIB -> htmlPage.getElementsByAttribute(attrName).first();
        };

        return element == null ? null : element.attr(urlAttrName);
    }
}
