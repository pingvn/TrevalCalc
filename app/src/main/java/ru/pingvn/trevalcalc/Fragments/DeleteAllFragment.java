package ru.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.pingvn.trevalcalc.R;

import io.realm.Realm;


public class DeleteAllFragment extends Fragment implements View.OnClickListener {
    private Button mDelete;
    private Button mCancel;

    private OnDeleteAllFragmentInteractionListener mListener;

    public DeleteAllFragment() {
        // Required empty public constructor
    }

    public void initElement(View mView){

        mDelete = mView.findViewById(R.id.id_delete_all_fragment_delete);
        mCancel = mView.findViewById(R.id.id_delete_all_fragment_cancel);
        mDelete.setOnClickListener(this);
        mCancel.setOnClickListener(this);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View mView = inflater.inflate(R.layout.fragment_delete_all, container, false);
      initElement(mView);
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDeleteAllFragmentInteractionListener) {
            mListener = (OnDeleteAllFragmentInteractionListener) context;
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
            case R.id.id_delete_all_fragment_cancel : {
                mListener.onDeleteAllFragmentInteraction();

            }break;
            case R.id.id_delete_all_fragment_delete : {
                Realm mRealm = Realm.getDefaultInstance();
                mRealm.beginTransaction();
                mRealm.deleteAll();
                mRealm.commitTransaction();
                mRealm.close();
                mListener.onDeleteAllFragmentInteraction();


            }break;

        }

    }


    public interface OnDeleteAllFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDeleteAllFragmentInteraction();
    }
}
