package com.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pingvn.trevalcalc.Adapters.AdapterDirectionList;
import com.pingvn.trevalcalc.Adapters.AdapterTouristList;
import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.DataModel.Tourist;
import com.pingvn.trevalcalc.R;
import com.pingvn.trevalcalc.Utils.SwipeToDeleteDirections;
import com.pingvn.trevalcalc.Utils.SwipeToDeleteTourist;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class EditListsTuristandDirectionFragment extends Fragment {

    private static final String PARAM_CHOISE = "param_choise";
    public static final String TOURIST_CHOISE = "tourist";
    public static final String DIRECTION_CHOISE = "direction";
    private Realm mRealm;
    private RecyclerView mRecyclerView;
    private String mParamChoise;
    private OnTuristAndDirecionFragmentInteractionListener mListener;

    private List<Direction> mListDirection = new ArrayList<>();
    private List<Tourist> mListTourist = new ArrayList<>();


    public EditListsTuristandDirectionFragment() {

    }

    private void initElements(View mView) {
        mRecyclerView = mView.findViewById(R.id.id_edit_list_tourist_direction_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRealm = Realm.getDefaultInstance();

    }

    private void fillRecyclerDirection(){

        mListDirection = mRealm.copyFromRealm(mRealm.where(Direction.class).findAll());
        AdapterDirectionList mAdapter = new AdapterDirectionList(mListDirection,mRealm,getContext());
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new SwipeToDeleteDirections(mAdapter,getContext()));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnClickListener(new AdapterDirectionList.ClickListenerDirection() {
            @Override
            public void onItemClick(int position) {
                mListener.onTuristAndDirecionFragmentInteraction("#editDirection",mListDirection.get(position).getmName());
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }
    private void fillRecyclerTourist(){

        mListTourist = mRealm.copyFromRealm(mRealm.where(Tourist.class).findAll());
        AdapterTouristList mAdapter = new AdapterTouristList(mListTourist,mRealm,getContext());
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new SwipeToDeleteTourist(mAdapter,getContext()));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnClickListener(new AdapterTouristList.ClickListenerTourist() {
            @Override
            public void onItemClick(int position) {
                mListener.onTuristAndDirecionFragmentInteraction("#editeTourist",mListTourist.get(position).getmName());
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }


    public static EditListsTuristandDirectionFragment newInstance(String param) {
        EditListsTuristandDirectionFragment fragment = new EditListsTuristandDirectionFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_CHOISE, param);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamChoise = getArguments().getString(PARAM_CHOISE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_edit_lists_turistand_direction, container, false);
        initElements(mView);
        switch (mParamChoise){
            case DIRECTION_CHOISE :{
                fillRecyclerDirection();
            }break;
            case TOURIST_CHOISE : {
                fillRecyclerTourist();
            }
        }
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTuristAndDirecionFragmentInteractionListener) {
            mListener = (OnTuristAndDirecionFragmentInteractionListener) context;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRealm.close();
    }

    public interface OnTuristAndDirecionFragmentInteractionListener {

        void onTuristAndDirecionFragmentInteraction(String mCommand, String mName);
    }
}
