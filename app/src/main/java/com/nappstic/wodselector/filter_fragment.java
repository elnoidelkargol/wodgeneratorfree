package com.nappstic.wodselector;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


public class filter_fragment extends Fragment {


    // TODO: Rename and change types of parameters
    private Switch mswitchGilrs;
    private Switch mswitchHeroes;
    Switch mSwtchback_extensions;
    Switch mSwtchBear_crawl;
    Switch mSwtchbench_press;
    Switch mSwtchbox_jump;
    Switch mSwtchburpees;
    Switch mSwtchclean;
    Switch mSwtchchest_to_bar;
    Switch mSwtchclean_and_jerk;
    Switch mSwtchdeadlift;
    Switch mSwtchdouble_unders;
    Switch mSwtchfront_squat;
    Switch mSwtchghd_sit_ups;
    Switch mSwtchhandstand_push_ups;
    Switch mSwtchhang_power_clean;
    Switch mSwtchkettlebell_swing;
    Switch mSwtchknees_to_elbow;
    Switch mSwtchl_pull_ups;
    Switch mSwtchmuscle_ups;
    Switch mSwtchoverhead_squat;
    Switch mSwtchoverhead_walk;
    Switch mSwtchpistols;
    Switch mSwtchpower_snatch;
    Switch mSwtchpull_ups;
    Switch mSwtchpush_jerk;
    Switch mSwtchpush_press;
    Switch mSwtchpush_ups;
    Switch mSwtchrun;
    Switch mSwtchremo;
    Switch mSwtchring_Dips;
    Switch mSwtchring_hashtand_pushups;
    Switch mSwtchRomanian_deadlift;
    Switch mSwtchrope_Climb;
    Switch mSwtchsit_ups;
    Switch mSwtchsnatch;
    Switch mSwtchsquat_clean;
    Switch mSwtchsquats;
    Switch mSwtchsumo_deadlift_high_pull;
    Switch mSwtchthruster;
    Switch mSwtchtoes_to_bar;
    Switch mSwtchturkish_get_ups;
    Switch mSwtchwalking_lunge;
    Switch mSwtchwall_ball;
    Switch mSwtchyard_overhead_walk;
    Switch mSwtchTypeAll;
    Switch mSwtchCardioAll;
    Switch mSwtchHalteroAll;
    Switch mSwtchGymnasticsAll;

    private SharedPreferences.Editor mPrefEditor;

    private OnFragmentInteractionListener mListener;

    public filter_fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {

        setHasOptionsMenu(true);



        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filters,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accept_filters:
                flipCard();
                break;
        }
        return true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_filter_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences mSharedPrefs = this.getActivity().getSharedPreferences("filtersPrefs", Context.MODE_PRIVATE);
        mPrefEditor = mSharedPrefs.edit();

