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

            view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

            time = (TextView) view.findViewById(R.id.frag_logB_date);
            time.setTextAppearance(getContext(), R.style.AppTextNormal);
            note = (TextView) view.findViewById(R.id.frag_logB_content);
            note.setTextAppearance(getContext(), R.style.AppTextNormal);
            tag = (TextView) view.findViewById(R.id.frag_logB_tag);
            tag.setTextAppearance(getContext(), R.style.AppTextNormal);
            carer = (TextView) view.findViewById(R.id.frag_logB_carer);
            carer.setTextAppearance(getContext(), R.style.AppTextNormal);

            time.setText(R.string.date);
            note.setText(R.string.note);
            tag.setText(R.string.tag);
            carer.setText(R.string.carer);

            return view;
        }
}
