package com.pingvn.trevalcalc.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pingvn.trevalcalc.DataModel.Treval;
import com.pingvn.trevalcalc.R;

import java.util.ArrayList;

public class AdapterTreval extends RecyclerView.Adapter<AdapterTreval.ViewHolder> {

    private ArrayList<Treval> mList = new ArrayList<>();

    public AdapterTreval(ArrayList<Treval> mList) {
        this.mList = mList;
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
        holder.mDirection.setText(mTreval.getmDirection().getmName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDirection;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inicial(itemView);
        }

        private void inicial(View mView) {
            mName = mView.findViewById(R.id.id_card_travel_name);
            mDirection = mView.findViewById(R.id.id_card_direction_text);
        }
    }
}
