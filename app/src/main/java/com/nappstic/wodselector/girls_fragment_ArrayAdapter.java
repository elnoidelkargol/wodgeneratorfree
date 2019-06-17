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

public class girls_fragment_ArrayAdapter extends ArrayAdapter<Wod> {

    public girls_fragment_ArrayAdapter(Context context, int resource, List<Wod> objects) {
        super(context, resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        viewHolder mviewHolder;
        Wod wodGirl;

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.girls_list_row,parent,false);
        }

        mviewHolder = (viewHolder) convertView.getTag();
        if (mviewHolder == null){
            mviewHolder = new viewHolder(convertView);
            convertView.setTag(mviewHolder);
        }

        wodGirl = getItem(position);

        mviewHolder.nombre.setText(wodGirl.getNombre());

        return convertView;
    }

    static class viewHolder{
        TextView nombre;

        viewHolder (View row){
            nombre = row.findViewById(R.id.txtvwGirlName);
        }
    }
}
