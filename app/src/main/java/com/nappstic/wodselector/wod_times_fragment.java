package com.nappstic.wodselector;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class wod_times_fragment extends Fragment {

    private ListView lstViewTimes;
    private Wod mWod;
    private resultsDbAdapter mresultsDbAdapter;
    private wod_times_fragment_seconds_ArrayAdapter mWodSecondsArrayAdapter;
    private Integer position;
    private String from;
    private TextView no_results;


    public wod_times_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        position = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_wod_detail:
                flipCard();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_wod_results, menu);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mWod = (Wod) this.getArguments().getSerializable("wod");
        position = this.getArguments().getInt("position");
        from = this.getArguments().getString("from");

        return inflater.inflate(R.layout.fragment_wod_times, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView titleWod = getView().findViewById(R.id.timesNombreWod);
        no_results = getView().findViewById(R.id.no_results_yet_wod);
        lstViewTimes = getView().findViewById(R.id.lstvwWodTimes);
        mresultsDbAdapter = new resultsDbAdapter(getContext());
        mresultsDbAdapter.open();

        titleWod.setText(mWod.getNombre());
        fillData();

    }

    private void fillData() {
        Cursor mCursorResults = mresultsDbAdapter.fetchResults(mWod.get_id());
        getActivity().startManagingCursor(mCursorResults);
        List<Result> mResults = new ArrayList<>();
        if (mCursorResults.getCount() != 0) {
            for (mCursorResults.moveToFirst(); !mCursorResults.isAfterLast(); mCursorResults.moveToNext()) {
                Result mTempResult = new Result();

                mTempResult.set_id(mCursorResults.getString(mCursorResults.getColumnIndexOrThrow("_id")));
                mTempResult.setFecha(mCursorResults.getString(mCursorResults.getColumnIndexOrThrow("fecha")));
                mTempResult.setRounds(mCursorResults.getString(mCursorResults.getColumnIndexOrThrow("rounds")));
                mTempResult.setTime(mCursorResults.getString(mCursorResults.getColumnIndexOrThrow("time")));
                mTempResult.setWodId(mCursorResults.getString(mCursorResults.getColumnIndexOrThrow("id_wod")));

                mResults.add(mTempResult);
            }
            switch (mWod.getResultUnit()) {
                case "time":
                    mWodSecondsArrayAdapter = new wod_times_fragment_seconds_ArrayAdapter(getActivity(), R.layout.fragment_wod_times_row_seconds, mResults);
                    lstViewTimes.setAdapter(mWodSecondsArrayAdapter);

                    break;
                case "rounds":
                    wod_times_fragment_rounds_ArrayAdapter mWodRoundsArrayAdapter = new wod_times_fragment_rounds_ArrayAdapter(getActivity(), R.layout.fragment_wod_times_row_rounds, mResults);
                    lstViewTimes.setAdapter(mWodRoundsArrayAdapter);

                    break;
            }
        } else {
            no_results.setVisibility(View.VISIBLE);
            lstViewTimes.setVisibility(View.GONE);
        }
    }

    private void flipCard() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment mFragment;
        if (from.equals("random")) {
            Bundle mBundle = new Bundle();
            mBundle.putString("from", "results");
            mBundle.putInt("position", position);
            mFragment = new randomWodGenerator_fragment();

            mFragment.setArguments(mBundle);

            assert fragmentManager != null;
            fragmentManager.beginTransaction()
                    // Replace the default fragment animations with animator resources representing
                    // rotations when switching to the back of the card, as well as animator
                    // resources representing rotations when flipping back to the front (e.g. when
                    // the system Back button is pressed).
                    .setCustomAnimations(
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out,
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out)

                    // Replace any fragments currently in the container view with a fragment
                    // representing the next page (indicated by the just-incremented currentPage
                    // variable).
                    .replace(R.id.fragment_container, mFragment)

                    // Add this transaction to the back stack, allowing users to press Back
                    // to get to the front of the card.
                    .addToBackStack(null)

                    .commit();

            Log.d("POSITION", "flipCard: position: " + position);
        } else {
            fragmentManager.popBackStack();

        }
    }


}
