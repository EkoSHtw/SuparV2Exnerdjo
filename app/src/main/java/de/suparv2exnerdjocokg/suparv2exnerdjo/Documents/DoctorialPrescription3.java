package de.suparv2exnerdjocokg.suparv2exnerdjo.Documents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.DocumentsTableTemplateFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.ImageDisplayFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.PictureButton;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableGenerator;
import de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools.TableRowExpand;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;

public class DoctorialPrescription3 extends DocumentsTableTemplateFragment {

    private ImageDisplayFragment.OnFragmentInteractionListener mListener;

    public DoctorialPrescription3() {
        // Required empty public constructor
    }
    private FrameLayout layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private File overwrite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        final Client c = ((ClientViewActivity)getActivity()).getClient();
        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.doctpre1hdz),
                view.getContext().getString(R.string.doctorname), view.getContext().getString(R.string.specialdiet),
                view.getContext().getString(R.string.frequency), view.getContext().getString(R.string.dochdz),
                view.getContext().getString(R.string.endon), view.getContext().getString(R.string.dochdz)};
        showTable(firstRow,getString(R.string.doctorialprescription2), view,c);

        return  view;
    }

}
