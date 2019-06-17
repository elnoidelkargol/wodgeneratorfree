package com.nappstic.wodselector;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
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


public class wodDetail_fragment extends Fragment {
    private static final String LOG_TAG = "WOD_DETAIL" ;

    private Wod mWod;
    private resultsDbAdapter mResultAdapter;
    private boolean cronoIniciado=false;
    private boolean cronoPausado=false;
    private Chronometer cronometro;
    private ImageButton btnPlayPause;
    private ImageButton btnStop;
    private Long timeWhenStoped;
    View dialogView = null;
    private Integer counter;
    private Integer counterToStart;
    private CountDownTimer mCountDownStart;
    private CountDownTimer mcountDownTimer;


    public wodDetail_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d("WOD DETAIL FRAGMENT", "onCreate: Hay argumentos");
        }else{
            Log.d(" WOD DETAIL FRAGMENT", "onCreate:No Hay argumentos");
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_wod_details,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId){
            /*case R.id.menu_results:
                flipCard();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        TextView txtViewNombre;
        TextView txtViewDescripcion;
        TextView txtTipo;

        timeWhenStoped = Long.valueOf(0);
        rootView = inflater.inflate(R.layout.fragment_wod_detail, container, false);

        txtViewNombre  = rootView.findViewById(R.id.detailNombrewod);
        txtTipo = rootView.findViewById(R.id.detailWodType);
        txtViewDescripcion = rootView.findViewById(R.id.detailWodDescription);

        btnPlayPause = rootView.findViewById(R.id.imgBtnPlayPause);
        btnStop = rootView.findViewById(R.id.imgBtnStop);

        cronometro = rootView.findViewById(R.id.mchronometer);

        mWod = (Wod)this.getArguments().getSerializable("wod");


        txtViewNombre.setText(mWod.getNombre());
        txtTipo.setText(mWod.getType());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtViewDescripcion.setText((Html.fromHtml(mWod.getDescripcion(), Html.FROM_HTML_MODE_COMPACT)));
        }else{
            txtViewDescripcion.setText(Html.fromHtml(mWod.getDescripcion()));

        }
        switch (mWod.getResultUnit()){
            case "time":
                cronometro.setVisibility(View.VISIBLE);

                btnPlayPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if((!cronoIniciado) && (!cronoPausado)){
                            startCountDown(null,cronometro);
                            //cronometro.start();
                            cronoIniciado=true;

                            btnPlayPause.setImageResource(R.drawable.ic_pause);

                        }else{
                            if(cronoIniciado){
                                cronometro.stop();
                                cronoPausado=true;
                                cronoIniciado = false;
                                timeWhenStoped = SystemClock.elapsedRealtime();
                                btnPlayPause.setImageResource(R.drawable.ic_play);

                                //Toast.makeText(getContext(),"Time:" + (SystemClock.elapsedRealtime() - cronometro.getBase()) ,Toast.LENGTH_LONG).show();

                            }else {
                                if(cronoPausado){
                                    cronoPausado = false;
                                    cronoIniciado = true;
                                    long intervalOnPause = (SystemClock.elapsedRealtime() - timeWhenStoped);
                                    cronometro.setBase( cronometro.getBase() + intervalOnPause );
                                    cronometro.start();
                                    btnPlayPause.setImageResource(R.drawable.ic_pause);
                                }
                            }
                        }
                    }
                });

                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Long milisegundos;
                        Long segundos;
                        Long minutos;
                        if (cronoIniciado){
                            cronometro.stop();
                            milisegundos = SystemClock.elapsedRealtime() - cronometro.getBase();
                            cronoPausado=true;
                            cronoIniciado = false;
                            timeWhenStoped = SystemClock.elapsedRealtime();
                            btnPlayPause.setImageResource(R.drawable.ic_play);
                        }else{
                            if (!cronoIniciado && !cronoPausado){
                                milisegundos = 0L;
                            }else{
                                milisegundos = timeWhenStoped - cronometro.getBase();
                        }   }
                        segundos = milisegundos / 1000 ;

                        minutos = segundos/60;
                        segundos = segundos - (minutos*60);
                        segundos = segundos%60;

                        showChangeLangDialog(minutos,segundos,null);
                        // Toast.makeText(getContext(),"Minutos =" +minutos + " Segundos: " + segundos,Toast.LENGTH_LONG).show();
                    }
                });
                break;

            case "rounds":
                final TextView mTxtViewCountDown  = rootView.findViewById(R.id.txtViewCountDown);
                final Integer minutes;
                final Integer seconds;

                seconds = 00;
                minutes = 00;
                mTxtViewCountDown.setVisibility(View.VISIBLE);
                counter = mWod.getAmrap_time();
                //counter = counter*60;
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
                                if(cronoPausado){
                                    cronoPausado = false;
                                    cronoIniciado = true;
                                    mcountDownTimer.start();
                                    btnPlayPause.setImageResource(R.drawable.ic_pause);
                                }
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

        return rootView;
    }
    private String seconds_to_time(Integer seconds){
        String time;
        Integer minutos;
        Integer resto;
        String segundosString;

        minutos = seconds/60;
        seconds = seconds - (minutos*60);
        resto = seconds%60;

        if(resto>0){
            seconds = resto;
        }else{
            segundosString = "00";
        }

        if((seconds.toString().length()) == 1){
            segundosString = "0" + seconds;
        }else{
            segundosString = seconds.toString();
        }


        time = minutos.toString() +":"+segundosString;
        return time;
    }
    private void flipCard() {
        FragmentManager fragmentManager =  getFragmentManager();
        Fragment mfragmentTimes = new wod_times_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("wod",mWod);
        bundle.putString("from","details");
        mfragmentTimes.setArguments(bundle);
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
        final LinearLayout lnrLyt = getActivity().findViewById(R.id.lnrLytDetailWod);

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
               // lnrLyt.setVisibility(View.VISIBLE);
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
            public void onClick(DialogInterface dialog, int whichButton) {
                mResultAdapter = new resultsDbAdapter(getContext());
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yy");
                String strDate = (mdformat.format(calendar.getTime())).toString();
                long Sqlres = 0;
                
                switch (mWod.getResultUnit()){
                    case "time":
                        final EditText Txtminutes = dialogView.findViewById(R.id.edTxtminutes);
                        final EditText Txtseconds = dialogView.findViewById(R.id.edTxtseconds);
                        Integer res = null;
                        int minutes;
                        int seconds;
                        int rounds;


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
    @Override

    public void onPause () {//PARCIALMENTE VISIBLE - INACTIVO

        super.onPause();
        Log.v(LOG_TAG, "onPause");
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
        Log.v(LOG_TAG, "onStop");
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
        Log.v(LOG_TAG, "onDestroyView");
    }

    @Override

    public void onDestroy () {//INVISIBLE

        super.onDestroy();
        Log.v(LOG_TAG, "onDestroy");
    }

    @Override

    public void onDetach () {//INVISIBLE

        super.onDetach();
        Log.v(LOG_TAG, "onDetach");
    }
}
