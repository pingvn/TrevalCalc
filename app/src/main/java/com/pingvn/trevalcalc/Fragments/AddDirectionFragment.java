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

import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.R;

import io.realm.Realm;

public class AddDirectionFragment extends Fragment implements View.OnClickListener {

    private OnFragmentAddDirectionInteractionListener mListener;
    private Button mCreate;
    private Button mCancel;
    private EditText mName;
    private EditText mInfo;
    private EditText mTicket;
    private EditText mAccomodation;
    private EditText mFood;
    private EditText mFare;

    public AddDirectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_add_direction, container, false);
        initElements(mView);
        return mView;
    }

    private void initElements(View mView) {
        mCreate = mView.findViewById(R.id.id_idcreateDirection_addbutton);
        mCancel = mView.findViewById(R.id.id_createDirection_cencelButton);
        mName = mView.findViewById(R.id.id_edittext_addDirection_name);
        mInfo = mView.findViewById(R.id.id_editText_addDirection_info);
        mTicket = mView.findViewById(R.id.id_EditText_addDirection_Ticket_coust);
        mAccomodation = mView.findViewById(R.id.id_EditText_addDirection_Accomodation_coast);
        mFood = mView.findViewById(R.id.id_EditText_addDirection_Food_coust);
        mFare = mView.findViewById(R.id.id_EditText_addDirection_fare);
        //------------------------------------------------------------------------------------------
        mCancel.setOnClickListener(this);
        mCreate.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAddDirectionInteractionListener) {
            mListener = (OnFragmentAddDirectionInteractionListener) context;
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

    private boolean compileDirection() {
        Realm mRealm = Realm.getDefaultInstance();
        if (mName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Должно быть заполнено хотябы поле Имя", Toast.LENGTH_LONG).show();
            mRealm.close();
            return false;
        } else {
            mRealm.beginTransaction();
            Direction mDirection = mRealm.createObject(Direction.class,mName.getText().toString());
            //mDirection.setmName(mName.getText().toString());
            if (!mInfo.getText().toString().isEmpty())
               mDirection.setmInfo(mInfo.getText().toString());
            else mDirection.setmInfo(" ");
            if (!mTicket.getText().toString().isEmpty())
                mDirection.setmTicetCoast(Double.parseDouble(mTicket.getText().toString()));
            if (!mAccomodation.getText().toString().isEmpty())
                mDirection.setmAccomodationCoast(Double.parseDouble(mAccomodation.getText().toString()));
            if (!mFood.getText().toString().isEmpty())
                mDirection.setmFoodCoast(Double.parseDouble(mFood.getText().toString()));
            if (!mFare.getText().toString().isEmpty())
                mDirection.setmFoodCoast(Double.parseDouble(mFare.getText().toString()));
            mRealm.commitTransaction();
            mRealm.close();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_idcreateDirection_addbutton: {
                if (compileDirection())
                    mListener.onFragmentAddDirectionInteraction("#create");

            }
            break;
            case R.id.id_createDirection_cencelButton: {
                mListener.onFragmentAddDirectionInteraction("#cancel");
            }

        }
    }

    public interface OnFragmentAddDirectionInteractionListener {
        // TODO: Update argument type and name
        void onFragmentAddDirectionInteraction(String respone);
    }
}
