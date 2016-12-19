package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Eko on 04.12.2016.
 */

public class PDFViewFragment extends Fragment {

    private ImageView pdfFile;
    private int currentPage =0;
    private Button next;
    private Button previous;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pdf_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        next.findViewById(R.id.next);
        previous.findViewById(R.id.previous);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                render();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage--;
                render();
            }
        });

        render();
    }

    private void render(){
        try{
            pdfFile = (ImageView) getView().findViewById(R.id.pdf);
            int width = pdfFile.getWidth();
            int height = pdfFile.getHeight();

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
            File file = new File("sd");
           // PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open())

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
