package com.nappstic.wodselector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class wod_times_fragment_seconds_ArrayAdapter extends ArrayAdapter<Result> {
    Wod mWod;

    public wod_times_fragment_seconds_ArrayAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource,objects);
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        viewHolder mviewHolder;
        Result resultWod;
        Integer segundos;
        String segundosString = null;
        Integer minutos;
        Integer resto;
        String ResultString = getContext().getString(R.string.result_text);

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.fragment_wod_times_row_seconds,parent,false);
        }

        mviewHolder = (viewHolder) convertView.getTag();
        if (mviewHolder == null){
            mviewHolder = new wod_times_fragment_seconds_ArrayAdapter.viewHolder(convertView);
            convertView.setTag(mviewHolder);
        }

        resultWod = getItem(position);

        segundos = Integer.parseInt(resultWod.getTime());

        minutos = segundos/60;
        segundos = segundos - (minutos*60);
        resto = segundos%60;

        if(resto>0){
            segundos = resto;
        }else{
            segundosString = "00";
        }

        if((segundos.toString().length()) == 1){
            segundosString = "0" + segundos;
        }else{
            segundosString = segundos.toString();
        }

        mviewHolder.fecha.setText(resultWod.getFecha());
        mviewHolder.minutes.setText(  minutos.toString() + ":");
        mviewHolder.seconds.setText(segundosString);

        return convertView;
    }

    static class viewHolder{
        TextView minutes = null;
        TextView seconds = null;
        TextView fecha = null;
        TextView title = null;

        viewHolder (View row){

            minutes = row.findViewById(R.id.txtViewResultsMinutes);
            seconds = row.findViewById(R.id.txtViewResultsSeconds);
            fecha = row.findViewById(R.id.TxtViewFecha);

        }
    }
}
