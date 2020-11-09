package webParser;

public class HTMLTag {
    private String tagName;
    private HTMLSelectorEnum htmlTag;

    public HTMLTag(String tagName, HTMLSelectorEnum htmlTag){
        this.tagName = tagName;
        this.htmlTag = htmlTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public HTMLSelectorEnum getHtmlTag() {
        return htmlTag;
    }

    public void setHtmlTagEnum(HTMLSelectorEnum htmlSelectorEnum) {
        this.htmlTag = htmlSelectorEnum;
    }
}
