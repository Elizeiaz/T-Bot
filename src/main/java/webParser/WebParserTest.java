package webParser;


import com.sun.tools.javac.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WebParserTest {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Document getHtml(String searchUrl) {
        Document htmlPage = null;
        try {
            htmlPage = Jsoup.connect(searchUrl).get();
        } catch (Exception e){
            logger.log(Level.INFO, "Get html error:", e);
        }
        return htmlPage;
    }

    public Elements putInContainer(Document htmlPage, String cssQuery){
        return htmlPage.select(cssQuery);
    }

    public String grepHtmlByClass(Element htmlPage, String className) {
        return htmlPage.getElementsByClass(className).text();
    }

    public String grepHtmlByTag(Element htmlPage, String tagName) {
        return htmlPage.getElementsByTag(tagName).text();
    }

    public String grepHtmlByID(Element htmlPage, String idName) {
        return htmlPage.getElementsByTag(idName).text();
    }

    public String grepHtmlByAttribute(Element htmlPage, String attributeName) {
        return htmlPage.getElementsByAttribute(attributeName).text();
    }
}
