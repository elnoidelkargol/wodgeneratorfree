package com.nappstic.wodselector;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class benchmarks_fragment_ArrayAdapter extends ArrayAdapter<Wod> {

    public benchmarks_fragment_ArrayAdapter(Context context, int resource, List<Wod> objects) {
        super(context, resource,objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        viewHolder mviewHolder;
        Wod mWod;

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.girls_list_row,parent,false);
        }

        mviewHolder = (viewHolder) convertView.getTag();
        if (mviewHolder == null){
            mviewHolder = new viewHolder(convertView);
            convertView.setTag(mviewHolder);
        }

        mWod = getItem(position);


        mviewHolder.nombre.setText(mWod.getNombre());

        return convertView;
    }

    static class viewHolder{
        TextView nombre = null;

        viewHolder (View row){
            nombre = row.findViewById(R.id.txtvwGirlName);
        }
    }
}
