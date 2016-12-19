package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;


public class BasicDataActivity extends FragmentActivity{

    private Client c;

    private static final String CLIENTKEY = "client_key";

    public BasicDataActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Fragment> frag =new ArrayList<>();
        WoundDocumentationFragment wdf = new WoundDocumentationFragment();

        ArrayList<ClientMedicine> medicineList= new ArrayList<ClientMedicine>();
        medicineList = null;
        PhoneNumber num1 = new PhoneNumber(getString(R.string.fam1),
                (getString(R.string.telephonenumber)));

        PhoneNumber num2 = new PhoneNumber(getString(R.string.fam2),
                (getString(R.string.telephonenumber2)));
        ArrayList<PhoneNumber>phoneNumber = new ArrayList<>();
        phoneNumber.add(num1);
        phoneNumber.add(num2);
        String a = "" + phoneNumber.size();
        Log.println(Log.INFO, "test",a);
        this.c = new Client(0,R.drawable.woman_image,getString(R.string.clientfirstname), getString(R.string.clientlastname),
                wdf, getString(R.string.clientBirthdate ),null,null, null, getString(R.string.infodump),
                Integer.parseInt(getString(R.string.careLevel)), getString(R.string.clientAddress), phoneNumber );

        setContentView(R.layout.activity_basic_data);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }


            Bundle bundle = new Bundle();
            bundle.putSerializable(CLIENTKEY, c);
            BasicDataBaseFragment fragobj = new BasicDataBaseFragment();
            fragobj.setArguments(bundle);
            // Create new fragment and transaction

          //  fragobj.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragobj).commit();

        }

    }
}
