package com.pingvn.trevalcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
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
    private CollapsingToolbarLayout mCollapsindTool;
    private AppBarLayout mAppBarLayout;
    private int mBackPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCollapsindTool = findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout = findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mCollapsindTool.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL);
        mCollapsindTool.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
        mCollapsindTool.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
        Realm.init(this);
        getSupportFragmentManager().beginTransaction().add(R.id.id_frame_container, new MainFragmentView()).commit();


    }

    @Override
    public void onFragmentInteraction(String mTrevalName, String mName) {
        mBackPressed = 0;

        switch (mTrevalName) {
            case "#createtreval": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#edit": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mName)).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
        }
    }


    @Override
    public void onFragmentAddTravelInteraction(String respone) {
        mBackPressed = 0;
        switch (respone) {
            case "#createDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddDirectionFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#createTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTouristFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#create": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mAppBarLayout.setExpanded(true);
            }
            break;
            case "#cancel": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mAppBarLayout.setExpanded(true);
            }
            break;
        }

    }

    @Override
    public void onFragmentAddDirectionInteraction(String respone) {
        mBackPressed = 0;
        switch (respone) {
            case "#create": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                    mAppBarLayout.setExpanded(false);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                    mAppBarLayout.setExpanded(false);
                }
            }
            break;
            case "#cancel": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                    mAppBarLayout.setExpanded(false);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                    mAppBarLayout.setExpanded(false);
                }
            }
            break;
        }

    }

    @Override
    public void onAddTouristFragmentInteraction(String respone) {
        mBackPressed = 0;
        switch (respone) {
            case "#create": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                    mAppBarLayout.setExpanded(false);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                    mAppBarLayout.setExpanded(false);
                }

            }
            break;
            case "#cancel": {
                if (mEditeTrevalName == " ") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTrevalFragment()).commit();
                    mAppBarLayout.setExpanded(false);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditTrevalFragment.newInstance(mEditeTrevalName)).commit();
                    mAppBarLayout.setExpanded(false);
                }
            }
            break;
        }
    }

    @Override
    public void onEditFragmentFragmentInteraction(String mCommand, String mName) {
        mBackPressed = 0;
        mEditeTrevalName = mName;
        switch (mCommand) {
            case "#createDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddDirectionFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#createTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AddTouristFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#create": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mEditeTrevalName = " ";
                mAppBarLayout.setExpanded(true);
            }
            break;
            case "#cancel": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mEditeTrevalName = " ";
                mAppBarLayout.setExpanded(true);
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
                mAppBarLayout.setExpanded(false);
            }
            break;
            case R.id.id_menu_edit_direction: {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditListsTuristandDirectionFragment.newInstance(EditListsTuristandDirectionFragment.DIRECTION_CHOISE)).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case R.id.id_menu_delete_all: {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new DeleteAllFragment()).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case R.id.id_menu_exit: {
                this.finish();
            }
            break;
            case R.id.id_menu_go_main :{
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
                mAppBarLayout.setExpanded(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteAllFragmentInteraction() {
        mBackPressed = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
        mAppBarLayout.setExpanded(true);
    }

    @Override
    public void onTuristAndDirecionFragmentInteraction(String mCommand, String mName) {
        mBackPressed = 0;
        switch (mCommand) {
            case "#editDirection": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditDirectionFragment.newInstance(mName)).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;
            case "#editeTourist": {
                getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, EditeTouristFragment.newInstance(mName)).commit();
                mAppBarLayout.setExpanded(false);
            }
            break;

        }

    }

    @Override
    public void onEditeDirectionFragmentInteraction(String message) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
        mAppBarLayout.setExpanded(true);
    }

    @Override
    public void onEditeTouristFragmentInteraction(String message) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_container, new MainFragmentView()).commit();
        mAppBarLayout.setExpanded(true);
    }
}
