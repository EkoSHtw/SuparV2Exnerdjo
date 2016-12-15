package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.document_list;

/**
 * Created by Eko on 27.11.2016.
 */

public class DocumentListFragment extends Fragment{

    private ListView documents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_document_list, container, false);
    }

    public ArrayList<String> getLFile(){
        Field[] fields= R.raw.class.getFields();
        ArrayList<String> s = new ArrayList<>();
        for(int count=0; count < fields.length; count++){
            try {

                s.add(fields[count].getName());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return s;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        ArrayList<String> s = getLFile();
        documents = (ListView) getView().findViewById(document_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getView().getContext(),
                android.R.layout.simple_list_item_1,
                s );
        documents.setAdapter(arrayAdapter);


        File file = new File("raw/" + documents.getSelectedItem().toString()+ ".pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf" );
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }
}
  //  int resourceID = fields[count].getInt(fields[count]);