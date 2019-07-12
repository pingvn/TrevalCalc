package com.pingvn.trevalcalc.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pingvn.trevalcalc.DataModel.Tourist;
import com.pingvn.trevalcalc.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class AdapterTourist extends RecyclerView.Adapter<AdapterTourist.ViewHolder> {
    private RealmList<Tourist> mList = new RealmList<>();

    public AdapterTourist(RealmList<Tourist> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tourist,parent,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tourist mTourist = mList.get(position);
        holder.mText.setText(mTourist.getmName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.id_textview_cardtourist);
        }

    }
}
