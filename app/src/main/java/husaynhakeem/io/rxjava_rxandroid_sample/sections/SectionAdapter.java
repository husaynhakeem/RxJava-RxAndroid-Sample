package husaynhakeem.io.rxjava_rxandroid_sample.sections;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import husaynhakeem.io.rxjava_rxandroid_sample.R;

/**
 * Created by husaynhakeem on 7/25/17.
 */

public class SectionAdapter extends ArrayAdapter<Section> {


    private List<Section> sections;


    public SectionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Section> sections) {
        super(context, resource, sections);
        this.sections = sections;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);

        Section section = sections.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.tv_section_title);
        titleTextView.setText(section.getTitle());

        TextView subTitleTextView = (TextView) convertView.findViewById(R.id.tv_section_sub_title);
        subTitleTextView.setText(section.getSubTitle());

        return convertView;
    }
}
