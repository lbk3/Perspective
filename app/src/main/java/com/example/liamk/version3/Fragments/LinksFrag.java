package com.example.liamk.version3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liamk.version3.Adapters.MyLinkAdapter;
import com.example.liamk.version3.R;

/**
 * Created by liamk on 22/01/2017.
 */
public class LinksFrag extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linksfrag, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.linkRecycle);
        rv.setHasFixedSize(true);
        MyLinkAdapter adapter = new MyLinkAdapter(new String[]{"MoodGym", "BetterHelp", "turn2me", "7cups", "NHS"});
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        // Inflate the layout for this fragment
        return view;
    }
}
