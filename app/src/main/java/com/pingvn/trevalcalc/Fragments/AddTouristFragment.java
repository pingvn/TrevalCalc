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
import android.widget.Toast;

import com.pingvn.trevalcalc.DataModel.Tourist;
import com.pingvn.trevalcalc.R;

import io.realm.Realm;

public class AddTouristFragment extends Fragment implements View.OnClickListener {

    private OnFragmentAddTouristInteractionListener mListener;
    private EditText mName;
    private EditText mCredite;
    private EditText mDeposite;
    private Button mCreate;
    private Button mCancel;


    public AddTouristFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_add_tourist, container, false);
        initElements(mView);
        return mView;
    }

    private void initElements(View mView){
        mName = mView.findViewById(R.id.id_createTurist_name);
        mCredite = mView.findViewById(R.id.id_createTourist_Credite);
        mDeposite = mView.findViewById(R.id.id_createTourist_deposite);
        mCancel = mView.findViewById(R.id.id_createTourist_cencelButton);
        mCreate = mView.findViewById(R.id.id_idcreateTourist_addButton);
        mCreate.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAddTouristInteractionListener) {
            mListener = (OnFragmentAddTouristInteractionListener) context;
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_createTourist_cencelButton : {
                mListener.onAddTouristFragmentInteraction("#cancel");
            }break;
            case R.id.id_idcreateTourist_addButton : {
                if(mName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Должно быть заполнено хотябы поле Имя",Toast.LENGTH_LONG).show();
                }else{
                    Realm mRealm = Realm.getDefaultInstance();
                    mRealm.beginTransaction();
                    Tourist mTourist = mRealm.createObject(Tourist.class,mName.getText().toString());
                   // mTourist.setmName(mName.getText().toString());
                    mTourist.setmCredit(Double.parseDouble(mCredite.getText().toString()));
                    mTourist.setmDeposite(Double.parseDouble(mDeposite.getText().toString()));
                    mRealm.commitTransaction();
                    mRealm.close();
                    mListener.onAddTouristFragmentInteraction("#create");
                }

            }break;
        }
    }

    public interface OnFragmentAddTouristInteractionListener {
        // TODO: Update argument type and name
        void onAddTouristFragmentInteraction(String respone);
    }
}
