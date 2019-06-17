package com.nappstic.wodselector;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class wod_times_fragment_rounds_ArrayAdapter extends ArrayAdapter<Result> {

    public wod_times_fragment_rounds_ArrayAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource,objects);
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        viewHolder mviewHolder;
        Result resultWod;

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.fragment_wod_times_row_rounds,parent,false);
        }

        mviewHolder = (viewHolder) convertView.getTag();
        if (mviewHolder == null){
            mviewHolder = new wod_times_fragment_rounds_ArrayAdapter.viewHolder(convertView);
            convertView.setTag(mviewHolder);
        }

        resultWod = getItem(position);

        mviewHolder.fecha.setText(resultWod.getFecha());
        mviewHolder.rounds.setText(resultWod.getRounds() + " " + getContext().getString(R.string.rounds) );

        return convertView;
    }

    static class viewHolder{
        TextView rounds = null;
        TextView fecha = null;

        viewHolder (View row){

            rounds = row.findViewById(R.id.txtViewResultsRounds);
            fecha = row.findViewById(R.id.TxtViewFecha);

        }
    }
}
