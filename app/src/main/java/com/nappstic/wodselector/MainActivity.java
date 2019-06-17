package com.nappstic.wodselector;

import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.android.gms.ads.MobileAds;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    public WodDbAdapter mWodAdapter;
    private boolean isShowingBackLayout = false;
    private static final String AD_UNIT_ID = "ca-app-pub-1663798572028841~2577456177";




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new home_fragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new benchmarks_fragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = new tabata_creator_fragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, AD_UNIT_ID);
        mWodAdapter = new WodDbAdapter(this);




        try {
            mWodAdapter.createDataBase();
        }catch (IOException ioe){
            throw new Error("Unable to create database");
        }

        try{
            mWodAdapter.openDataBase();
        }catch (SQLException sqle){
            throw sqle;
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);





        loadFragment(new home_fragment());

    }

    protected void onDestroy (){
        super.onDestroy();
        if (mWodAdapter != null ){
            mWodAdapter.close();
        }
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    /*.setCustomAnimations(
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out)*/
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackStackChanged() {
        isShowingBackLayout = (getFragmentManager().getBackStackEntryCount() > 0);

    }


}
