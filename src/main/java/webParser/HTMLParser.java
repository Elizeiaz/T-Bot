package webParser;

import com.sun.tools.javac.Main;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.URIForParse;

import java.util.List;
import java.util.logging.Logger;

public abstract class HTMLParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final HTMLSelectorsForParse htmlSelectorsForParse = getHTMLSelectorsForParse();
    private final ItemFactory itemFactory = new ItemFactory();

    abstract public String getSiteName();

    abstract public String getSiteURI();

    abstract public String getItemContainerHTMLSelector();

    abstract public HTMLSelectorsForParse getHTMLSelectorsForParse();

    abstract public String getNextPageURISelector();

    @Override
    public boolean parse(List<URIForParse> urisForParse) {
        boolean isParsed = false;
        for (URIForParse uri : urisForParse) {
            while (true) {
                Document html = getHtmlPage(uri.getUri());
                if (html == null) {
                    break;
                }
                boolean curIsParsed = parseHTML(html, uri.getCategory());
                if (!curIsParsed) {
                    break;
                }
                isParsed = true;
                uri.setUri(toNextPage(uri.getUri()));
            }
        }

        return isParsed;
    }

    private boolean parseHTML(Document html, ItemCategoryEnum category) {
        logger.fine("Start parsing " + html.location());

        boolean isItemParsed = false;
        Elements htmlItemContainers = putInContainer(html, getItemContainerHTMLSelector());

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

    private Item parseItem(Element htmlItemContainer, ItemCategoryEnum category) {
        String brand = parseBrand(htmlItemContainer);
        String itemModel = parseItemModel(htmlItemContainer);
        String itemURL = parseItemUrl(htmlItemContainer);
        String photoURL = parsePhotoUrl(htmlItemContainer);
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
                getSiteURI(),
                category,
                brand,
                itemModel,
                itemURL,
                photoURL,
                sizes,
                strPrice,
                strDiscountPrice
        );
    }


    private String parseBrand(Element htmlItemContainer) {
        return grepHTMLEmenent(
                htmlItemContainer,
                htmlSelectorsForParse.getBrand().getTagName(),
                htmlSelectorsForParse.getBrand().getHtmlTag()
        );
    }

    private String parseItemModel(Element htmlItemContainer) {
        return grepHTMLEmenent(htmlItemContainer,
                htmlSelectorsForParse.getItemModel().getTagName(),
                htmlSelectorsForParse.getItemModel().getHtmlTag());
    }

    private String parseItemUrl(Element htmlItemContainer) {
        return getSiteURI().substring(0, getSiteURI().length() - 1) + grepUrl(
                htmlItemContainer,
                htmlSelectorsForParse.getItemURL().getAttrName(),
                htmlSelectorsForParse.getItemURL().getSelectorEnum(),
                htmlSelectorsForParse.getItemURL().getUrlAttribName()
        );
    }

    private String parsePhotoUrl(Element htmlItemContainer) {
        return grepUrl(
                htmlItemContainer,
                htmlSelectorsForParse.getPhotoURL().getAttrName(),
                htmlSelectorsForParse.getPhotoURL().getSelectorEnum(),
                htmlSelectorsForParse.getPhotoURL().getUrlAttribName()
        );
    }

    private String parseSizes(Element htmlItemContainer) {
        return grepHTMLEmenent(htmlItemContainer,
                htmlSelectorsForParse.getSizes().getTagName(),
                htmlSelectorsForParse.getSizes().getHtmlTag()
        );
    }

    private String parsePrice(Element htmlItemContainer) {
        return grepHTMLEmenent(
                htmlItemContainer,
                htmlSelectorsForParse.getPrice().getTagName(),
                htmlSelectorsForParse.getPrice().getHtmlTag()
        );
    }

    private String parseFullPrice(Element htmlItemContainer) {
        return grepHTMLEmenent(
                htmlItemContainer,
                htmlSelectorsForParse.getFullPrice().getTagName(),
                htmlSelectorsForParse.getFullPrice().getHtmlTag()
        );
    }

    private String parseDiscountPrice(Element htmlItemContainer) {
        return grepHTMLEmenent(
                htmlItemContainer,
                htmlSelectorsForParse.getDiscountPrice().getTagName(),
                htmlSelectorsForParse.getDiscountPrice().getHtmlTag()
        );
    }

    private Elements putInContainer(Document htmlPage, String cssQuerySelector) {
        return htmlPage.select(cssQuerySelector);
    }

    private String grepHTMLByClass(Element htmlPage, String className) {
        return htmlPage.getElementsByClass(className).text();
    }

    private String grepHTMLByTag(Element htmlPage, String tagName) {
        return htmlPage.getElementsByTag(tagName).text();
    }

    private String grepHTMLByID(Element htmlPage, String idName) {
        return htmlPage.getElementById(idName).text();
    }

    private String grepHTMLByAttribute(Element htmlPage, String attributeName) {
        return htmlPage.getElementsByAttribute(attributeName).text();
    }

    private String grepHTMLEmenent(Element htmlPage, String elementName, HTMLSelectorEnum htmlSelector) {
        return switch (htmlSelector) {
            case CLASS -> grepHTMLByClass(htmlPage, elementName);
            case ID -> grepHTMLByID(htmlPage, elementName);
            case TAG -> grepHTMLByTag(htmlPage, elementName);
            case ATTRIB -> grepHTMLByAttribute(htmlPage, elementName);
        };
    }

    private String grepUrl(Element htmlPage, String attrName, HTMLSelectorEnum selectorEnum, String urlAttrName) {
        Element element = switch (selectorEnum) {
            case CLASS -> htmlPage.getElementsByClass(attrName).first();
            case ID -> htmlPage.getElementById(attrName);
            case TAG -> htmlPage.getElementsByTag(attrName).first();
            case ATTRIB -> htmlPage.getElementsByAttribute(attrName).first();
        };

        return element == null ? null : element.attr(urlAttrName);
    }
}
