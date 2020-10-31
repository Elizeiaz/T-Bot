package webParser;

import com.sun.tools.javac.Main;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.logging.Logger;

public abstract class AbstructSite {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private ParserSelector parserSelector = setParserSelector();
    private HashMap<String, String> urlsDict = urlsForParse();
    private WebParser webParser = new WebParser();

    abstract public String siteUrl();
    abstract public HashMap<String, String> urlsForParse();
    abstract public boolean isJSONParser();
    abstract public String setItemContainer();
    abstract public ParserSelector setParserSelector();
    abstract public String nextPage();





    public boolean startParse(){
        if (isJSONParser()){
            return false;
        } else {
            for (String urlForParse : urlsForParse().keySet()){
                Document html = webParser.getHtml(urlForParse);


            }
        }
    }
}
