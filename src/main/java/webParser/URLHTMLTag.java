package webParser;

public class URLHTMLTag {
    HTMLTag htmlTag;
    String attrName;
    HTMLSelectorEnum selectorEnum;
    String urlAttribName;

    public URLHTMLTag(
            String attrName,
            HTMLSelectorEnum selectorEnum,
            String urlAttribName
    ){
        this.attrName = attrName;
        this.selectorEnum = selectorEnum;
        this.urlAttribName = urlAttribName;
        this.htmlTag = new HTMLTag(this.attrName, this.selectorEnum);
    }

    public HTMLTag getHTMLTag() {
        return htmlTag;
    }

    public String getAttrName() {
        return attrName;
    }

    public HTMLSelectorEnum getSelectorEnum() {
        return selectorEnum;
    }

    public String getUrlAttribName() {
        return urlAttribName;
    }
}
