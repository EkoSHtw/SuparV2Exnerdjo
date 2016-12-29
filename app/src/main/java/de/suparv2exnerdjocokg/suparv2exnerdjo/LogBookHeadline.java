package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dsfd on 29.12.2016.
 */

public class LogBookHeadline extends Fragment {

        // TODO: Customize parameter argument names
        private static final String ARG_COLUMN_COUNT = "column-count";
        // TODO: Customize parameters
        private int mColumnCount = 1;

        private TextView time;
        private TextView note;
        private TextView tag;
        private TextView carer;
        //private OnListFragmentInteractionListener mListener;

        /**
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
        public LogBookHeadline() {

        }

        // TODO: Customize parameter initialization
        @SuppressWarnings("unused")
        public static LogBookHeadline newInstance(int columnCount) {
            LogBookHeadline fragment = new LogBookHeadline();
            Bundle args = new Bundle();
            args.putInt(ARG_COLUMN_COUNT, columnCount);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getArguments() != null) {
                mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            }
        }

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

            time.setText("Zeit");
            note.setText("Notiz");
            tag.setText("Tag");
            carer.setText("Pfleger");

            return view;
        }


        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
        }

        @Override
        public void onDetach() {
            super.onDetach();
            //mListener = null;
        }
}
