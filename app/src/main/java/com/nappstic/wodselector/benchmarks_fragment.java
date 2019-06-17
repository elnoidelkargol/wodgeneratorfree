package com.nappstic.wodselector;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class benchmarks_fragment extends Fragment {

    private ListView mListView;
    private resultsDbAdapter mResultsDbAdapter;
    private benchmarks_fragment_ArrayAdapter mbenchmarksArrayAdapter;
    private TextView no_resuts;


    public benchmarks_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benchmarks, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getView().findViewById(R.id.lvwWodsnamelist);
        no_resuts = getView().findViewById(R.id.no_results_yet);

        mResultsDbAdapter = new resultsDbAdapter(getContext());
        mResultsDbAdapter.open();

        fillData();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wod mWod = (Wod) parent.getItemAtPosition(position);
                FragmentManager fragmentManager = getFragmentManager();
                Fragment mfragmentTimes = new wod_times_fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wod", mWod);
                bundle.putString("from","details");
                mfragmentTimes.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mfragmentTimes)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void fillData() {
        Cursor mWodsCursor = mResultsDbAdapter.fetchNameWodsWithResults();
        getActivity().startManagingCursor(mWodsCursor);
        List<Wod> mWods = new ArrayList<>();

        if (mWodsCursor.getCount() != 0) {
            mListView.setVisibility(View.VISIBLE);
            no_resuts.setVisibility(View.GONE);

            for (mWodsCursor.moveToFirst(); !mWodsCursor.isAfterLast(); mWodsCursor.moveToNext()) {
                Wod mTempWod = new Wod();

                mTempWod.set_id(mWodsCursor.getInt(mWodsCursor.getColumnIndexOrThrow("_id")));
                mTempWod.setNombre(mWodsCursor.getString(mWodsCursor.getColumnIndexOrThrow("nombre")));
                mTempWod.setResultUnit(mWodsCursor.getString(mWodsCursor.getColumnIndexOrThrow("result_unit")));
                mWods.add(mTempWod);
            }

            mbenchmarksArrayAdapter = new benchmarks_fragment_ArrayAdapter(getActivity(), R.layout.girls_list_row, mWods);
            mListView.setAdapter(mbenchmarksArrayAdapter);

        } else {

            mListView.setVisibility(View.GONE);
            no_resuts.setVisibility(View.VISIBLE);

        }
    }
}