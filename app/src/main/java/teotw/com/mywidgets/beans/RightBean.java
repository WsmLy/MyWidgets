package teotw.com.mywidgets.beans;

/**
 * created by samwsm at 2020-01-01 15:30
 * update by samwsm at 2020-01-01 15:30
 * updateDetail :
 */
public class RightBean {
    private long id;
    private String title;
    private String image;
    private int itemType;
    private int fakePosition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getFakePosition() {
        return fakePosition;
    }

    public void setFakePosition(int fakePosition) {
        this.fakePosition = fakePosition;
    }
}
