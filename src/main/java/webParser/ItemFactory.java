package webParser;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFactory {
    Pattern numberRegExp = Pattern.compile("\\d*[., ]?\\d*");
    Pattern sizesRegExp = Pattern.compile("\\d*[.,]?\\d");

    public Item createNewItem(
            String siteName,
            String siteUrl,
            ItemCategoryEnum category,
            String brand,
            String itemName,
            String itemUrl,
            String photoUrl,
            String sizes,
            String strPrice,
            String strDiscountPrice
    ) {
        if (brand.equals("")) {
            brand = "BRAND";
        }

        if (itemName.equals("")) {
            itemName = brand;
        }

        List<Float> sizesList = new ArrayList<>();
        Matcher matcherForSizes = sizesRegExp.matcher(sizes);

        while (matcherForSizes.find()) {
            Float floatSize = Float.valueOf(matcherForSizes.group());
            if (!sizesList.contains(floatSize)) {
                sizesList.add(floatSize);
            }
        }


        Matcher matcherForPrice = numberRegExp.matcher(strPrice);

        int intPrice = 0;
        if (matcherForPrice.find()) {
            intPrice = Integer.parseInt(matcherForPrice.group().replaceAll("[ ,.]", ""));
        }
        if (intPrice == 0) {
            return null;
        }

        Matcher matcherForDiscountPrice = numberRegExp.matcher(strDiscountPrice);

        int intDiscountPrice = 0;
        if (matcherForDiscountPrice.find()) {
            if (!matcherForDiscountPrice.group().equals("")){
                intDiscountPrice = Integer.parseInt(matcherForDiscountPrice.group().
                        replaceAll("[ ,.]", ""));
            }
        }

        return new Item(
                siteName,
                siteUrl,
                category,
                brand,
                itemName,
                itemUrl,
                photoUrl,
                sizesList,
                intPrice,
                intDiscountPrice
        );
    }
}
