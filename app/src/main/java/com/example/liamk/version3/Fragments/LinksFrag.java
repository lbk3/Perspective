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
        String[] titles = {"MoodGym", "BetterHelp", "turn2me", "7 Cups of Tea", "SAMH", "NHS"};
        String[] descs = {"MoodGYM is an interactive self-help program that provides CBT training in order to help users prevent and cope with mental health issues",
                "BetterHelp is an online portal that provides direct-to-consumer access to behavioral health services",
                "turn2me is an online support community offering a three tiered approach to supporting mental health issues. ",
                "7 Cups is a service which provides free support to people experiencing emotional distress by connecting them with trained listeners.",
                "SAMH is based in Scotland, providing mental health social care support, homelessness, addictions and employment services, among others.",
                "The National Health Service has a variety of online resources for coping with mental health issues"};
        for(int i=0;i<icons.length && i<titles.length; i++)
        {
            LinkData current = new LinkData();
            current.cardTitle = titles[i];
            current.imageID = icons[i];
            current.cardDesc = descs[i];
            linkData.add(current);
        }
        return linkData;
    }
}
