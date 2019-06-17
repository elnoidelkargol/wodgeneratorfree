package com.nappstic.wodselector;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {
    Fragment fragment;
    String fragmentName;
    Bundle bundle = new Bundle();
    private InterstitialAd mInterstitialAd;
    //private String unitId = "ca-app-pub-3940256099942544/1033173712";
    private String unitId = "ca-app-pub-1663798572028841/3148479422";


    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        RelativeLayout thegirls = getView().findViewById(R.id.relativeLytTheGirlsHome);
        RelativeLayout theheroes = getView().findViewById(R.id.relativeLytTheHeroesHome);
        RelativeLayout randomWod = getView().findViewById(R.id.relativeLytRandomHome);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(unitId);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        thegirls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        fragment = new girls_fragment();
                        bundle.putString("type", "girl");
                        fragment.setArguments(bundle);
                        fragmentName = "The girls";
                        if (fragment != null) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, fragment, fragmentName)
                                    .addToBackStack(fragmentName)
                                    .commit();

                        } else {
                            Log.e("HOMEFRAGMENT", "Error in creating The girls fragment");
                        }
                    }
                });

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();

                }else {

                    mInterstitialAd.show();
                    fragment = new girls_fragment();
                    bundle.putString("type", "girl");
                    fragment.setArguments(bundle);
                    fragmentName = "The girls";
                    if (fragment != null) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment, fragmentName)
                                .addToBackStack(fragmentName)
                                .commit();

                    } else {
                        Log.e("HOMEFRAGMENT", "Error in creating The girls fragment");
                    }
                }

            }

        });

        theheroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        fragment = new girls_fragment();

                        fragment = new girls_fragment();
                        bundle.putString("type", "heroe");
                        fragment.setArguments(bundle);
                        fragmentName = "The girls";
                        if (fragment != null) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, fragment, fragmentName)
                                    .addToBackStack(fragmentName)
                                    .commit();

                        } else {
                            Log.e("HOMEFRAGMENT", "Error in creating The girls fragment");
                        }

                    }
                });

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();

                } else {

                    mInterstitialAd.show();

                    fragment = new girls_fragment();
                    bundle.putString("type", "heroe");
                    fragment.setArguments(bundle);
                    fragmentName = "The girls";
                    if (fragment != null) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment, fragmentName)
                                .addToBackStack(fragmentName)
                                .commit();

                    } else {
                        Log.e("HOMEFRAGMENT", "Error in creating The girls fragment");
                    }
                }

            }
        });
        randomWod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        fragment = new randomWodGenerator_fragment();
                        fragmentName = "Random Wod";
                        if (fragment != null) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, fragment, fragmentName)
                                    .addToBackStack(fragmentName)
                                    .commit();

                        } else {
                            Log.e("HOMEFRAGMENT", "Error in creating The random fragment");
                        }


                    }
                });

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();

                } else {

                    mInterstitialAd.show();

                    fragment = new randomWodGenerator_fragment();
                    fragmentName = "Random Wod";
                    if (fragment != null) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment, fragmentName)
                                .addToBackStack(fragmentName)
                                .commit();

                    } else {
                        Log.e("HOMEFRAGMENT", "Error in creating The random fragment");
                    }

                }



            }


        });
    }
}
