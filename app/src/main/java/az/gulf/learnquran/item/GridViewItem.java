package az.gulf.learnquran.item;

/**
 * Created by www.ucuz.az on 19.01.2016.
 */
public class GridViewItem {

    private String value;
    private String title;

    public GridViewItem(String value, String title) {

        super();

        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GridViewItem{" +
                "value='" + value + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
