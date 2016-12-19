package de.suparv2exnerdjocokg.suparv2exnerdjo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Documents.WoundDocumentationFragment;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyClients;


public class BasicDataActivity extends FragmentActivity{

    private Client c;

    private static final String CLIENTKEY = "client_key";

    public BasicDataActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = DummyClients.ITEMS.get(0);

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
