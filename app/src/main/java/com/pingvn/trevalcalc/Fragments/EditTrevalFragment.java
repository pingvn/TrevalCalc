package com.pingvn.trevalcalc.Fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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


public class EditTrevalFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM = "name";
    private String mNameTreval;
    private OnEditFragmentFragmentInteractionListener mListener;
    private Treval mTreval;
    private Button mButtonCreateDirection;
    private Button mButtonCreateTuriste;
    private Button mButtonSave;
    private Button mButtonCancel;
    private Spinner mSpinnerDirection;
    private Spinner mSpinerTurist;
    private EditText mEditNameTravel;
    private TextView mTextDirection;
    private RecyclerView mRecyclerTourist;
    private String mNameDirection;
    private boolean onPushed = false;
    private int mDirectionPosition;
    private List<Direction> mDirections = new ArrayList<>();
    private List<String> mListNamesDirections = new ArrayList<>();
    private RealmList<Tourist> mRealmTourists = new RealmList<>();
    private AdapterTourist mAdapterTouristRecycler;
    private List<Tourist> mTourists = new ArrayList<>();
    private Realm mRealm;
    private RealmList<Direction> mList = new RealmList<>();
    private Drawable mIcon;
    private final ColorDrawable mBackground =  new ColorDrawable(Color.RED);

    //----------------------------------------------------------------------------------------------
    public EditTrevalFragment() {
        // Required empty public constructor
    }

    public static EditTrevalFragment newInstance(String mName) {
        EditTrevalFragment fragment = new EditTrevalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, mName);
        fragment.setArguments(args);
        return fragment;
    }

    //----------------------------------------------------------------------------------------------


    private void spinerDirection(View mView) {

        mDirections = mRealm.copyFromRealm(mRealm.where(Direction.class).findAll());

        //------------------------------------------------------------------------------------------

        for (int i = 0; i < mDirections.size(); i++) {
            mListNamesDirections.add(mDirections.get(i).getmName());
        }

        if (mListNamesDirections.size() > 0) {
            ArrayAdapter<String> mAdapterDiscriptions = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, mListNamesDirections);
            mAdapterDiscriptions.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mSpinnerDirection.setAdapter(mAdapterDiscriptions);
            mSpinnerDirection.setSelection(mAdapterDiscriptions.getPosition(mNameDirection));
            mSpinnerDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mDirectionPosition = i;
                    mTextDirection.setText(mDirections.get(i).getmName());
                    mTextDirection.append(" - ");
                    mTextDirection.append(mDirections.get(i).getmInfo());
                    mTextDirection.append(summTreval(mDirections.get(i)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void spinerTurist(View mView) {

        mTourists = mRealm.copyFromRealm(mRealm.where(Tourist.class).findAll());
        final List<String> mListTouristes = new ArrayList<>();
        for (int i = 0; i < mTourists.size(); i++) {
            mListTouristes.add(mTourists.get(i).getmName());
        }
        if (mListTouristes.size() > 0) {
            final ArrayAdapter<String> mAdapterTourist = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, mListTouristes);
            mAdapterTourist.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mSpinerTurist.setAdapter(mAdapterTourist);
            mSpinerTurist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!onPushed) {
                        onPushed = true;
                    } else if (!mRealmTourists.contains(mTourists.get(i))) {
                        mRealmTourists.add(mTourists.get(i));
                    }
                    mAdapterTouristRecycler = new AdapterTourist(mRealmTourists);
                    mIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_delete_button);

                    ItemTouchHelper.SimpleCallback sampleTouch = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                            //добавляем действие при свайпе, у меня удаление элемента
                            //получаю позицию элемента на котором свайпнули в списке
                            int mPosition = viewHolder.getAdapterPosition();
                            //удаление из адаптера
                            mAdapterTouristRecycler.removeItem(mPosition);

                        }

                        @Override
                        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                            View mItemView = viewHolder.itemView;
                            int mBackgroundCornerOffset = 20;
                            int mIconMArign = (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
                            int mIconTop = mItemView.getTop() + (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
                            int mIconBottom = mIconTop + mIcon.getIntrinsicHeight();
                            //------------------------------------------------------------------------------------------

                            //свайп в право
                            if (dX > 0) {
                                int mIconLeft = mItemView.getLeft() + mIconMArign;
                                int mIconRight = mIconLeft + mIcon.getIntrinsicWidth();
                                mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
                                mBackground.setBounds(mItemView.getLeft(), mItemView.getTop(), mItemView.getLeft() + ((int) dX) - mBackgroundCornerOffset, mItemView.getBottom());
                            } else if (dX < 0) {
                                int mIconLeft = mItemView.getRight() - mIconMArign - mIcon.getIntrinsicWidth();
                                int mIconRight = mItemView.getRight() - mIconMArign;
                                mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
                                mBackground.setBounds(mItemView.getRight() + ((int) dX) - mBackgroundCornerOffset, mItemView.getTop(), mItemView.getRight(), mItemView.getBottom());
                            } else {
                                mBackground.setBounds(0, 0, 0, 0);
                            }
                            mBackground.draw(c);
                            mIcon.draw(c);
                        }
                    };
                    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(sampleTouch);
                    mItemTouchHelper.attachToRecyclerView(mRecyclerTourist);

                   // ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new SwipetoDeleteTouristonTreval(mAdapterTouristRecycler, getContext()));
                   // mItemTouchHelper.attachToRecyclerView(mRecyclerTourist);

                    mRecyclerTourist.setAdapter(mAdapterTouristRecycler);

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

    private void initElements(View mView) {
        mButtonCreateDirection = mView.findViewById(R.id.id_Create_Edite_button_derection_text_view_Treval);
        mButtonCreateTuriste = mView.findViewById(R.id.id_create_button_tourist_text_view_Edite_Treval);
        mButtonSave = mView.findViewById(R.id.id_create_treval_button_Edite_treval);
        mButtonCancel = mView.findViewById(R.id.id_cancel_button_create_Edite_treval);
        mSpinnerDirection = mView.findViewById(R.id.id_spener_direction_Edit_treval);
        mSpinerTurist = mView.findViewById(R.id.id_spener_tourist_tEdite_reval);
        mEditNameTravel = mView.findViewById(R.id.id_edittext_Edit_treval_create);
        mTextDirection = mView.findViewById(R.id.id_textview_addet_Edit_direction_Treval);
        mRecyclerTourist = mView.findViewById(R.id.id_recycler_viev_select_tourist_Etite_Treval);
        mRecyclerTourist.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mEditNameTravel.setText(mNameTreval);


        //------------------------------------------------------------------------------------------
        mButtonCreateDirection.setOnClickListener(this);
        mButtonCreateTuriste.setOnClickListener(this);
        mButtonSave.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
        //------------------------------------------------------------------------------------------
        mAdapterTouristRecycler = new AdapterTourist(mRealmTourists);
        mIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_delete_button);

        ItemTouchHelper.SimpleCallback sampleTouch = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //добавляем действие при свайпе, у меня удаление элемента
                //получаю позицию элемента на котором свайпнули в списке
                int mPosition = viewHolder.getAdapterPosition();
                //удаление из адаптера
                mAdapterTouristRecycler.removeItem(mPosition);

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View mItemView = viewHolder.itemView;
                int mBackgroundCornerOffset = 20;
                int mIconMArign = (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
                int mIconTop = mItemView.getTop() + (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
                int mIconBottom = mIconTop + mIcon.getIntrinsicHeight();
                //------------------------------------------------------------------------------------------

                //свайп в право
                if (dX > 0) {
                    int mIconLeft = mItemView.getLeft() + mIconMArign;
                    int mIconRight = mIconLeft + mIcon.getIntrinsicWidth();
                    mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
                    mBackground.setBounds(mItemView.getLeft(), mItemView.getTop(), mItemView.getLeft() + ((int) dX) - mBackgroundCornerOffset, mItemView.getBottom());
                } else if (dX < 0) {
                    int mIconLeft = mItemView.getRight() - mIconMArign - mIcon.getIntrinsicWidth();
                    int mIconRight = mItemView.getRight() - mIconMArign;
                    mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
                    mBackground.setBounds(mItemView.getRight() + ((int) dX) - mBackgroundCornerOffset, mItemView.getTop(), mItemView.getRight(), mItemView.getBottom());
                } else {
                    mBackground.setBounds(0, 0, 0, 0);
                }
                mBackground.draw(c);
                mIcon.draw(c);
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(sampleTouch);
        mItemTouchHelper.attachToRecyclerView(mRecyclerTourist);
       // ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new SwipetoDeleteTouristonTreval(mAdapterTouristRecycler, getContext()));
        //mItemTouchHelper.attachToRecyclerView(mRecyclerTourist);
        mRecyclerTourist.setAdapter(mAdapterTouristRecycler);


    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNameTreval = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_edit_treval, container, false);
        mRealm = Realm.getDefaultInstance();
        mTreval = mRealm.where(Treval.class).equalTo("mName", mNameTreval).findFirst();
        mNameDirection = mTreval.getmDirection().get(0).getmName();
        mRealmTourists.clear();
        mRealmTourists.addAll(mTreval.getmTurists());


        //------------------------------------------------------------------------------------------
        initElements(mView);
        spinerDirection(mView);
        spinerTurist(mView);
        //------------------------------------------------------------------------------------------
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRealm.close();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditFragmentFragmentInteractionListener) {
            mListener = (OnEditFragmentFragmentInteractionListener) context;
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

            case R.id.id_Create_Edite_button_derection_text_view_Treval: {
                mListener.onEditFragmentFragmentInteraction("#createDirection", mNameTreval);

            }
            break;
            case R.id.id_create_button_tourist_text_view_Edite_Treval: {
                mListener.onEditFragmentFragmentInteraction("#createTourist", mNameTreval);

            }
            break;
            case R.id.id_create_treval_button_Edite_treval: {

                if (mDirectionPosition == -1) {
                    Toast.makeText(getContext(), getString(R.string.message_select_direction), Toast.LENGTH_LONG).show();
                } else {
                    mList.clear();
                    mList.add(mDirections.get(mDirectionPosition));
                    mRealm.beginTransaction();
                    mTreval.setmDirection(mList);
                    mTreval.setmTurists(mRealmTourists);
                    mRealm.commitTransaction();
                    mListener.onEditFragmentFragmentInteraction("#create", " ");
                }

            }
            break;
            case R.id.id_cancel_button_create_Edite_treval: {

                mListener.onEditFragmentFragmentInteraction("#cancel", " ");


            }
            break;

        }

    }



    public interface OnEditFragmentFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEditFragmentFragmentInteraction(String mComand, String mName);
    }
}
