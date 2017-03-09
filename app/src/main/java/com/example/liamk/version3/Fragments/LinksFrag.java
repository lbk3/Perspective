package com.example.liamk.version3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liamk.version3.Adapters.MyLinkAdapter;
import com.example.liamk.version3.CardInfo.LinkData;
import com.example.liamk.version3.CardInfo.TechData;
import com.example.liamk.version3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamk on 22/01/2017.
 */
public class LinksFrag extends Fragment {
    private MyLinkAdapter linkAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linksfrag, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.linkRecycle);
        rv.setHasFixedSize(true);
        linkAdapter = new MyLinkAdapter(getActivity(), getData());
        rv.setAdapter(linkAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public static List<LinkData> getData(){
        List<LinkData> linkData = new ArrayList<>();
        int[] icons = {R.drawable.moodgym, R.drawable.betterhelp, R.drawable.turn2me, R.drawable.cups, R.drawable.samh, R.drawable.nhs};
        String[] titles = {"MoodGym", "BetterHelp", "turn2me", "7cups", "SAMH", "NHS"};
        for(int i=0;i<icons.length && i<titles.length; i++)
        {
            LinkData current = new LinkData();
            current.cardTitle = titles[i];
            current.imageID = icons[i];
            linkData.add(current);
        }
        return linkData;
    }
}
