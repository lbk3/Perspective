package com.example.liamk.version3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liamk.version3.Adapters.MyTechniqueAdapter;
import com.example.liamk.version3.CardInfo.TechData;
import com.example.liamk.version3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamk on 22/01/2017.
 */
public class TechFrag extends Fragment{
    private MyTechniqueAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.techfrag, container, false);


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.techRecycle);
        rv.setHasFixedSize(true);
        adapter = new MyTechniqueAdapter(getActivity(), getData());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*MyTechniqueAdapter adapter = new MyTechniqueAdapter(new String[]{"Take a time out", "Countdown", "Meditation", "Positive Reinforcement", "4-7-8 Breathing" , "Breath Regulation" , "Aerobic Exercise", "Open Up"});
        rv.setAdapter(adapter);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);*/

        return view;
    }

    public static List<TechData> getData(){
        List<TechData> data = new ArrayList<>();
        int[] icons = {R.drawable.timeout, R.drawable.countdown, R.drawable.meditation, R.drawable.positive, R.drawable.sleep, R.drawable.breath, R.drawable.running, R.drawable.friend};
        String[] titles = {"Take a Time Out", "Count Down", "Meditation", "Positive Reinforcement", "4-7-8 Breathing", "Breath Regulation", "Aerobic Exercise", "Open Up"};
        for(int i=0;i<icons.length && i<titles.length; i++)
        {
            TechData current = new TechData();
            current.cardTitle = titles[i];
            current.imageID = icons[i];
            data.add(current);
        }
        return data;
    }

}
