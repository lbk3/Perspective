package com.example.liamk.version3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liamk on 22/01/2017.
 */
public class TechFrag extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.techfrag, container, false);

       RecyclerView rv = (RecyclerView) view.findViewById(R.id.techRecycle);
        rv.setHasFixedSize(true);
        MyLinkAdapter adapter = new MyLinkAdapter(new String[]{"Take a time out", "Countdown", "Meditation", "Positive Reinforcement", "4-7-8 Breathing" , "Breath Regulation" , "Aerobic Exercise", "Open Up"});
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return view;
    }

}
