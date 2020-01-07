package teotw.com.mywidgets.beans;

/**
 * created by samwsm at 2020-01-01 15:27
 * update by samwsm at 2020-01-01 15:27
 * updateDetail :
 */
public class LeftBean {
    private long id;
    private String title;
    private boolean isSelected;

    public LeftBean(long id, String title) {
        this.id = id;
        this.title = title;
        this.isSelected = false;
    }

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
