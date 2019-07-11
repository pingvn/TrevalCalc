package com.pingvn.trevalcalc.Utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.pingvn.trevalcalc.Adapters.AdapterTourist;
import com.pingvn.trevalcalc.Adapters.AdapterTreval;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {
    private AdapterTreval mAdapterTreval;
    private AdapterTourist mAdapterTourist;

    public SwipeToDelete(AdapterTreval mAdapter) {
        super(0,ItemTouchHelper.LEFT);
        mAdapterTreval = mAdapter;
    }

    public SwipeToDelete(AdapterTourist mAdapterTourist) {
        super(0, ItemTouchHelper.LEFT);
        this.mAdapterTourist = mAdapterTourist;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

    }
}
