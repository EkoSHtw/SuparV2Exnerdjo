package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.graphics.Path;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;

import static android.R.attr.fragment;
import static android.R.attr.path;
import static de.suparv2exnerdjocokg.suparv2exnerdjo.R.id.gebdat;


public class BasicDataActivity extends FragmentActivity{

    private Client c;

    private static final String CLIENTKEY = "client_key";

    public BasicDataActivity(){
        ArrayList<Fragment> frag =null;
        WoundDocumentationFragment wdf = new WoundDocumentationFragment();
        frag.add(wdf);
        ArrayList<ClientMedicine> medicineList= new ArrayList<ClientMedicine>();
        medicineList = null;
        ArrayList<PhoneNumber> pnum;
        PhoneNumber num1 = new PhoneNumber(getString(R.string.fam1),
                (getString(R.string.telephonenumber)));

        PhoneNumber num2 = new PhoneNumber(getString(R.string.fam2),
                (getString(R.string.telephonenumber2)));
        ArrayList<PhoneNumber>phoneNumber = new ArrayList<>();
        phoneNumber.add(num1);
        phoneNumber.add(num2);
        this.c = new Client(0,getString(R.string.clientfirstname), getString(R.string.clientlastname),
                frag, getString(R.string.clientBirthdate ),null,null, null, getString(R.string.infodump),
       Integer.parseInt(getString(R.string.careLevel)), getString(R.string.clientAddress), phoneNumber );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_data);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }


            ArrayList<Client> clientList = new ArrayList<>();

            savedInstanceState.putSerializable(CLIENTKEY, 0);
            BasicDataBaseFragment fragobj = new BasicDataBaseFragment();
            fragobj.setArguments(savedInstanceState);

            // Create new fragment and transaction
            fragobj.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragobj).commit();

        }

    }
puts
}
