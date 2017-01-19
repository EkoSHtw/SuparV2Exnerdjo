package de.suparv2exnerdjocokg.suparv2exnerdjo.dummy;

import java.util.HashMap;
import java.util.List;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Medication.Medicine;

/**
 * Created by dsfd on 19.01.2017.
 */

public class DummyClientMedicine {

    public HashMap<String, List<Medicine>> CLIENTMEDICINE = new HashMap<>();

    public DummyClientMedicine(List<Medicine> predescribed, List<Medicine> temporary, List<Medicine> selfOrdered){

        CLIENTMEDICINE.put("PREDESCRIBED",predescribed);
        CLIENTMEDICINE.put("TEMPORARY", temporary);
        CLIENTMEDICINE.put("SELF_ORDERED", selfOrdered);

    }

}
