package webParser;

public class HtmlTag {
    private String tagName;
    private HtmlSelectorEnum htmlTag;

    public HtmlTag(String tagName, HtmlSelectorEnum htmlTag){
        this.tagName = tagName;
        this.htmlTag = htmlTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public HtmlSelectorEnum getHtmlTag() {
        return htmlTag;
    }

    public void setHtmlTagEnum(HtmlSelectorEnum htmlSelectorEnum) {
        this.htmlTag = htmlSelectorEnum;
    }
}
