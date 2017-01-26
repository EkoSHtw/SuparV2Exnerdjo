package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Client;
import de.suparv2exnerdjocokg.suparv2exnerdjo.ClientViewActivity;
import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.string.addRow;


public class DocumentsTableTemplateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DocumentsTableTemplateFragment() {
        // Required empty public constructor
    }

    private FrameLayout layMain;
    private TableGenerator mTable;
    private Button addRow;
    private Button saveIt;
    private View view;
    private String[] firstRow;

    private File overwrite;
    private int index;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_document_wound, container, false);
        final Client c = ((ClientViewActivity) getActivity()).getClient();
        String[] firstRow = {view.getContext().getString(R.string.wounddate), view.getContext().getString(R.string.mobdestination),
                view.getContext().getString(R.string.mobMeasures), view.getContext().getString(R.string.mobcharacteristics),
                view.getContext().getString(R.string.mobtime),
                view.getContext().getString(R.string.hdz)};
        showTable(firstRow, getString(R.string.mobdocname), this.view, c);

        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void showTable(String[] firstRow, String filename, View view, final Client c) {
        mTable = new TableGenerator(getActivity());
        layMain = (FrameLayout) view.findViewById(R.id.table);

        mTable.addHead(firstRow);
        for (int i = 0; i < c.docsListLenghts(); i++) {
            this.overwrite = c.getDocumentation().get(i);
            if (c.getDocumentation().get(i).getName().equals(filename + " " + c.getFullName())) {
                index = i;
                int rowCount = 2;
                int fillCount = 0;

                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(c.getDocumentation().get(i)));
                    if (bufferedReader.readLine() != null) {
                        mTable.addRow();
                        String receiveString = "";

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            View v = mTable.getTable().getChildAt(rowCount);
                            TableRowExpand t = (TableRowExpand) v;
                            String s = receiveString.replace("/", "");

                            while(!(t.getChildAt(fillCount) instanceof EditText)) {
                                fillCount++;
                            }

                            Log.println(Log.INFO, ""+i, ""+t.getChildCount());
                            Log.println(Log.INFO, "Row", ""+fillCount);

                            EditText firstTextView = (EditText) t.getChildAt(fillCount);
                            firstTextView.setText(s);
                            fillCount++;

                            if (fillCount == mTable.getHeadLenght()*2-1) {
                                mTable.addRow();
                                fillCount = 0;
                                rowCount+=2;
                            }
                        }
                        bufferedReader.close();
                    } else {
                        bufferedReader.close();
                        mTable.addRow();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
        addRow = (Button) view.findViewById(R.id.addRowB);
        saveIt = (Button) view.findViewById(R.id.saveStuff);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable.addRow();
            }
        });
        saveIt = (Button) view.findViewById(R.id.saveStuff);
        saveIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outputStream;
                    int k = mTable.getChildCount();

                    outputStream = new FileOutputStream(overwrite);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(outputStream);
                    myOutWriter.write(" " + "/" + "\n");
                    for (int i = 1; i < mTable.getChildCount(); i++) {
                        View vi = mTable.getTable().getChildAt(i);

                        TableRowExpand t = (TableRowExpand) vi;
                        String textLine = "" ;
                        if(t.getChildCount() > 1) {
                            for (int j = 0; j < mTable.getHeadLenght() * 2; j += 2) {
                                EditText text = (EditText) t.getChildAt(j);
                                textLine += text.getText().toString() + "\n";
                            }
                            myOutWriter.write(textLine);
                            myOutWriter.flush();
                            outputStream.flush();
                        }
                    }
                    myOutWriter.close();
                    outputStream.close();

                    Context context = getContext();
                    CharSequence text = getString(R.string.saved);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}