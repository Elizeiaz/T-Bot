package webParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sites.URIForParse;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParser {
    protected abstract String getNextPageURISelector();

    //todo list вынести наружу
    //todo Вторым параметром передать что-то куда записывать item
    abstract public boolean parse(URIForParse uriForParse);

    public Document getHtmlPage(String uri) {
        Document htmlPage = null;
        try {
            htmlPage = Jsoup.connect(uri).referrer("http://www.google.com").
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                            " Chrome/86.0.4240.183 Safari/537.36").get();
        } catch (Exception e) {
            //todo Сделать логгером
            return null;
        }

        return htmlPage;
    }

    private List<Item> itemList = new ArrayList<>();

    public boolean saveItem(Item item) {
        //todo saveItems in db
        if (item != null){
            return itemList.add(item);
        }
        return false;
    }

    public int itemCount() {
        return itemList.size();
    }

    public Item getItem(int itemPos) {
        return itemList.get(itemPos);
    }
}
