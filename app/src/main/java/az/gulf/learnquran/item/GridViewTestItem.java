package az.gulf.learnquran.item;

/**
 * Created by www.ucuz.az on 19.01.2016.
 */
public class GridViewTestItem {

    private String answer;
    private int imageId;
    private String isTrueAnswer;

    public GridViewTestItem(String answer, int imageId, String isTrueAnswer) {
        this.answer = answer;
        this.imageId = imageId;
        this.isTrueAnswer = isTrueAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIsTrueAnswer() {
        return isTrueAnswer;
    }

    public void setIsTrueAnswer(String isTrueAnswer) {
        this.isTrueAnswer = isTrueAnswer;
    }

    @Override
    public String toString() {
        return "GridViewTestItem{" +
                "answer='" + answer + '\'' +
                ", imageId=" + imageId +
                ", isTrueAnswer=" + isTrueAnswer +
                '}';
    }
}
