package de.suparv2exnerdjocokg.suparv2exnerdjo.DocumentTools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;

/**
 * Created by Eko on 08.12.2016.
 */

public class TableGenerator {
    private final Context mContext;
    private TableLayout mTable;
    private FrameLayout mFrame;
    private TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams();
    private TableRow.LayoutParams colParams = new TableRow.LayoutParams();
    private int headLenght;

    public int getIdCount() {
        return idCount;
    }

    private int idCount = 0;
    public TableGenerator(Context context) {
        mContext = context;
        mTable = new TableLayout(context);
        mFrame = new FrameLayout(context);
        rowParams.setMargins(0, 0, 0, 1);
        colParams.setMargins(0, 0, 1, 0);

        TableLayout.LayoutParams lptable = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTable.setLayoutParams(lptable);
        mTable.setStretchAllColumns(true);
        mTable.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
    }

    public void addRow() {
        TableRowExpand tr = new TableRowExpand(mContext);
        tr.setId(idCount);

        tr.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < headLenght; iCol++) {
            if(iCol == headLenght-1){
                final PictureButton pb = new PictureButton(mContext);
                pb.setGravity(Gravity.CENTER | Gravity.CENTER);
                pb.setPadding(3, 3, 3, 3);
                pb.setTextColor(mContext.getResources().getColor(
                        R.color.black));
                pb.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                pb.setMaxHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                pb.setLayoutParams(colParams);
                pb.setBackgroundColor(mContext.getResources().getColor(
                        R.color.row_background));
                pb.setHint(R.string.addPicture);


                pb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pb.ispicAdded()== false){

                        }
                    }
                });
                tr.addView(pb);
            }
            EditText tvCol = new EditText(mContext);
            tvCol.setGravity(Gravity.CENTER | Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(
                    R.color.black));
            tvCol.setMaxWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(
                    R.color.row_background));
            tr.addView(tvCol);
        }

        mTable.addView(tr);
        idCount++;
    }

    public void getRows(){

    }
    public void addHead(String[] data) {
        TableRow tr = new TableRow(mContext);
        tr.setBackgroundColor(mContext.getResources().getColor(
                R.color.table_background));
        headLenght= data.length;
        tr.setLayoutParams(rowParams);

        for (int iCol = 0; iCol < data.length; iCol++) {
            TextView tvCol = new TextView(mContext);
            tvCol.setText(data[iCol]);
            tvCol.setGravity(Gravity.CENTER | Gravity.CENTER);
            tvCol.setPadding(3, 3, 3, 3);
            tvCol.setTextColor(mContext.getResources().getColor(
                    R.color.black));
            tvCol.setLayoutParams(colParams);
            tvCol.setBackgroundColor(mContext.getResources().getColor(
                    R.color.row_background));
            tr.addView(tvCol);
        }

        mTable.addView(tr);
    }

    private void savePreferences(String key, String value) {

        SharedPreferences sharedPreferences = PreferenceManager

                .getDefaultSharedPreferences(mContext);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.commit();

    }
    public TableLayout getTable() {
        return mTable;
    }
}
/*public class MainActivity extends Activity {
    private ScrollView layMain;
    private TableGenerator mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        showTable();
    }

    private void showTable() {
        mTable = new TableGenerator(getApplicationContext());
        layMain = (ScrollView)findViewById(R.id.table);

        String[] firstRow = {"col1", "col2", "col3", "col4"};
        String[] secondRow = {"11", "12", "13", "14"};
        String[] thridRow = {"2a", "2b", "2c", "2d"};

        mTable.addRow(firstRow);
        mTable.addRow(secondRow);
        mTable.addRow(thridRow);

        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
    }
}*/

