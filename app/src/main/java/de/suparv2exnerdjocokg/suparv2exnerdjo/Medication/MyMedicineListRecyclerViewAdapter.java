package de.suparv2exnerdjocokg.suparv2exnerdjo.Medication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.w3c.dom.Text;

import de.suparv2exnerdjocokg.suparv2exnerdjo.R;
import de.suparv2exnerdjocokg.suparv2exnerdjo.dummy.DummyMedicine;

import java.util.List;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class MyMedicineListRecyclerViewAdapter extends RecyclerView.Adapter<MyMedicineListRecyclerViewAdapter.ViewHolder> {

    private final List<Medicine> medicine;
    //private final MedicineListFragment.OnListFragmentInteractionListener mListener;

    public MyMedicineListRecyclerViewAdapter(List<Medicine> items) {
        medicine = items;
        //mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = medicine.get(position);
        holder.substance.setText(holder.mItem.getSubstance());
        holder.tradeName.setText(holder.mItem.getTradeName());
        holder.intensity.setText(holder.mItem.getIntensity());
        holder.form.setText(holder.mItem.getForm());
        holder.morning.setText(""+holder.mItem.getMornings());
        holder.noon.setText(""+holder.mItem.getNoon());
        holder.afternoon.setText(""+holder.mItem.getAfternoon());
        holder.night.setText(""+holder.mItem.getNight());
        holder.unit.setText(holder.mItem.getUnit());
        holder.information.setText(holder.mItem.getInformatio());
        holder.reason.setText(holder.mItem.getReason());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView substance;
        public final TextView tradeName;
        public final TextView intensity;
        public final TextView form;
        public final TextView morning;
        public final TextView noon;
        public final TextView afternoon;
        public final TextView night;
        public final TextView unit;
        public final TextView information;
        public final TextView reason;
        public Medicine mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            substance = (TextView) view.findViewById(R.id.substance);
            tradeName = (TextView) view.findViewById(R.id.trade_name);
            intensity = (TextView) view.findViewById(R.id.intensity);
            form = (TextView) view.findViewById(R.id.form);
            morning = (TextView) view.findViewById(R.id.morning);
            noon = (TextView) view.findViewById(R.id.noon);
            afternoon = (TextView) view.findViewById(R.id.afternoon);
            night = (TextView) view.findViewById(R.id.night);
            unit = (TextView) view.findViewById(R.id.unit);
            information = (TextView) view.findViewById(R.id.information);
            reason = (TextView) view.findViewById(R.id.reason);
        }
    }
}
