package com.nappstic.wodselector;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class girls_fragment extends Fragment {

    private WodDbAdapter mWodAdapter;
    ListView lvWods;


    public girls_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvWods =  getView().findViewById(R.id.lvwGirlsList);
        mWodAdapter = new WodDbAdapter(getContext());
        mWodAdapter.open();
        String type = (String)this.getArguments().getSerializable("type");
        fillData(type);
        lvWods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wod mWod = (Wod) parent.getItemAtPosition(position);
                FragmentManager mfragmentManager = getFragmentManager();
                Fragment mfragmentDetail = new wodDetail_fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wod",mWod);
                mfragmentDetail.setArguments(bundle);


                mfragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mfragmentDetail,"Wod Detail")
                        .addToBackStack("Wod Detail")
                        .commit();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_girls_fragment, container, false);
    }

    private void fillData(String tipo){
        Cursor mGirlsCursor = mWodAdapter.fetchAllWods(tipo);
        getActivity().startManagingCursor(mGirlsCursor);
        List<Wod> girlsWod = new ArrayList<>();
        //DatabaseUtils.dumpCursor(mGirlsCursor);

        for (mGirlsCursor.moveToFirst();!mGirlsCursor.isAfterLast();mGirlsCursor.moveToNext()){
            Wod mTempWod = new Wod();

            mTempWod.set_id(mGirlsCursor.getInt(mGirlsCursor.getColumnIndexOrThrow( "_id")));
            mTempWod.setNombre(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "nombre")));
            mTempWod.setType(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "type")));
            mTempWod.setAmrap_time(mGirlsCursor.getInt(mGirlsCursor.getColumnIndexOrThrow( "amrap_time")));
            mTempWod.setResultUnit(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "result_unit")));
            mTempWod.setDescripcion(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "descripcion")));
            mTempWod.setPistols(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "pistols")));
            mTempWod.setBackExtensions(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "back_extensions")));
            mTempWod.setBenchPress(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "bench_press")));
            mTempWod.setBoxJump(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "box_jump")));
            mTempWod.setBurpees(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "burpees")));
            mTempWod.setClean(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "clean")));
            mTempWod.setCleanAndJerk(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "clean_and_jerk")));
            mTempWod.setRun(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "run")));
            mTempWod.setDeadlift(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "deadlift")));
            mTempWod.setDoubleUnders(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "double_unders")));
            mTempWod.setFrontSquat(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "front_squat")));
            mTempWod.setGhdSitUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "ghd_sit_ups")));
            mTempWod.setHandstandPushUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "handstand_push_ups")));
            mTempWod.setHangPowerClean(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "hang_power_clean")));
            mTempWod.setKettlebellSwing(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "kettlebell_swing")));
            mTempWod.setKneesToElbow(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "knees_to_elbow")));
            mTempWod.setLPullUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "l_pull_ups")));
            mTempWod.setMuscleUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "muscle_ups")));
            mTempWod.setOverheadSquat(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "overhead_squat")));
            mTempWod.setOverheadWalk(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "overhead_walk")));
            mTempWod.setPowerSnatch(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "power_snatch")));
            mTempWod.setPullUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "pull_ups")));
            mTempWod.setPushJerk(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "push_jerk")));
            mTempWod.setPushPress(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "push_press")));
            mTempWod.setPushUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "push_ups")));
            mTempWod.setRemo(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "remo")));
            mTempWod.setRingDips(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "ring_Dips")));
            mTempWod.setRopeClimb(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "rope_Climb")));
            mTempWod.setSitUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "sit_ups")));
            mTempWod.setSnatch(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "snatch")));
            mTempWod.setSquatClean(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "squat_clean")));
            mTempWod.setSquats(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "squats")));
            mTempWod.setSumoDeadliftHighPull(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "sumo_deadlift_high_pull")));
            mTempWod.setThruster(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "thruster")));
            mTempWod.setToesToBar(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "toes_to_bar")));
            mTempWod.setTurkishGetUps(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "turkish_get_ups")));
            mTempWod.setWalkingLunge(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "walking_lunge")));
            mTempWod.setWallBall(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "wall_ball")));

            girlsWod.add(mTempWod);

        }
        girls_fragment_ArrayAdapter mgirls_fragmentArrayAdapter = new girls_fragment_ArrayAdapter(getActivity(), R.layout.girls_list_row, girlsWod);
        lvWods.setAdapter(mgirls_fragmentArrayAdapter);
    }



}
