package husaynhakeem.io.rxjava_rxandroid_sample.sections;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import husaynhakeem.io.rxjava_rxandroid_sample.R;
import husaynhakeem.io.rxjava_rxandroid_sample.fragments.DemoEditTextDebounce;
import husaynhakeem.io.rxjava_rxandroid_sample.fragments.DemoTextViewDoubleBinding;

/**
 * Created by husaynhakeem on 7/25/17.
 */

public class SectionHandler {


    private static final int INDEX_DEMO_DEBOUNCE = 0;
    private static final int INDEX_DEMO_DOUBLE_BINDING = 1;


    public static List<Section> getListOfSections(Context context) {
        String[] titles = context.getResources().getStringArray(R.array.titles);
        String[] subTitles = context.getResources().getStringArray(R.array.subTitles);

        List<Section> sections = new ArrayList<>();
        for (int i = 0; i < titles.length; i++)
            sections.add(new Section(titles[i], subTitles[i]));

        return sections;
    }


    public static Fragment matchFragment(int position) {
        switch (position) {
            case INDEX_DEMO_DEBOUNCE:
                return new DemoEditTextDebounce();

            case INDEX_DEMO_DOUBLE_BINDING:
                return new DemoTextViewDoubleBinding();

            default:
                throw new RuntimeException("Error in matching clicked on section with corresponding Fragment");
        }
    }
}
