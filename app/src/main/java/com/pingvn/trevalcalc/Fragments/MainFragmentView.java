package com.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pingvn.trevalcalc.Adapters.AdapterTreval;
import com.pingvn.trevalcalc.DataModel.Treval;
import com.pingvn.trevalcalc.R;
import com.pingvn.trevalcalc.Utils.SwipeTreval;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainFragmentView extends Fragment {
    private FloatingActionButton mCreateButton;
    private RecyclerView mRecuclerViewTreval;

    private OnMainFragmentInteractionListener mListener;

    public MainFragmentView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main_fragment_view, container, false);
        initElements(mView);
        fillRecycler();
        return mView;
    }

    private void initElements(View mView){
        SwipeTreval mSwipe = new SwipeTreval();
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mSwipe);
        mCreateButton = mView.findViewById(R.id.id_floatingActionButton_treval);
        mRecuclerViewTreval = mView.findViewById(R.id.id_recyclerview_treval);
        mRecuclerViewTreval.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mItemTouchHelper.attachToRecyclerView(mRecuclerViewTreval);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction("#createtreval");
            }
        });
    }

    private void fillRecycler(){
        Realm mRealm = Realm.getDefaultInstance();
        List<Treval> mList = mRealm.copyFromRealm(mRealm.where(Treval.class).findAll());
        mRealm.close();
        AdapterTreval mAdapter = new AdapterTreval(mList);
        mRecuclerViewTreval.setAdapter(mAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainFragmentInteractionListener) {
            mListener = (OnMainFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMainFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String mTrevalName);
    }
}
