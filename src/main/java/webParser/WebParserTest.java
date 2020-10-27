package webParser;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.tools.javac.Main;

import javax.xml.xpath.XPathExpression;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class WebParserTest {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    WebClient webClient = new WebClient();

    public HtmlPage getHtmlXml(String searchUrl) {
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = null;

        try {
            htmlPage = webClient.getPage(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlPage;
    }


    public void grepHtmlByClass(HtmlPage htmlPage, String xPathExpr) {

        logger.info(htmlPage.asXml());
        logger.info(htmlPage.getByXPath("//div[@class='result-info']").toString());
//        List<HtmlElement> items = (List<HtmlElement>) htmlPage.getByXPath(xPathExpr);


    }
}
