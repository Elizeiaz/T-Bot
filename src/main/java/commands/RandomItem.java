package commands;

import com.sun.tools.javac.Main;
import sites.URIForParse;
import sites.Yoox;
import telegram.ItemConverter;
import webParser.Item;
import webParser.ItemCategoryEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class RandomItem implements Command {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private final Random random = new Random();

    @Override
    public String execute() {
        return getRandomItem();
    }

    public String getRandomItem(){
        List<URIForParse> uris = new ArrayList<>();
        URIForParse uri = new URIForParse(
                "https://www.yoox.com/RU/shoponline?dept=samplesaleman&gender=U&page=1&attributes=%7B%27ctgr%27%3A%5B%27snkrs5%27%5D%7D&season=X&clientabt=SmsMultiChannel_ON%2CSrRecommendations_ON%2CRecentlyViewed_ON%2CRecentlyViewedItemPage_ON%2CmyooxNew_ON%2CImageFormatB_ON%2COnePageCheckout_ON",
                ItemCategoryEnum.SNEAKERS
        );
        uris.add(uri);

        Yoox yoox = new Yoox();
        yoox.parse(uris);
        int randomInt = random.nextInt(yoox.itemCount());
        Item item = yoox.getItem(randomInt);
        ItemConverter itemConverter = new ItemConverter();
        return itemConverter.itemToString(item);
    }
}
