package de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by dsfd on 29.12.2016.
 */

public class LogBookHeadline extends Fragment {

        private TextView time;
        private TextView note;
        private TextView tag;
        private TextView carer;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_logbook_item, container, false);


            time = (TextView) view.findViewById(R.id.frag_logB_date);
            time.setTextAppearance(getContext(), R.style.AppTextHeadline);
            time.setTextSize(16);
            note = (TextView) view.findViewById(R.id.frag_logB_content);
            note.setTextAppearance(getContext(), R.style.AppTextHeadline);
            note.setTextSize(16);
            tag = (TextView) view.findViewById(R.id.frag_logB_tag);
            tag.setTextAppearance(getContext(), R.style.AppTextHeadline);
            tag.setTextSize(16);
            carer = (TextView) view.findViewById(R.id.frag_logB_carer);
            carer.setTextAppearance(getContext(), R.style.AppTextHeadline);
            carer.setTextSize(16);

            time.setText(R.string.date);
            note.setText(R.string.note);
            tag.setText(R.string.tag);
            carer.setText(R.string.carer);

            return view;
        }
}
