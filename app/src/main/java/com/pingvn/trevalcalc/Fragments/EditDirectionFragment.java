package com.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.R;

import io.realm.Realm;


public class EditDirectionFragment extends Fragment implements View.OnClickListener {
    private static final String PARAM_DIRECTION = "direction_name";
    private String mDirectionName;
    private OnEditeDirectionFragmentInteractionListener mListener;
    private Realm mRealm;
    private Direction mDirection;
    private EditText mName;
    private EditText mInfo;
    private EditText mTicket;
    private EditText mLive;
    private EditText mFood;
    private EditText mFade;
    private Button mCancel;
    private Button mSave;

    //----------------------------------------------------------------------------------------------
    public EditDirectionFragment() {
        // Required empty public constructor
    }

    public static EditDirectionFragment newInstance(String mName) {
        EditDirectionFragment fragment = new EditDirectionFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_DIRECTION, mName);
        fragment.setArguments(args);
        return fragment;
    }

    private void initElements(View mView) {
        mInfo = mView.findViewById(R.id.id_Edite_Text_Edite_Direction_Info);
        mTicket = mView.findViewById(R.id.id_Edite_Direction_Edite_Text_Ticket_Coast);
        mLive = mView.findViewById(R.id.id_Edite_Direction_Edite_Text_Accomodation_Coast);
        mFood = mView.findViewById(R.id.id_Edit_Direction_Edite_Text_Food_Coast);
        mFade = mView.findViewById(R.id.id_Edit_Direction_Edite_Text_Edite_fare);
        mCancel = mView.findViewById(R.id.id_Edite_Direction_Button_Cancel);
        mSave = mView.findViewById(R.id.id_Edite_Direction_Button_Save);
        mName = mView.findViewById(R.id.id_Edite_Text_Edite_Direction_Name);
        //------------------------------------------------------------------------------------------
        mDirection = mRealm.where(Direction.class).equalTo("mName", mDirectionName).findFirst();
        mName.setText(mDirection.getmName());
        mInfo.setText(mDirection.getmInfo());
        mTicket.setText(Double.toString(mDirection.getmTicetCoast()));
        mLive.setText(Double.toString(mDirection.getmAccomodationCoast()));
        mFood.setText(Double.toString(mDirection.getmFoodCoast()));
        mFade.setText(Double.toString(mDirection.getFare()));
        //------------------------------------------------------------------------------------------
        mCancel.setOnClickListener(this);
        mSave.setOnClickListener(this);

    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDirectionName = getArguments().getString(PARAM_DIRECTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_edit_direction, container, false);
        mRealm = Realm.getDefaultInstance();
        initElements(mView);
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditeDirectionFragmentInteractionListener) {
            mListener = (OnEditeDirectionFragmentInteractionListener) context;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_Edite_Direction_Button_Cancel: {
                mListener.onEditeDirectionFragmentInteraction("#cancel");
            }
            break;
            case R.id.id_Edite_Direction_Button_Save: {
                mRealm.beginTransaction();
                if (mInfo.getText().toString().isEmpty()) {
                    mDirection.setmInfo("-");
                } else {
                    mDirection.setmInfo(mInfo.getText().toString());
                }
                if(mTicket.getText().toString().isEmpty()){
                    mDirection.setmTicetCoast(0);
                }else{
                    mDirection.setmTicetCoast(Double.valueOf(mTicket.getText().toString()));
                }
                if(mLive.getText().toString().isEmpty()){
                    mDirection.setmAccomodationCoast(0);
                }else
                {
                    mDirection.setmAccomodationCoast(Double.valueOf(mLive.getText().toString()));
                }
                if(mFood.getText().toString().isEmpty()){
                    mDirection.setmFoodCoast(0);
                }else{
                    mDirection.setmFoodCoast(Double.valueOf(mFood.getText().toString()));
                }
                if(mFade.getText().toString().isEmpty()){
                    mDirection.setFare(0);
                }else{
                    mDirection.setFare(Double.valueOf(mFade.getText().toString()));
                }

                mRealm.commitTransaction();
                mListener.onEditeDirectionFragmentInteraction("#save");
            }
            break;
        }

    }

    public interface OnEditeDirectionFragmentInteractionListener {
        void onEditeDirectionFragmentInteraction(String message);
    }
}
