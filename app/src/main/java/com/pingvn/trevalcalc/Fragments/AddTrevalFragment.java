package com.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.pingvn.trevalcalc.Adapters.AdapterTourist;
import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.DataModel.Tourist;
import com.pingvn.trevalcalc.R;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmList;

public class AddTrevalFragment extends Fragment implements View.OnClickListener {

    private OnFragmentAddTrevalInteractionListener mListener;
    private Button mCreateDirection;
    private Button mCreateTourist;
    private Button mCancel;
    private Button mSave;
    private Spinner mChoiseDirection;
    private Spinner mChoiseTourist;
    private TextView mDirectionText;
    private List<Direction> mDirections = new ArrayList<>();
    private List<Tourist> mTourists = new ArrayList();
    private int mSelectDirection = -1;
    private AdapterTourist mAdapterTourist;
    private RecyclerView mRecyclerView;
    private RealmList<Tourist> mRealmTourists;

    List<Tourist> mBufferTourist = new ArrayList<>();

    public AddTrevalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_add_treval, container, false);
        initElements(mView);
        spinerDirection(mView);


        return mView;
    }

    private void initElements(final View mView) {
        mCreateDirection = mView.findViewById(R.id.id_create_button_derection_text_view);
        mCreateTourist = mView.findViewById(R.id.id_create_button_tourist_text_view);
        mCancel = mView.findViewById(R.id.id_cancel_button_create_treval);
        mSave = mView.findViewById(R.id.id_create_treval_button);
        mChoiseDirection = mView.findViewById(R.id.id_spener_direction_treval);
        mChoiseTourist = mView.findViewById(R.id.id_spener_tourist_treval);
        mDirectionText = mView.findViewById(R.id.id_textview_addet_direction);
        mRecyclerView = mView.findViewById(R.id.id_recycler_viev_select_tourist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        //------------------------------------------------------------------------------------------
        mSave.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mCreateTourist.setOnClickListener(this);
        mCreateDirection.setOnClickListener(this);


    }

    private void spinerDirection(View mView){

    }


    /*
    private void spinerWork(View mView) {
        Realm mRealm = Realm.getDefaultInstance();
        mDirections = mRealm.copyFromRealm(mRealm.where(Direction.class).findAll());
        mRealm.close();
        Realm mRealmTourist = Realm.getDefaultInstance();
        mBufferTourist = mRealmTourist.copyFromRealm(mRealmTourist.where(Tourist.class).findAll());
        mRealmTourist.close();
        String[] mArraeyDirections = new String[mDirections.size()];
        String[] mArrayTourists = new String[mBufferTourist.size()];
        for(int i = 0; i< mTourists.size();i++){
            mArrayTourists[i] = mBufferTourist.get(i).getmName();
        }
        for (int i = 0; i < mDirections.size(); i++) {
            mArraeyDirections[i] = mDirections.get(i).getmName();
        }
        ArrayAdapter<String> mAdapterSpinnerTourists = new ArrayAdapter<String>(mView.getContext(),android.R.layout.simple_spinner_item, mArrayTourists);
        ArrayAdapter<String> mAdapterSpinerDirections = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, mArraeyDirections);

        mAdapterSpinerDirections.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mAdapterSpinnerTourists.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mChoiseDirection.setAdapter(mAdapterSpinerDirections);
        mChoiseTourist.setAdapter(mAdapterSpinnerTourists);

        mChoiseTourist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               mTourists.add(mBufferTourist.get(i));
                mAdapterTourist = new AdapterTourist(mTourists);
               mRecyclerView.setAdapter(mAdapterTourist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mDirectionText.setText("Выберете из списка, или создайти новое направление");
        mChoiseDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectDirection = i;
                mDirectionText.setText(mDirections.get(i).getmName());
                mDirectionText.append(mDirections.get(i).getmInfo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentAddTrevalInteractionListener) {
            mListener = (OnFragmentAddTrevalInteractionListener) context;
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
        switch (view.getId()) {
            case R.id.id_create_button_derection_text_view: {
                mListener.onFragmentAddTravelInteraction("#createDirection");
            }
            break;
            case R.id.id_create_button_tourist_text_view: {
                mListener.onFragmentAddTravelInteraction("#createTourist");
            }
            break;
            case R.id.id_cancel_button_create_treval: {
                mListener.onFragmentAddTravelInteraction("#cancel");
            }
            break;
            case R.id.id_create_treval_button: {
                mListener.onFragmentAddTravelInteraction("#create");
            }
            break;
        }

    }


    public interface OnFragmentAddTrevalInteractionListener {
        // TODO: Update argument type and name
        void onFragmentAddTravelInteraction(String respone);
    }
}
