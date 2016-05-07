package az.gulf.learnquran.domain;

/**
 * Created by www.ucuz.az on 24.01.2016.
 */
public class ViewPagerObject {

    private String text;
    private String descriptionText;
    private int playId;

    public ViewPagerObject() {
    }

    public ViewPagerObject(String text, String descriptionText, int playId) {
        this.text = text;
        this.descriptionText = descriptionText;
        this.playId = playId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    @Override
    public String toString() {
        return "ViewPagerObject{" +
                "text='" + text + '\'' +
                ", descriptionText='" + descriptionText + '\'' +
                ", playId=" + playId +
                '}';
    }
}
