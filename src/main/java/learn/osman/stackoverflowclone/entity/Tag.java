package learn.osman.stackoverflowclone.entity;

public class Tag {
    private String tagName;
    private String tagDetails;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDetails() {
        return tagDetails;
    }

    public void setTagDetails(String tagDetails) {
        this.tagDetails = tagDetails;
    }

    public Tag(String tagName, String tagDetails) {
        this.tagName = tagName;
        this.tagDetails = tagDetails;
    }

}
