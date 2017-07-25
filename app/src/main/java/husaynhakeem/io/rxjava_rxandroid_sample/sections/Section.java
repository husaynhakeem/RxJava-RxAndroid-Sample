package husaynhakeem.io.rxjava_rxandroid_sample.sections;

/**
 * Created by husaynhakeem on 7/25/17.
 */

public class Section {

    private String title;
    private String subTitle;

    public Section() {
    }

    public Section(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
