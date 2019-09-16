package ru.pingvn.trevalcalc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pingvn.trevalcalc.DataModel.Tourist;
import ru.pingvn.trevalcalc.DataModel.Treval;

import ru.pingvn.trevalcalc.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AdapterTouristList extends RecyclerView.Adapter<AdapterTouristList.ViewHolder> {
    private List<Tourist> mList = new ArrayList<>();
    private Realm mRealm;
    private Context mContext;
    private List<Treval> mListTreval = new ArrayList<>();
    private static ClickListenerTourist clickListenerTourist;

    public AdapterTouristList(List<Tourist> mList, Realm mRealm, Context context) {
        this.mList = mList;
        this.mRealm = mRealm;
        this.mContext = context;
    }

    public void removeItem(int position) {
        Tourist mTourist = mRealm.where(Tourist.class).equalTo("mName", mList.get(position).getmName()).findFirst();
        mListTreval = mRealm.copyFromRealm(mRealm.where(Treval.class).findAll());
        boolean consist = false;

        for(Treval mt: mListTreval){
            for(Tourist tt: mt.getmTurists()){
                if(tt.getmName().equals(mTourist.getmName())){
                    consist = true;
                }
            }

        }

        if(consist){
            Toast.makeText(mContext, mContext.getString(R.string.Message_delete), Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }else{
            mRealm.beginTransaction();
            mTourist.deleteFromRealm();
            mRealm.commitTransaction();
            mList.remove(position);
            notifyDataSetChanged();
        }

    }

    //----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_treval, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tourist mTourist= mList.get(position);
        holder.mName.setText(mTourist.getmName());
       // holder.mInfo.setText(mDirecion.getmInfo());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private TextView mInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            initElements(itemView);
        }

        private void initElements(View mView) {
            mName = mView.findViewById(R.id.id_card_travel_name);
            mInfo = mView.findViewById(R.id.id_card_direction_text);
        }

        @Override
        public void onClick(View view) {
            clickListenerTourist.onItemClick(getAdapterPosition());

        }
    }

    public void setOnClickListener(ClickListenerTourist clickListener) {
        AdapterTouristList.clickListenerTourist = clickListener;

    }

    public interface ClickListenerTourist {
        void onItemClick(int position);
    }
}
