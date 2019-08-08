package com.pingvn.trevalcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pingvn.trevalcalc.Fragments.AddDirectionFragment;
import com.pingvn.trevalcalc.Fragments.AddTouristFragment;
import com.pingvn.trevalcalc.Fragments.AddTrevalFragment;
import com.pingvn.trevalcalc.Fragments.DeleteAllFragment;
import com.pingvn.trevalcalc.Fragments.EditDirectionFragment;
import com.pingvn.trevalcalc.Fragments.EditListsTuristandDirectionFragment;
import com.pingvn.trevalcalc.Fragments.EditTrevalFragment;
import com.pingvn.trevalcalc.Fragments.EditeTouristFragment;
import com.pingvn.trevalcalc.Fragments.MainFragmentView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainFragmentView.OnMainFragmentInteractionListener,
        AddTrevalFragment.OnFragmentAddTrevalInteractionListener,
        AddDirectionFragment.OnFragmentAddDirectionInteractionListener,
        AddTouristFragment.OnFragmentAddTouristInteractionListener,
        EditTrevalFragment.OnEditFragmentFragmentInteractionListener,
        DeleteAllFragment.OnDeleteAllFragmentInteractionListener,
        EditListsTuristandDirectionFragment.OnTuristAndDirecionFragmentInteractionListener,
        EditeTouristFragment.OnEditeTouristFragmentInteractionListener,
        EditDirectionFragment.OnEditeDirectionFragmentInteractionListener {
    private String mEditeTrevalName = " ";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }
            }
            break;
            case "#cancel": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                } else {
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
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                }

            }
            break;
            case "#cancel": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                } else {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_edit_turist: {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditListsTuristandDirectionFragment.newInstance(EditListsTuristandDirectionFragment.TOURIST_CHOISE)).commit();
            }
            break;
            case R.id.id_menu_edit_direction: {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditListsTuristandDirectionFragment.newInstance(EditListsTuristandDirectionFragment.DIRECTION_CHOISE)).commit();
            }
            break;
            case R.id.id_menu_delete_all: {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new DeleteAllFragment()).commit();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteAllFragmentInteraction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
    }

    @Override
    public void onTuristAndDirecionFragmentInteraction(String mCommand, String mName) {
        switch (mCommand) {
            case "#editDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditDirectionFragment.newInstance(mName)).commit();
            }
            break;
            case "#editeTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditeTouristFragment.newInstance(mName)).commit();
            }
            break;

        }

    }

    @Override
    public void onEditeDirectionFragmentInteraction(String message) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();

    }

    @Override
    public void onEditeTouristFragmentInteraction(String message) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();

    }
}
