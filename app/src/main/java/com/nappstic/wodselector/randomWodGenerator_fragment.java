package com.nappstic.wodselector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class randomWodGenerator_fragment extends Fragment {
    Button btnWodGenerator;

    private WodDbAdapter mWodAdapter;
    private Boolean exerciseSelected = false;
    private Wod mWod;
    private View dialogView = null;
    private Menu mMenu;
    private Cursor mCursorWods;
    private Integer totalWods;
    private Integer random;
    private TextView txtNombreWod;
    private TextView txtTipoWod;
    private TextView txtDescripcionWod;
    private TextView txtRandomInfo;
    private TextView txtPosibleWods;
    private TextView txtTimer;
    private Chronometer mChronometer;
    private LinearLayout lnrLytWod;
    private LinearLayout lnrLytTimer;
    private LinearLayout lnrLytWodTitle;
    private MenuItem btnResult;
    private ImageButton btnPlayPause;
    private ImageButton btnStop;
    private boolean cronoIniciado = false;
    private boolean cronoPausado = false;
    private int counterToStart;
    private long timeWhenStoped;
    private Integer counter;
    private String textTotalWods;
    private CountDownTimer mcountDownTimer;
    private CountDownTimer mCountDownStart;
    public randomWodGenerator_fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

                rootView = inflater.inflate(R.layout.randomwodgenerator_fragment,container,false);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.random_wod_filter:
                    flipCard("filters");
                break;
            /*case R.id.random_menu_results:
                    flipCard("results");
                break;*/
        }
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        mMenu = menu;
        Bundle mBundle = getArguments();
        super.onPrepareOptionsMenu(menu);
        if (mBundle != null && mBundle.getString("from").equals("results")) {

            //btnResult = mMenu.findItem(R.id.random_menu_results);
            //btnResult.setVisible(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_wod_random,menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        TextView txtNumWods;
        Bundle mBundle = getArguments();
        super.onActivityCreated(savedInstanceState);

        lnrLytWod = getView().findViewById(R.id.lnrLytDetailWodRandomWod);
        lnrLytWod.setVisibility(View.GONE);

        mWodAdapter = new WodDbAdapter(getContext());
        mWodAdapter.openDataBase();

        txtTimer = getView().findViewById(R.id.txtViewCountDownRandomWod);

        btnWodGenerator = getView().findViewById(R.id.btnRandomGenerator);
        btnPlayPause = getView().findViewById(R.id.imgBtnPlayPauseRandomWod);
        btnStop = getView().findViewById(R.id.imgBtnStopRandomWod);

        mChronometer = getView().findViewById(R.id.mchronometerRandomWod);
        mCursorWods = getRandomWod();

        if (mBundle != null && mBundle.getString("from").equals("results")){


            random = mBundle.getInt("position");

            totalWods = mCursorWods.getCount();

            mCursorWods.moveToPosition(random);

            mWod = new Wod();

            mWod.setDescripcion(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("descripcion")));
            mWod.setNombre(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("nombre")));
            mWod.setType(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("type")));
            mWod.setResultUnit(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("result_unit")));
            mWod.set_id(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("_id")));
            mWod.setAmrap_time(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("amrap_time")));

            txtPosibleWods = getView().findViewById(R.id.txtViewInfoPosibleWods);
            txtRandomInfo =  getView().findViewById(R.id.txtViewInfoRandom);
            txtDescripcionWod =  getView().findViewById(R.id.txtViewdetailWodDescription);
            txtNombreWod =  getView().findViewById(R.id.txtViewdetailNombrewod) ;
            txtTipoWod =  getView().findViewById(R.id.txtViewdetailWodType);
            lnrLytWod =  getView().findViewById(R.id.lnrLytRandom);
            lnrLytTimer = getActivity().findViewById(R.id.lnrLytTimerRandomWod);
            lnrLytWodTitle = getActivity().findViewById(R.id.wodHeaderRandomWodDetail);

           /* lnrLytWod.setVisibility(View.VISIBLE);
            txtNombreWod.setVisibility(View.VISIBLE);
            txtRandomInfo.setVisibility(View.GONE);
            txtPosibleWods.setVisibility(View.GONE);
            lnrLytTimer.setVisibility(View.VISIBLE);
            lnrLytWodTitle.setVisibility(View.VISIBLE);*/
            /*txtRandomInfo.setVisibility(View.GONE);
            lnrLytWod.setVisibility(View.VISIBLE);
            txtPosibleWods.setVisibility(View.GONE);
            lnrLytTimer.setVisibility(View.VISIBLE);*/


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion(), Html.FROM_HTML_MODE_COMPACT));
            }else{
                txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion()));

            }

            txtNombreWod.setText(mWod.getNombre());
            txtTipoWod.setText(mWod.getType());
            if(mWod.getResultUnit().equals("time")){
                mChronometer.setVisibility(View.VISIBLE);
                txtTimer.setVisibility(View.GONE);
            }else{
                if (mWod.getResultUnit().equals("rounds")){
                    txtTimer.setText(seconds_to_time(mWod.getAmrap_time()));
                    mChronometer.setVisibility(View.GONE);
                    txtTimer.setVisibility(View.VISIBLE);
                }
            }


        }else{

            if (mCursorWods != null) {
                totalWods = mCursorWods.getCount();
            }else {
                totalWods = 0;
            }
            txtNumWods =  getView().findViewById(R.id.txtViewInfoPosibleWods);
            textTotalWods = txtNumWods.getText().toString() + " " + totalWods.toString();
            txtNumWods.setText(textTotalWods);
        }


        btnWodGenerator.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if (mCountDownStart != null){

                    mCountDownStart.cancel();
                }
                if (mcountDownTimer != null){

                    mcountDownTimer.cancel();
                }
                getActivity().startManagingCursor(mCursorWods);
                if (totalWods > 0){
                    lnrLytWod.setVisibility(View.VISIBLE);
                    random = new Random().nextInt(totalWods);
                    mCursorWods.moveToPosition(random);
                    mWod = new Wod();
                    mWod.setDescripcion(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("descripcion")));
                    mWod.setNombre(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("nombre")));
                    mWod.setType(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("type")));
                    mWod.setResultUnit(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("result_unit")));
                    mWod.setAmrap_time(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("amrap_time")));
                    mWod.set_id(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("_id")));

                    txtPosibleWods =  getView().findViewById(R.id.txtViewInfoPosibleWods);
                    txtRandomInfo =  getView().findViewById(R.id.txtViewInfoRandom);
                    txtDescripcionWod =  getView().findViewById(R.id.txtViewdetailWodDescription);
                    txtNombreWod =  getView().findViewById(R.id.txtViewdetailNombrewod) ;
                    txtTipoWod =  getView().findViewById(R.id.txtViewdetailWodType);
                    lnrLytWod =  getView().findViewById(R.id.lnrLytRandom);
                    lnrLytTimer = getView().findViewById(R.id.lnrLytTimerRandomWod);
                    //btnResult = mMenu.findItem(R.id.random_menu_results) ;

                    txtRandomInfo.setVisibility(View.GONE);
                    lnrLytWod.setVisibility(View.VISIBLE);
                    txtPosibleWods.setVisibility(View.GONE);
                    lnrLytTimer.setVisibility(View.VISIBLE);
                    //btnResult.setEnabled(true);

                    mChronometer.setBase(SystemClock.elapsedRealtime());

                    cronoIniciado = false;
                    cronoPausado = false;
                    btnPlayPause.setImageResource(R.drawable.ic_play);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion(), Html.FROM_HTML_MODE_COMPACT));
                    }else{
                        txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion()));

                    }

                    txtNombreWod.setText(mWod.getNombre());
                    txtTipoWod.setText(mWod.getType());
                    if(mWod.getResultUnit().equals("time")){

                        mChronometer.setVisibility(View.VISIBLE);
                        txtTimer.setVisibility(View.GONE);
                    }else{
                        if (mWod.getResultUnit().equals("rounds")){
                            txtTimer.setText(seconds_to_time(mWod.getAmrap_time()));
                            mChronometer.setVisibility(View.GONE);
                            txtTimer.setVisibility(View.VISIBLE);
                        }
                    }

                    switch (mWod.getResultUnit()){
                        case "time":


                            btnPlayPause.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if((!cronoIniciado) && (!cronoPausado)){
                                        mChronometer.setBase(SystemClock.elapsedRealtime());
                                        startCountDown(null,mChronometer);
                                        cronoIniciado=true;

                                        btnPlayPause.setImageResource(R.drawable.ic_pause);

                                    }else{
                                        if(cronoIniciado){
                                            mChronometer.stop();
                                            cronoPausado=true;
                                            cronoIniciado = false;
                                            timeWhenStoped = SystemClock.elapsedRealtime();
                                            btnPlayPause.setImageResource(R.drawable.ic_play);

                                        }else {
                                            cronoPausado = false;
                                            cronoIniciado = true;
                                            long intervalOnPause = (SystemClock.elapsedRealtime() - timeWhenStoped);
                                            mChronometer.setBase( mChronometer.getBase() + intervalOnPause );
                                            mChronometer.start();
                                            btnPlayPause.setImageResource(R.drawable.ic_pause);
                                        }
                                    }
                                }
                            });

                            btnStop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    long milisegundos;
                                    long segundos;
                                    long minutos;
                                    if(!cronoIniciado && !cronoPausado){
                                        milisegundos = 0;
                                    }else{

                                        if (cronoIniciado){
                                            mChronometer.stop();
                                            milisegundos = SystemClock.elapsedRealtime() - mChronometer.getBase();
                                            cronoPausado=true;
                                            cronoIniciado = false;
                                            timeWhenStoped = SystemClock.elapsedRealtime();
                                            btnPlayPause.setImageResource(R.drawable.ic_play);
                                        }else{

                                            milisegundos = timeWhenStoped - mChronometer.getBase();
                                        }
                                    }
                                    segundos = milisegundos / 1000 ;

                                    minutos = segundos/60;
                                    segundos = segundos - (minutos*60);
                                    segundos = segundos%60;

                                    showChangeLangDialog(minutos,segundos,null);
                                }
                            });
                            break;

                        case "rounds":
                            final TextView mTxtViewCountDown  = getView().findViewById(R.id.txtViewCountDownRandomWod);
                            mTxtViewCountDown.setVisibility(View.VISIBLE);
                            counter = mWod.getAmrap_time();
                            //counter = 68;

                            mTxtViewCountDown.setText(seconds_to_time(counter));

                            mcountDownTimer = new CountDownTimer(counter*1000,1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    MediaPlayer mp;

                                    counter--;
                                    mTxtViewCountDown.setText(String.valueOf(seconds_to_time(counter)));
                                    if (counter == 60){
                                        switch (Locale.getDefault().getLanguage()){
                                            case"en":
                                                mp = MediaPlayer.create(getActivity(), R.raw.last_minute);
                                                break;
                                            case "es":
                                                mp = MediaPlayer.create(getActivity(), R.raw.ultimo_minuto);
                                                break;
                                            case "ca":
                                                mp = MediaPlayer.create(getActivity(), R.raw.ultim_minut);
                                                break;
                                            default:
                                                mp = MediaPlayer.create(getActivity(), R.raw.last_minute);
                                                break;

                                        }
                                        mp.start();


                                    }
                                    if(counter == 10){
                                        mp = MediaPlayer.create(getActivity(),R.raw.countdownfinish );
                                        mp.start();
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    mTxtViewCountDown.setText(seconds_to_time(mWod.getAmrap_time()));
                                    cronoIniciado = false;
                                    cronoPausado = false;
                                    btnPlayPause.setImageResource(R.drawable.ic_play);
                                    showChangeLangDialog(null,null,null);
                                }
                            };

                            btnPlayPause.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if((!cronoIniciado) && (!cronoPausado)){
                                        startCountDown(mcountDownTimer,null);
                                        //mcountDownTimer.start();
                                        cronoIniciado=true;
                                        btnPlayPause.setImageResource(R.drawable.ic_pause);

                                    }else{
                                        if(cronoIniciado){
                                            mcountDownTimer.cancel();
                                            cronoPausado=true;
                                            cronoIniciado = false;
                                            btnPlayPause.setImageResource(R.drawable.ic_play);

                                        }else {
                                            cronoPausado = false;
                                            cronoIniciado = true;
                                            mcountDownTimer.start();
                                            btnPlayPause.setImageResource(R.drawable.ic_pause);
                                        }
                                    }
                                }
                            });

                            btnStop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                        mcountDownTimer.cancel();

                                        cronoPausado = true;
                                        cronoIniciado = false;
                                        showChangeLangDialog(null, null, null);
                                        btnPlayPause.setImageResource(R.drawable.ic_play);


                                }
                            });
                            break;
                    }


                }else{
                    Toast.makeText(getContext(),getString(R.string.no_type_selected),Toast.LENGTH_LONG).show();
                }


            }
        });


    }

