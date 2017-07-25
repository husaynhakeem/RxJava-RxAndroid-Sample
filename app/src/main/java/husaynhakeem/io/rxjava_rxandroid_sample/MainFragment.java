package husaynhakeem.io.rxjava_rxandroid_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import husaynhakeem.io.rxjava_rxandroid_sample.sections.SectionAdapter;
import husaynhakeem.io.rxjava_rxandroid_sample.sections.SectionHandler;

/**
 * Created by husaynhakeem on 7/24/17.
 */

public class MainFragment extends Fragment {


    private Unbinder unbinder;

    @BindView(R.id.lv_sections)
    ListView sectionsListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setUpSections();
        return rootView;
    }


    private void setUpSections() {
        sectionsListView.setAdapter(new SectionAdapter(getContext(), R.layout.item_section, SectionHandler.getListOfSections(getContext())));
        sectionsListView.setOnItemClickListener( (parent, view, position, id) -> switchFragment(SectionHandler.matchFragment(position)));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void switchFragment(Fragment fragment) {
        String fragmentTag = fragment.getClass().getName();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragmentTag)
                .replace(R.id.content, fragment)
                .commit();
    }
}
