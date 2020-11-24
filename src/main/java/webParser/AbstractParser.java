package webParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sites.URIForParse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser {
    protected abstract String getNextPageURISelector();

    abstract public boolean startParse(List<URIForParse> urisForParse);

    public Document getHtmlPage(String uri) {
        Document htmlPage = null;
        try {
            htmlPage = Jsoup.connect(uri).referrer("http://www.google.com").
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                            " Chrome/86.0.4240.183 Safari/537.36").get();
        } catch (Exception e) {
            return null;
        }

        return htmlPage;
    }

    private final Pattern numberRegExp = Pattern.compile("\\d+");
    public String toNextPage(String uri) {
        try {
            String identifyer = getNextPageURISelector();

            int index = uri.indexOf(identifyer);
            if (index == -1) {
                return null;
            }

            String headUrl = uri.substring(0, index + identifyer.length());
            String tailUrl = "";
            Matcher matcher = numberRegExp.matcher(uri);
            int nextPage = 1;
            if (matcher.find(index + identifyer.length())) {
                tailUrl = uri.substring(index + identifyer.length() + matcher.group().length());
                nextPage = Integer.parseInt(matcher.group()) + 1;
            }
            return headUrl + nextPage + tailUrl;

        } catch (Exception e) {
            return null;
        }
    }


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