//    public void showWodDetail(Integer position){
//        TextView txtNombreWod;
//        TextView txtTipoWod;
//        TextView txtDescripcionWod;
//        TextView txtRandomInfo;
//        TextView txtPosibleWods;
//        Button FltBtnAdd;
//        LinearLayout lnrLytWod;
//        MenuItem btnResult;
//
//        mCursorWods.moveToPosition(position);
//
//        mWod = new Wod();
//
//        mWod.setDescripcion(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("descripcion")));
//        mWod.setNombre(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("nombre")));
//        mWod.setType(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("type")));
//        mWod.setResultUnit(mCursorWods.getString(mCursorWods.getColumnIndexOrThrow("result_unit")));
//        mWod.set_id(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("_id")));
//        mWod.setAmrap_time(mCursorWods.getInt(mCursorWods.getColumnIndexOrThrow("amrap_time")));
//
//        txtPosibleWods = (TextView) getView().findViewById(R.id.txtViewInfoPosibleWods);
//        txtRandomInfo = (TextView) getView().findViewById(R.id.txtViewInfoRandom);
//        txtDescripcionWod = (TextView) getView().findViewById(R.id.txtViewdetailWodDescription);
//        txtNombreWod = (TextView) getView().findViewById(R.id.txtViewdetailNombrewod) ;
//        txtTipoWod = (TextView) getView().findViewById(R.id.txtViewdetailWodType);
//        //FltBtnAdd = (Button) getView().findViewById(R.id.btn_add_result_generator);
//        lnrLytWod = (LinearLayout) getView().findViewById(R.id.lnrLytRandom);
//        btnResult = mMenu.findItem(R.id.random_menu_results) ;
//
//        txtRandomInfo.setVisibility(View.GONE);
//        lnrLytWod.setVisibility(View.VISIBLE);
//        txtPosibleWods.setVisibility(View.GONE);
//        btnResult.setVisible(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion(), Html.FROM_HTML_MODE_COMPACT));
//        }else{
//            txtDescripcionWod.setText(Html.fromHtml(mWod.getDescripcion()));
//
//        }
//
//        txtNombreWod.setText(mWod.getNombre());
//        txtTipoWod.setText(mWod.getType());
//        //FltBtnAdd.setVisibility(View.VISIBLE);
//
//        FltBtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showChangeLangDialog();
//            }
//        });
//
//    }
public void showChangeLangDialog(Long minutos , Long segundos, Integer rounds) {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    switch (mWod.getResultUnit()){
        case "time":
            EditText txtMinutos;
            EditText txtSegundos;
            dialogView = inflater.inflate(R.layout.layout_add_result_time, null);

            txtMinutos = dialogView.findViewById(R.id.edTxtminutes);
            txtSegundos = dialogView.findViewById(R.id.edTxtseconds);

            txtMinutos.setText(minutos.toString());
            txtSegundos.setText(segundos.toString());

            break;

        case "rounds":
            dialogView = inflater.inflate(R.layout.layout_add_result_rounds, null);
            break;
    }

    dialogBuilder.setView(dialogView);


    dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
        private resultsDbAdapter mResultAdapter;

        public void onClick(DialogInterface dialog, int whichButton) {
            mResultAdapter = new resultsDbAdapter(getContext());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yy");
            String strDate = (mdformat.format(calendar.getTime()));
            long Sqlres = 0;

            switch (mWod.getResultUnit()){
                case "time":
                    final EditText Txtminutes = dialogView.findViewById(R.id.edTxtminutes);
                    final EditText Txtseconds = dialogView.findViewById(R.id.edTxtseconds);
                    Integer res = null;
                    int minutes;
                    int seconds;
                    int rounds;

                    //Log.d("WODDETAILFRAG", "onClick: FECHA " + strDate);

                    if (Txtminutes.getText().toString().equals("")){
                        minutes = 0;
                    }else {

                        minutes = Integer.parseInt(Txtminutes.getText().toString());
                    }

                    if (Txtseconds.getText().toString().equals("")){
                        seconds = 0;
                    }else {

                        seconds = Integer.parseInt(Txtseconds.getText().toString());
                    }
                    res = (minutes*60)+seconds;
                    if (res > 0) {
                        Sqlres = mResultAdapter.addResult(mWod.get_id(), null, res, strDate);
                    }                        break;
                case "rounds":
                    final EditText txtRounds = dialogView.findViewById(R.id.edTxtrounds);

                    if (txtRounds.getText().toString().equals("")){
                        rounds = -1;
                    }else{

                        rounds = Integer.parseInt(txtRounds.getText().toString());
                    }

                    if(rounds > 0){

                        Sqlres = mResultAdapter.addResult(mWod.get_id(),rounds,null,strDate);
                    }

                    break;
            }
            Log.d("WODDETAILFRAGM", "onClick: Resultado insersion" + Sqlres);
        }


    });
    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            //pass
        }
    });
    AlertDialog b = dialogBuilder.create();
    b.show();
}
    private String seconds_to_time(Integer seconds){
        String time;
        int minutos;
        Integer resto;
        String segundosString;

        minutos = seconds/60;
        seconds = seconds - (minutos*60);
        resto = seconds%60;

        if(resto>0){
            seconds = resto;
        }

        if((seconds.toString().length()) == 1){
            segundosString = "0" + seconds;
        }else{
            segundosString = seconds.toString();
        }


        time = Integer.toString(minutos) +":"+segundosString;
        return time;
    }
    private Cursor getRandomWod (){
        SharedPreferences mPreferences;
        Cursor mCursor = null;

        mPreferences = getActivity().getSharedPreferences("filtersPrefs", Context.MODE_PRIVATE);
        exerciseSelected = mPreferences.getBoolean("back_extensions", true);
        if(mPreferences.getBoolean("Bear_crawl",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("bench_press",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("box_jump",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("burpees",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("clean",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("chest_to_bar",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("clean_and_jerk",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("deadlift",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("double_unders",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("front_squat",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("ghd_sit_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("handstand_push_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("hang_power_clean",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("kettlebell_swing",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("knees_to_elbow",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("l_pull_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("muscle_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("overhead_squat",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("overhead_walk",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("pistols",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("power_snatch",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("pull_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("push_jerk",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("push_press",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("push_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("run",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("remo",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("ring_Dips",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("ring_hashtand_pushups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("Romanian_deadlift",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("rope_Climb",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("sit_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("snatch",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("squat_clean",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("squats",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("sumo_deadlift_high_pull",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("thruster",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("toes_to_bar",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("turkish_get_ups",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("walking_lunge",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("wall_ball",true)){
            exerciseSelected = true;
        }
        if(mPreferences.getBoolean("yard_overhead_walk",true)){
            exerciseSelected = true;
        }
        if ( (mPreferences.getBoolean("fGirls",true)) || (mPreferences.getBoolean("fHeroes",true))){
            if(exerciseSelected){

                //String num = random.toString();
                //mCursor = mWodDbAdapter.fetchWod(num);
                mCursor = mWodAdapter.fetchFilteredWods();


                /*
                *
                * mTempWod.set_id(mGirlsCursor.getInt(mGirlsCursor.getColumnIndexOrThrow( "_id")));
            mTempWod.setNombre(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "nombre")));
            mTempWod.setType(mGirlsCursor.getString(mGirlsCursor.getColumnIndexOrThrow( "type")));
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

                * */

            }else {
                Toast.makeText(getContext(),R.string.no_exercice_selected,Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(),R.string.no_type_selected,Toast.LENGTH_LONG).show();

        }
        return mCursor;
    }
    private void flipCard(String fragmentName) {
        FragmentManager fragmentManager =  getFragmentManager();
        Fragment mfragmentTimes = null;

        switch (fragmentName){
            case "filters":
                mfragmentTimes = new filter_fragment();
                break;
            case "results":
                mfragmentTimes = new wod_times_fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wod",mWod);
                bundle.putInt("position",random);
                bundle.putString("from","random");
                mfragmentTimes.setArguments(bundle);
                break;
        }

        fragmentManager.beginTransaction()
                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).
                .replace(R.id.fragment_container, mfragmentTimes)

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                .commit();
    }
    private void startCountDown (final CountDownTimer mCountDownTimerWod , final Chronometer mCronometerWod){
        counterToStart = 6;
        final LinearLayout lnrLyt = getActivity().findViewById(R.id.lnrLytDetailWodRandomWod);

        final TextView txtViewCounterDown = getActivity().findViewById(R.id.txtViewStartCountdown);
        final MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.countdownstart);

        txtViewCounterDown.setVisibility(View.VISIBLE);

        mCountDownStart = new CountDownTimer(counterToStart*1000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                counterToStart--;
                txtViewCounterDown.setText(String.valueOf((counterToStart)));

                if (counterToStart ==3){
                    final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.countdownstart);
                    mp.start();
                }

            }

            @Override
            public void onFinish() {
                txtViewCounterDown.setText("GO!");
                txtViewCounterDown.setVisibility(View.GONE);
                //lnrLyt.setVisibility(View.VISIBLE);
                if (mCountDownTimerWod != null){
                    mCountDownTimerWod.start();
                }else {
                    if (mCronometerWod != null){
                        mCronometerWod.setBase(SystemClock.elapsedRealtime());
                        mCronometerWod.start();
                    }
                }
            }
        };
        mCountDownStart.start();
        //lnrLyt.setVisibility(View.GONE);


    }

    @Override

    public void onStop () {//INVISIBLE
        super.onStop();
        if (mCountDownStart != null){

            mCountDownStart.cancel();
        }
        if (mcountDownTimer != null){

            mcountDownTimer.cancel();
        }
    }

    @Override

    public void onDestroyView () {//INVISIBLE

        super.onDestroyView();
        if (mCountDownStart != null){

            mCountDownStart.cancel();
        }
        if (mcountDownTimer != null){

            mcountDownTimer.cancel();
        }
    }
}
