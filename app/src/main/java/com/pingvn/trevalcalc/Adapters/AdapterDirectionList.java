package com.pingvn.trevalcalc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.DataModel.Treval;
import com.pingvn.trevalcalc.R;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AdapterDirectionList extends RecyclerView.Adapter<AdapterDirectionList.ViewHolder> {
    private List<Direction> mList = new ArrayList<>();
    private Realm mRealm;
    private Context mContext;
    private List<Treval> mListTreval = new ArrayList<>();
    private static ClickListenerDirection clickListenerDirection;

    public AdapterDirectionList(List<Direction> mList, Realm mRealm, Context context) {
        this.mList = mList;
        this.mRealm = mRealm;
        this.mContext = context;
    }

    public void removeItem(int position) {
        Direction mDirection = mRealm.where(Direction.class).equalTo("mName", mList.get(position).getmName()).findFirst();
        mListTreval = mRealm.copyFromRealm(mRealm.where(Treval.class).findAll());
        boolean consist = false;
        for(Treval mt: mListTreval){
            if(mt.getmDirection().get(0).getmName().equals(mDirection.getmName())){
                consist = true;
            }
        }
        if(consist){
            Toast.makeText(mContext, mContext.getString(R.string.Message_delete), Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }else{
            mRealm.beginTransaction();
            mDirection.deleteFromRealm();
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
        Direction mDirecion = mList.get(position);
        holder.mName.setText(mDirecion.getmName());
        holder.mInfo.setText(mDirecion.getmInfo());

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
            initElements(itemView);
        }

        private void initElements(View mView) {
            mName = mView.findViewById(R.id.id_card_travel_name);
            mInfo = mView.findViewById(R.id.id_card_direction_text);
        }

        @Override
        public void onClick(View view) {
            clickListenerDirection.onItemClick(getAdapterPosition());

        }
    }

    public void setOnClickListener(ClickListenerDirection clickListener) {
        AdapterDirectionList.clickListenerDirection = clickListener;

    }

    public interface ClickListenerDirection {
        void onItemClick(int position);
    }
}
