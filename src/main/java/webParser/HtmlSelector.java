package webParser;

public class HtmlSelector {
    private String tagName;
    private HtmlSelectorEnum htmlTag;

    public HtmlSelector(String tagName, HtmlSelectorEnum htmlTag){
        this.tagName = tagName;
        this.htmlTag = htmlTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public HtmlSelectorEnum getHtmlTagEnum() {
        return htmlTag;
    }

    public void setHtmlTagEnum(HtmlSelectorEnum htmlSelectorEnum) {
        this.htmlTag = htmlSelectorEnum;
    }
}
