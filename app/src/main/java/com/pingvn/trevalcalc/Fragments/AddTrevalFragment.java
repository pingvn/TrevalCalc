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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pingvn.trevalcalc.Adapters.AdapterTourist;
import com.pingvn.trevalcalc.DataModel.Direction;
import com.pingvn.trevalcalc.DataModel.Tourist;
import com.pingvn.trevalcalc.DataModel.Treval;
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
    private EditText mNameTravel;
    private Spinner mChoiseDirection;
    private Spinner mChoiseTourist;
    private TextView mDirectionText;
    private List<Direction> mDirections = new ArrayList<>();
    private List<Tourist> mTourists = new ArrayList<>();
    private int mSelectDirection = -1;
    private AdapterTourist mAdapterTouristRecycler;
    private RecyclerView mRecyclerView;
    private RealmList<Tourist> mRealmTourists = new RealmList<>();
    private boolean nocoise = true;
    private boolean nocoiseTourist = true;
    private String mEditTreval;
    private Treval mTrevalForEdite = new Treval();
    private boolean misEdite = false;


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
        spinerTourist(mView);
        return mView;
    }

    private void initElements(final View mView) {
        mNameTravel = mView.findViewById(R.id.id_edittext_treval_create);
        mCreateDirection = mView.findViewById(R.id.id_create_button_derection_text_view);
        mCreateTourist = mView.findViewById(R.id.id_create_button_tourist_text_view);
        mCancel = mView.findViewById(R.id.id_cancel_button_create_treval);
        mSave = mView.findViewById(R.id.id_create_treval_button);
       // mSave.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_save),null,null,null);
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

    private void spinerDirection(View mView) {

        Realm mRealmDirection = Realm.getDefaultInstance();
        mDirections = mRealmDirection.copyFromRealm(mRealmDirection.where(Direction.class).findAll());
        mRealmDirection.close();
        final List<String> mListNamesDirections = new ArrayList<>();
        for (int i = 0; i < mDirections.size(); i++) {
            mListNamesDirections.add(mDirections.get(i).getmName());
        }

        if (mListNamesDirections.size() > 0) {
            mListNamesDirections.add(getString(R.string.spiner_direction_default));
            ArrayAdapter<String> mAdapterDiscriptions = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, mListNamesDirections);
            mAdapterDiscriptions.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mChoiseDirection.setAdapter(mAdapterDiscriptions);
            mChoiseDirection.setSelection((mListNamesDirections.size()) - 1);
            mChoiseDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!nocoise) {
                        mSelectDirection = i;
                        mDirectionText.setText(mDirections.get(i).getmName());
                        mDirectionText.append(" - ");
                        mDirectionText.append(mDirections.get(i).getmInfo());
                        mDirectionText.append(summTreval(mDirections.get(i)));
                    } else {
                        nocoise = false;
                        mListNamesDirections.remove((mListNamesDirections.size()) - 1);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void spinerTourist(View mView) {
        final Realm mRealmTourist = Realm.getDefaultInstance();
        mTourists = mRealmTourist.copyFromRealm(mRealmTourist.where(Tourist.class).findAll());
        mRealmTourist.close();
        final List<String> mListTourists = new ArrayList<>();
        for (int i = 0; i < mTourists.size(); i++) {
            mListTourists.add(mTourists.get(i).getmName());
        }
        if (mListTourists.size() > 0) {
            mListTourists.add(getString(R.string.spiner_tourist_default));
            ArrayAdapter<String> mAdapterTourist = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, mListTourists);
            mAdapterTourist.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mChoiseTourist.setAdapter(mAdapterTourist);
            mChoiseTourist.setSelection((mListTourists.size()) - 1);
            mChoiseTourist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!nocoiseTourist) {
                        if (!mRealmTourists.contains(mTourists.get(i))) {
                            mRealmTourists.add(mTourists.get(i));
                        }
                        mAdapterTouristRecycler = new AdapterTourist(mRealmTourists);
                        mRecyclerView.setAdapter(mAdapterTouristRecycler);
                    } else {
                        nocoiseTourist = false;
                        mListTourists.remove((mListTourists.size()) - 1);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    private String summTreval(Direction mDirection) {
        return "\nСумма: " + (mDirection.getmTicetCoast() + mDirection.getmAccomodationCoast() + mDirection.getmFoodCoast() + mDirection.getFare());
    }


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
                if (mNameTravel.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.message_now_name_treval), Toast.LENGTH_LONG).show();
                } else {
                    if (mSelectDirection == -1) {
                        Toast.makeText(getContext(), getString(R.string.message_select_direction), Toast.LENGTH_LONG).show();
                    } else {
                        RealmList<Direction> mList = new RealmList<>();
                        mList.add(mDirections.get(mSelectDirection));
                        Realm mRealm = Realm.getDefaultInstance();
                        mRealm.beginTransaction();
                        Treval mTreval = mRealm.createObject(Treval.class, mNameTravel.getText().toString());
                        mTreval.setmDirection(mList);
                        mTreval.setmTurists(mRealmTourists);
                        mRealm.commitTransaction();
                        mRealm.close();
                        mListener.onFragmentAddTravelInteraction("#create");
                    }
                }

            }
            break;
        }

    }


    public interface OnFragmentAddTrevalInteractionListener {
        // TODO: Update argument type and name
        void onFragmentAddTravelInteraction(String respone);
    }
}