        mswitchGilrs =  getActivity().findViewById(R.id.switchFilterGirl);
        mswitchHeroes = getActivity().findViewById(R.id.switchFilterHeroe);
        mSwtchback_extensions = getActivity().findViewById(R.id.back_extensions);
        mSwtchBear_crawl = getActivity().findViewById(R.id.bear_crawl);
        mSwtchbench_press = getActivity().findViewById(R.id.bench_press);
        mSwtchbox_jump = getActivity().findViewById(R.id.box_jump);
        mSwtchburpees = getActivity().findViewById(R.id.burpees);
        mSwtchclean = getActivity().findViewById(R.id.clean);
        mSwtchchest_to_bar = getActivity().findViewById(R.id.chest_to_bar);
        mSwtchclean_and_jerk = getActivity().findViewById(R.id.clean_and_jerk);
        mSwtchdeadlift = getActivity().findViewById(R.id.deadlift);
        mSwtchdouble_unders = getActivity().findViewById(R.id.double_unders);
        mSwtchfront_squat = getActivity().findViewById(R.id.front_squat);
        mSwtchghd_sit_ups = getActivity().findViewById(R.id.ghd_sit_ups);
        mSwtchhandstand_push_ups = getActivity().findViewById(R.id.handstand_push_ups);
        mSwtchhang_power_clean = getActivity().findViewById(R.id.hang_power_clean);
        mSwtchkettlebell_swing = getActivity().findViewById(R.id.kettlebell_swing);
        mSwtchknees_to_elbow = getActivity().findViewById(R.id.knees_to_elbow);
        mSwtchl_pull_ups = getActivity().findViewById(R.id.l_pull_ups);
        mSwtchmuscle_ups = getActivity().findViewById(R.id.muscle_ups);
        mSwtchoverhead_squat = getActivity().findViewById(R.id.overhead_squat);
        mSwtchoverhead_walk = getActivity().findViewById(R.id.overhead_walk);
        mSwtchpistols = getActivity().findViewById(R.id.pistols);
        mSwtchpower_snatch = getActivity().findViewById(R.id.power_snatch);
        mSwtchpull_ups = getActivity().findViewById(R.id.pull_ups);
        mSwtchpush_jerk = getActivity().findViewById(R.id.push_jerk);
        mSwtchpush_press = getActivity().findViewById(R.id.push_press);
        mSwtchpush_ups = getActivity().findViewById(R.id.push_ups);
        mSwtchrun = getActivity().findViewById(R.id.run);
        mSwtchremo = getActivity().findViewById(R.id.row);
        mSwtchring_Dips = getActivity().findViewById(R.id.ring_dips);
        mSwtchring_hashtand_pushups = getActivity().findViewById(R.id.ring_hashtand_pushups);
        mSwtchRomanian_deadlift = getActivity().findViewById(R.id.romanian_deadlift);
        mSwtchrope_Climb = getActivity().findViewById(R.id.rope_climb);
        mSwtchsit_ups = getActivity().findViewById(R.id.sit_ups);
        mSwtchsnatch = getActivity().findViewById(R.id.snatch);
        mSwtchsquat_clean = getActivity().findViewById(R.id.squat_clean);
        mSwtchsquats = getActivity().findViewById(R.id.squats);
        mSwtchsumo_deadlift_high_pull = getActivity().findViewById(R.id.sumo_deadlift_high_pull);
        mSwtchthruster = getActivity().findViewById(R.id.thruster);
        mSwtchtoes_to_bar = getActivity().findViewById(R.id.toes_to_bar);
        mSwtchturkish_get_ups = getActivity().findViewById(R.id.turkish_get_ups);
        mSwtchwalking_lunge = getActivity().findViewById(R.id.walking_lunge);
        mSwtchwall_ball = getActivity().findViewById(R.id.wall_ball);
        mSwtchyard_overhead_walk = getActivity().findViewById(R.id.yard_overhead_walk);
        mSwtchTypeAll = getActivity().findViewById(R.id.switchAllType);
        mSwtchCardioAll = getActivity().findViewById(R.id.switchAllCardio);
        mSwtchHalteroAll = getActivity().findViewById(R.id.switchAllHaltero);
        mSwtchGymnasticsAll = getActivity().findViewById(R.id.switchAllGymnastics);

