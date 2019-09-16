package ru.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.pingvn.trevalcalc.DataModel.Tourist;

import ru.pingvn.trevalcalc.R;

import io.realm.Realm;

public class EditeTouristFragment extends Fragment implements View.OnClickListener {
    private static final String PARAM_TOURIST = "tourist_name";
    private Realm mRealm;
    private String mNameTourist;
    private OnEditeTouristFragmentInteractionListener mListener;
    private EditText mEditeName;
    private EditText mEditeCredite;
    private EditText mEditeDeposite;
    private Button mCancel;
    private Button mSave;
    private Tourist mTourist;

    //----------------------------------------------------------------------------------------------
    public EditeTouristFragment() {
        // Required empty public constructor
    }

    public static EditeTouristFragment newInstance(String mName) {
        EditeTouristFragment fragment = new EditeTouristFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_TOURIST, mName);
        fragment.setArguments(args);
        return fragment;
    }

    private void initElements(View mView) {
        mEditeName = mView.findViewById(R.id.id_Edite_Tourist_Edite_Text_Name);
        mEditeCredite = mView.findViewById(R.id.id_Edite_Tourist_Edite_Text_Credite);
        mEditeDeposite = mView.findViewById(R.id.id_Edite_Tourist_Edite_Text_Deposite);
        mCancel = mView.findViewById(R.id.id_Edite_Tourist_Button_Cancel);
        mSave = mView.findViewById(R.id.id_Edite_Tourist_Button_Save);
        mCancel.setOnClickListener(this);
        mSave.setOnClickListener(this);
    }

    private void fillElements(){
        mEditeName.setText(mTourist.getmName());
        mEditeCredite.setText(String.valueOf(mTourist.getmCredit()));
        mEditeDeposite.setText(String.valueOf(mTourist.getmDeposite()));
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNameTourist = getArguments().getString(PARAM_TOURIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_edite_tourist, container, false);
        initElements(mView);
        mRealm = Realm.getDefaultInstance();
        mTourist = mRealm.where(Tourist.class).equalTo("mName", mNameTourist).findFirst();
        fillElements();
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditeTouristFragmentInteractionListener) {
            mListener = (OnEditeTouristFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRealm.close();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_Edite_Tourist_Button_Cancel: {
                mListener.onEditeTouristFragmentInteraction("#cancel");

            }
            break;
            case R.id.id_Edite_Tourist_Button_Save: {
                    mRealm.beginTransaction();
                    mTourist.setmCredit(Double.parseDouble(mEditeCredite.getText().toString()));
                    mTourist.setmDeposite(Double.parseDouble(mEditeDeposite.getText().toString()));
                    mRealm.commitTransaction();

            }
            break;
        }

    }

    public interface OnEditeTouristFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEditeTouristFragmentInteraction(String message);
    }
}
