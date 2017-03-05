package com.example.liamk.version3.Fragments;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liamk.version3.Adapters.MyAdapter;
import com.example.liamk.version3.Adapters.MyEventAdapter;
import com.example.liamk.version3.R;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventsFrag extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.eventfrag, container, false);

        RecyclerView rvEvent = (RecyclerView) view.findViewById(R.id.eventRecycle);
        rvEvent.setHasFixedSize(true);
        MyEventAdapter eventAdapter = new MyEventAdapter(new String[]{"Event 1", "Event 2", "Event 3", "Event 4", "Event 5", "Event 6"});
        rvEvent.setAdapter(eventAdapter);

        LinearLayoutManager llmEvent = new LinearLayoutManager(getActivity());
        rvEvent.setLayoutManager(llmEvent);

        return view;
    }
}
