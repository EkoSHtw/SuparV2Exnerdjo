package de.suparv2exnerdjocokg.suparv2exnerdjo.Documents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class DoctorialPrescription2 extends DocumentsTableTemplateFragment {

    public DoctorialPrescription2() {
        // Required empty public constructor
    }
    private FrameLayout layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private Client c;
    private File overwrite;
    private Context con;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.fragment_document_wound, container, false);
        this.c = ((ClientViewActivity)getActivity()).getClient();
        showTable();
        con = getContext();

        return  view;
    }



    private void showTable() {
        mTable = new TableGenerator(getActivity());
        layMain = (FrameLayout) view.findViewById(R.id.table);
        Log.println(Log.INFO, "2", "Kommt Durch");
        String[] firstRow = {getString(R.string.wounddate), getString(R.string.doctpre1hdz),
                getString(R.string.doctorname), getString(R.string.injec_infu),
                getString(R.string.frequency), getString(R.string.dochdz),
                getString(R.string.endon), getString(R.string.dochdz)};
        con = getContext();
        showTable(firstRow, getString(R.string.doctorialprescription2),view, c);


        return  view;
    }

}
