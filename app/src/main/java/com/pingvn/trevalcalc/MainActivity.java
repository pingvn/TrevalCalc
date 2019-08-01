package com.pingvn.trevalcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.pingvn.trevalcalc.Fragments.AddDirectionFragment;
import com.pingvn.trevalcalc.Fragments.AddTouristFragment;
import com.pingvn.trevalcalc.Fragments.AddTrevalFragment;
import com.pingvn.trevalcalc.Fragments.EditTrevalFragment;
import com.pingvn.trevalcalc.Fragments.MainFragmentView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainFragmentView.OnMainFragmentInteractionListener,
        AddTrevalFragment.OnFragmentAddTrevalInteractionListener,
        AddDirectionFragment.OnFragmentAddDirectionInteractionListener,
        AddTouristFragment.OnFragmentAddTouristInteractionListener,
        EditTrevalFragment.OnEditFragmentFragmentInteractionListener {
    private String mEditeTrevalName = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);

        getSupportFragmentManager().beginTransaction().add(R.id.id_frame_container, new MainFragmentView()).commit();

    }

    @Override
    public void onFragmentInteraction(String mTrevalName, String mName) {

        switch (mTrevalName) {
            case "#createtreval": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
            }
            break;
            case "#edit": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mName)).commit();
            }
            break;
        }
    }


    @Override
    public void onFragmentAddTravelInteraction(String respone) {
        switch (respone) {
            case "#createDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddDirectionFragment()).commit();
            }
            break;
            case "#createTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTouristFragment()).commit();
            }
            break;
            case "#create": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
            }
            break;
            case "#cancel": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
            }
            break;
        }

    }

    @Override
    public void onFragmentAddDirectionInteraction(String respone) {
        switch (respone) {
            case "#create": {
                if(mEditeTrevalName == " "){
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }
            }
            break;
            case "#cancel": {
                if(mEditeTrevalName == " "){
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }
            }
            break;
        }

    }

    @Override
    public void onAddTouristFragmentInteraction(String respone) {
        switch (respone) {
            case "#create": {
                if(mEditeTrevalName == " "){
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }

            }
            break;
            case "#cancel": {
                if(mEditeTrevalName == " "){
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }
            }
            break;
        }
    }

    @Override
    public void onEditFragmentFragmentInteraction(String mCommand, String mName) {
        mEditeTrevalName = mName;
        switch (mCommand) {
            case "#createDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddDirectionFragment()).commit();
            }
            break;
            case "#createTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTouristFragment()).commit();
            }
            break;
            case "#create": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mEditeTrevalName = " ";
            }
            break;
            case "#cancel": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mEditeTrevalName = " ";
            }
            break;
        }

    }
}
