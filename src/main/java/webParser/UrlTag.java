package webParser;

public class UrlTag {
    HtmlTag htmlTag;
    String attrName;
    HtmlSelectorEnum selectorEnum;
    String urlAttribName;

    public UrlTag(
            String attrName,
            HtmlSelectorEnum selectorEnum,
            String urlAttribName
    ){
        this.attrName = attrName;
        this.selectorEnum = selectorEnum;
        this.urlAttribName = urlAttribName;
        this.htmlTag = new HtmlTag(this.attrName, this.selectorEnum);
    }

    public HtmlTag getHtmlTag() {
        return htmlTag;
    }

    public String getAttrName() {
        return attrName;
    }

    public HtmlSelectorEnum getSelectorEnum() {
        return selectorEnum;
    }

    public String getUrlAttribName() {
        return urlAttribName;
    }
}
