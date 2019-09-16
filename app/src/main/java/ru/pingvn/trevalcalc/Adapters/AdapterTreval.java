package ru.pingvn.trevalcalc.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pingvn.trevalcalc.DataModel.Treval;

import ru.pingvn.trevalcalc.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AdapterTreval extends RecyclerView.Adapter<AdapterTreval.ViewHolder> {

    private List<Treval> mList = new ArrayList<>();
    private Realm mRealm;
    private static ClickListenerTravel clickListenerTravel;
    private Context mContext;

    public AdapterTreval(List<Treval> mList,Realm realm, Context context) {
        this.mList = mList;
        this.mRealm = realm;
        this.mContext = context;
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
       // holder.mName.setText(mTreval.getmName());
       // holder.mDirection.setText(mTreval.getmDirection().get(0).getmName());
        double coast = costTrip(mTreval.getmDirection().get(0).getmTicetCoast(),mTreval.getmDirection().get(0).getmAccomodationCoast(),mTreval.getmDirection().get(0).getmFoodCoast(),mTreval.getmDirection().get(0).getFare());
        double turist = mTreval.getmTurists().size();
       // holder.mDirection.append(" стоимость: "+ coast);
       // holder.mInfo.setText("количество путешественников : "+ mTreval.getmTurists().size());
       // holder.mInfo.append("\nобщая стоимость : ");
        holder.mChevk.setTextColor(Color.RED);
       // holder.mChevk.setText(""+coast*turist);
        holder.mName.setText(mTreval.getmName());
        holder.mDirection.setText(mContext.getResources().getString(R.string.text_Card_Direction)+mTreval.getmDirection().get(0).getmName());
        holder.mInfo.setText(mContext.getResources().getString(R.string.text_Card_turist)+mTreval.getmTurists().size());
        holder.mChevk.setText(mContext.getResources().getString(R.string.text_Card_total_coast)+coast*turist);
    }

    private double costTrip(double ticket, double hotel, double food, double car){
        return ticket+hotel+food+car;
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
        private TextView mInfo;
        private TextView mChevk;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            inicial(itemView);
        }

        private void inicial(View mView) {
            mName = mView.findViewById(R.id.id_card_travel_name);
            mDirection = mView.findViewById(R.id.id_card_direction_text);
            mInfo = mView.findViewById(R.id.id_card_treval_info);
            mChevk = mView.findViewById(R.id.id_card_filan_chek);
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