        mswitchGilrs.setChecked(mSharedPrefs.getBoolean("fGirls",true));
        mswitchHeroes.setChecked(mSharedPrefs.getBoolean("fHeroes",true));
        mSwtchback_extensions.setChecked(mSharedPrefs.getBoolean("back_extensions",true));
        mSwtchBear_crawl.setChecked(mSharedPrefs.getBoolean("Bear_crawl",true));
        mSwtchbench_press.setChecked(mSharedPrefs.getBoolean("bench_press",true));
        mSwtchbox_jump.setChecked(mSharedPrefs.getBoolean("box_jump",true));
        mSwtchburpees.setChecked(mSharedPrefs.getBoolean("burpees",true));
        mSwtchclean.setChecked(mSharedPrefs.getBoolean("clean",true));
        mSwtchchest_to_bar.setChecked(mSharedPrefs.getBoolean("chest_to_bar",true));
        mSwtchclean_and_jerk.setChecked(mSharedPrefs.getBoolean("clean_and_jerk",true));
        mSwtchdeadlift.setChecked(mSharedPrefs.getBoolean("deadlift",true));
        mSwtchdouble_unders.setChecked(mSharedPrefs.getBoolean("double_unders",true));
        mSwtchfront_squat.setChecked(mSharedPrefs.getBoolean("front_squat",true));
        mSwtchghd_sit_ups.setChecked(mSharedPrefs.getBoolean("ghd_sit_ups",true));
        mSwtchhandstand_push_ups.setChecked(mSharedPrefs.getBoolean("handstand_push_ups",true));
        mSwtchhang_power_clean.setChecked(mSharedPrefs.getBoolean("hang_power_clean",true));
        mSwtchkettlebell_swing.setChecked(mSharedPrefs.getBoolean("kettlebell_swing",true));
        mSwtchknees_to_elbow.setChecked(mSharedPrefs.getBoolean("knees_to_elbow",true));
        mSwtchl_pull_ups.setChecked(mSharedPrefs.getBoolean("l_pull_ups",true));
        mSwtchmuscle_ups.setChecked(mSharedPrefs.getBoolean("muscle_ups",true));
        mSwtchoverhead_squat.setChecked(mSharedPrefs.getBoolean("overhead_squat",true));
        mSwtchoverhead_walk.setChecked(mSharedPrefs.getBoolean("overhead_walk",true));
        mSwtchpistols.setChecked(mSharedPrefs.getBoolean("pistols",true));
        mSwtchpower_snatch.setChecked(mSharedPrefs.getBoolean("power_snatch",true));
        mSwtchpull_ups.setChecked(mSharedPrefs.getBoolean("pull_ups",true));
        mSwtchpush_jerk.setChecked(mSharedPrefs.getBoolean("push_jerk",true));
        mSwtchpush_press.setChecked(mSharedPrefs.getBoolean("push_press",true));
        mSwtchpush_ups.setChecked(mSharedPrefs.getBoolean("push_ups",true));
        mSwtchrun.setChecked(mSharedPrefs.getBoolean("run",true));
        mSwtchremo.setChecked(mSharedPrefs.getBoolean("remo",true));
        mSwtchring_Dips.setChecked(mSharedPrefs.getBoolean("ring_Dips",true));
        mSwtchring_hashtand_pushups.setChecked(mSharedPrefs.getBoolean("ring_hashtand_pushups",true));
        mSwtchRomanian_deadlift.setChecked(mSharedPrefs.getBoolean("Romanian_deadlift",true));
        mSwtchrope_Climb.setChecked(mSharedPrefs.getBoolean("rope_Climb",true));
        mSwtchsit_ups.setChecked(mSharedPrefs.getBoolean("sit_ups",true));
        mSwtchsnatch.setChecked(mSharedPrefs.getBoolean("snatch",true));
        mSwtchsquat_clean.setChecked(mSharedPrefs.getBoolean("squat_clean",true));
        mSwtchsquats.setChecked(mSharedPrefs.getBoolean("squats",true));
        mSwtchsumo_deadlift_high_pull.setChecked(mSharedPrefs.getBoolean("sumo_deadlift_high_pull",true));
        mSwtchthruster.setChecked(mSharedPrefs.getBoolean("thruster",true));
        mSwtchtoes_to_bar.setChecked(mSharedPrefs.getBoolean("toes_to_bar",true));
        mSwtchturkish_get_ups.setChecked(mSharedPrefs.getBoolean("turkish_get_ups",true));
        mSwtchwalking_lunge.setChecked(mSharedPrefs.getBoolean("walking_lunge",true));
        mSwtchwall_ball.setChecked(mSharedPrefs.getBoolean("wall_ball",true));
        mSwtchyard_overhead_walk.setChecked(mSharedPrefs.getBoolean("yard_overhead_walk",true));
        mSwtchTypeAll.setChecked(mSharedPrefs.getBoolean("typeall",true));
        mSwtchGymnasticsAll.setChecked(mSharedPrefs.getBoolean("gymnasticsAll",true));
        mSwtchHalteroAll.setChecked(mSharedPrefs.getBoolean("HalteroAll",true));
        mSwtchCardioAll.setChecked(mSharedPrefs.getBoolean("CardioAll",true));


        mSwtchCardioAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("CardioAll",isChecked);
                mPrefEditor.commit();
                mSwtchBear_crawl.setChecked(isChecked);
                mSwtchdouble_unders.setChecked(isChecked);
                mSwtchrun.setChecked(isChecked);
                mSwtchremo.setChecked(isChecked);

            }
        });

        mSwtchTypeAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("typeall",isChecked);
                mPrefEditor.commit();
                mswitchGilrs.setChecked(isChecked);
                mswitchHeroes.setChecked(isChecked);
            }
        });

        mSwtchHalteroAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("HalteroAll",isChecked);
                mPrefEditor.commit();
                mSwtchbench_press.setChecked(isChecked);
                mSwtchclean.setChecked(isChecked);
                mSwtchclean_and_jerk.setChecked(isChecked);
                mSwtchdeadlift.setChecked(isChecked);
                mSwtchfront_squat.setChecked(isChecked);
                mSwtchhang_power_clean.setChecked(isChecked);
                mSwtchkettlebell_swing.setChecked(isChecked);
                mSwtchoverhead_squat.setChecked(isChecked);
                mSwtchoverhead_walk.setChecked(isChecked);
                mSwtchpower_snatch.setChecked(isChecked);
                mSwtchpush_jerk.setChecked(isChecked);
                mSwtchpush_press.setChecked(isChecked);
                mSwtchRomanian_deadlift.setChecked(isChecked);
                mSwtchsnatch.setChecked(isChecked);
                mSwtchsquat_clean.setChecked(isChecked);
                mSwtchsumo_deadlift_high_pull.setChecked(isChecked);
                mSwtchthruster.setChecked(isChecked);
                mSwtchturkish_get_ups.setChecked(isChecked);
                mSwtchwall_ball.setChecked(isChecked);
                mSwtchyard_overhead_walk.setChecked(isChecked);
            }
        });

        mSwtchGymnasticsAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("gymnasticsAll",isChecked);
                mPrefEditor.commit();
                mSwtchback_extensions.setChecked(isChecked);
                mSwtchbox_jump.setChecked(isChecked);
                mSwtchburpees.setChecked(isChecked);
                mSwtchchest_to_bar.setChecked(isChecked);
                mSwtchghd_sit_ups.setChecked(isChecked);
                mSwtchhandstand_push_ups.setChecked(isChecked);
                mSwtchknees_to_elbow.setChecked(isChecked);
                mSwtchl_pull_ups.setChecked(isChecked);
                mSwtchmuscle_ups.setChecked(isChecked);
                mSwtchpistols.setChecked(isChecked);
                mSwtchpull_ups.setChecked(isChecked);
                mSwtchpush_ups.setChecked(isChecked);
                mSwtchring_Dips.setChecked(isChecked);
                mSwtchring_hashtand_pushups.setChecked(isChecked);
                mSwtchrope_Climb.setChecked(isChecked);
                mSwtchsit_ups.setChecked(isChecked);
                mSwtchsquats.setChecked(isChecked);
                mSwtchtoes_to_bar.setChecked(isChecked);
                mSwtchwalking_lunge.setChecked(isChecked);


            }
        });

        mswitchGilrs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("fGirls",isChecked);
                mPrefEditor.commit();
            }
        });

        mswitchHeroes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("fHeroes",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchback_extensions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("back_extensions",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchBear_crawl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("Bear_crawl",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchbench_press.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("bench_press",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchbox_jump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("box_jump",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchburpees.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("burpees",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchclean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("clean",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchchest_to_bar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("chest_to_bar",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchclean_and_jerk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("clean_and_jerk",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchdeadlift.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("deadlift",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchdouble_unders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("double_unders",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchfront_squat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("front_squat",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchghd_sit_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("ghd_sit_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchhandstand_push_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("handstand_push_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchhang_power_clean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("hang_power_clean",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchkettlebell_swing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("kettlebell_swing",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchknees_to_elbow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("knees_to_elbow",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchl_pull_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("l_pull_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchmuscle_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("muscle_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchoverhead_squat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("overhead_squat",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchoverhead_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("overhead_walk",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpistols.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("pistols",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpower_snatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("power_snatch",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpull_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("pull_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpush_jerk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("push_jerk",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpush_press.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("push_press",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchpush_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("push_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchrun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("run",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchremo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("remo",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchring_Dips.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("ring_Dips",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchring_hashtand_pushups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("ring_hashtand_pushups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchRomanian_deadlift.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("Romanian_deadlift",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchrope_Climb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("rope_Climb",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchsit_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("sit_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchsnatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("snatch",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchsquat_clean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("squat_clean",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchsquats.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("squats",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchsumo_deadlift_high_pull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("sumo_deadlift_high_pull",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchthruster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("thruster",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchtoes_to_bar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("toes_to_bar",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchturkish_get_ups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("turkish_get_ups",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchwalking_lunge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("walking_lunge",isChecked);
                mPrefEditor.commit();
            }
        });

        mSwtchwall_ball.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("wall_ball",isChecked);
                mPrefEditor.commit();
            }
        });

       mSwtchyard_overhead_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefEditor.putBoolean("yard_overhead_walk",isChecked);
                mPrefEditor.commit();
            }
        });



    }

    private void flipCard() {
        FragmentManager fragmentManager =  getFragmentManager();
        Fragment mfragmentTimes = new randomWodGenerator_fragment();

        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out
                        )
                .replace(R.id.fragment_container, mfragmentTimes)
                .addToBackStack(null)

                .commit();
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
