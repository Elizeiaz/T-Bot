package webParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser {
    abstract String setNextPageIdentifyer();

    abstract public boolean startParse();

    public Document getHtmlPage(String uri) {
        Document htmlPage = null;
        try {
            htmlPage = Jsoup.connect(uri).get();
        } catch (Exception e) {
            return null;
        }

        return htmlPage;
    }

    private final Pattern numberRegExp = Pattern.compile("\\d+");

    public String toNextPage(String uri) {

        try {
            String identifyer = setNextPageIdentifyer();

            int index = uri.indexOf(identifyer);
            if (index == -1) {
                return null;
            }

            String headUrl = uri.substring(0, index + identifyer.length() - 1);

            Matcher matcher = numberRegExp.matcher(uri);
            int nextPage = 1;
            if (matcher.find(index + identifyer.length())) {
                nextPage = Integer.parseInt(matcher.group()) + 1;
            }

            String tailUrl = uri.substring(index + identifyer.length() + String.valueOf(nextPage).length());

            return headUrl + nextPage + tailUrl;

        } catch (Exception e) {
            return null;
        }
    }


//        int newItemsCount = 0;
//
//        for (String urlForParse : urlsDict.keySet()) {
//                Document html = getHtmlPage(urlForParse);
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
//
//            TODO ссылки меняются, но не сождержимое
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
//
//
//        return newItemsCount;
//    }

//    public int jsonParser(Document html, ItemCategoryEnum category) {
//        return 0;
//    }


    private List<Item> itemList = new ArrayList<>();

    public boolean saveItem(Item item) {
        //todo saveItems in db
        return itemList.add(item);
    }

    public int itemCount() {
        return itemList.size();
    }

    public Item getItem(int itemPos) {
        return itemList.get(itemPos);
    }
}
