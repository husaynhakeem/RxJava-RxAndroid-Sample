package husaynhakeem.io.rxjava_rxandroid_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import husaynhakeem.io.rxjava_rxandroid_sample.fragments.DemoEditTextDebounce;
import husaynhakeem.io.rxjava_rxandroid_sample.fragments.DemoTextViewDoubleBinding;

/**
 * Created by husaynhakeem on 7/24/17.
 */

public class MainFragment extends Fragment {


    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick(R.id.btn_edit_text_debounce)
    public void debounceDemo() {
        switchFragment(new DemoEditTextDebounce());
    }


    @OnClick(R.id.btn_text_view_double_binding)
    public void doubleBindingDemo() {
        switchFragment(new DemoTextViewDoubleBinding());
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
