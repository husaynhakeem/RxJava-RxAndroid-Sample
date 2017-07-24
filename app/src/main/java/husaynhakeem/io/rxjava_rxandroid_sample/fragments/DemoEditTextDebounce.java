package husaynhakeem.io.rxjava_rxandroid_sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import husaynhakeem.io.rxjava_rxandroid_sample.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by husaynhakeem on 7/24/17.
 */

public class DemoEditTextDebounce extends Fragment {

    private static final int DEBOUNCE_INTERVAL = 300;

    private Unbinder unbinder;
    private Disposable disposable;

    @BindView(R.id.et_debounce)
    EditText debounceEditText;

    @BindView(R.id.lv_entries)
    ListView entriesListView;

    private ArrayAdapter<String> debounceAdapter;
    private List<String> searchEntries;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.demo_edit_text_debounce, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setUpAdapter();
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposable.dispose();
    }


    private void setUpAdapter() {
        searchEntries = new ArrayList<>();
        debounceAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, searchEntries);
        entriesListView.setAdapter(debounceAdapter);
    }


    @OnClick(R.id.btn_clear_entries)
    public void clearEntriesList() {
        searchEntries.clear();
        debounceAdapter.clear();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DisposableObserver<TextViewTextChangeEvent> observer = new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) {
                addEntry(textViewTextChangeEvent.text().toString());
                Timber.d("Debounce observer adding to list view: %s", textViewTextChangeEvent.text().toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Timber.e("Error in observer: %s" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Timber.d("debounce observer done");
            }
        };

        disposable = RxTextView.textChangeEvents(debounceEditText)
                .debounce(DEBOUNCE_INTERVAL, TimeUnit.MILLISECONDS)
                .filter(changeEvent -> !TextUtils.isEmpty(changeEvent.text().toString()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }


    private void addEntry(String entry) {
        searchEntries.add(0, entry);
        debounceAdapter.notifyDataSetChanged();
    }
}
