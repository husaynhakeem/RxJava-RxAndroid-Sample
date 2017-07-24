package husaynhakeem.io.rxjava_rxandroid_sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import husaynhakeem.io.rxjava_rxandroid_sample.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by husaynhakeem on 7/24/17.
 */

public class DemoTextViewDoubleBinding extends Fragment {


    private Unbinder unbinder;
    private PublishProcessor<String> publishProcessor;
    Disposable disposable;

    @BindView(R.id.et_double_binding_a)
    EditText a_doubleBindingEditText;

    @BindView(R.id.et_double_binding_b)
    EditText b_doubleBindingEditText;

    @BindView(R.id.tv_double_binding_result)
    TextView doubleBindingResultTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.demo_text_view_double_binding, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        publishProcessor = PublishProcessor.create();
        disposable = publishProcessor.subscribe(result -> doubleBindingResultTextView.setText(result));
    }


    @OnTextChanged({R.id.et_double_binding_a, R.id.et_double_binding_b})
    public void onValuesChanged() {

        String a_string = a_doubleBindingEditText.getText().toString();
        String b_string = b_doubleBindingEditText.getText().toString();

        if (TextUtils.isEmpty(a_string) || TextUtils.isEmpty(b_string))
            return;

        double a;
        double b;

        a = Double.parseDouble(a_string);
        b = Double.parseDouble(b_string);

        resultFrom(a, b);
    }


    private void resultFrom(double a, double b) {

        if (a == 0)
            publishProcessor.onNext(getString(R.string.double_binding_division_on_zero));
        publishProcessor.onNext(String.valueOf((-b) / a));
    }


    private boolean isEmpty(String value) {
        return TextUtils.isEmpty(String.valueOf(value));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposable.dispose();
    }
}
