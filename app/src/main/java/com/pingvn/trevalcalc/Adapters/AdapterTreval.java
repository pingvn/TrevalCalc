package com.pingvn.trevalcalc.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pingvn.trevalcalc.DataModel.Treval;
import com.pingvn.trevalcalc.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AdapterTreval extends RecyclerView.Adapter<AdapterTreval.ViewHolder> {

    private List<Treval> mList = new ArrayList<>();
    private Realm mRealm;
    private static ClickListenerTravel clickListenerTravel;

    public AdapterTreval(List<Treval> mList,Realm realm) {
        this.mList = mList;
        this.mRealm = realm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_treval, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Treval mTreval = mList.get(position);
        holder.mName.setText(mTreval.getmName());
        holder.mDirection.setText(mTreval.getmDirection().get(0).getmName());
    }
    public void removeItem(int position){
        Treval mTreval = mRealm.where(Treval.class).equalTo("mName",mList.get(position).getmName()).findFirst();
        mRealm.beginTransaction();
        mTreval.deleteFromRealm();
        mRealm.commitTransaction();
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mName;
        private TextView mDirection;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            inicial(itemView);
        }

        private void inicial(View mView) {
            mName = mView.findViewById(R.id.id_card_travel_name);
            mDirection = mView.findViewById(R.id.id_card_direction_text);
        }

        @Override
        public void onClick(View view) {
            clickListenerTravel.onItemClick(getAdapterPosition());
        }
    }

    public void setOnClickListener(ClickListenerTravel clickListener){
       AdapterTreval.clickListenerTravel = clickListener;
    }

    public interface ClickListenerTravel{
        void onItemClick(int position);
    }



}
