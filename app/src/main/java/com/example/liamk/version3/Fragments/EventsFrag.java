package com.example.liamk.version3.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liamk.version3.Activities.APIActivity;
import com.example.liamk.version3.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventsFrag extends Fragment {
    TextView getList;
    String getData;
    ListView eventList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eventfrag, container, false);

        eventList = (ListView) view.findViewById(R.id.eventList);
        getList = (TextView) view.findViewById(R.id.getView);

        getData = getActivity().getIntent().getExtras().getString("importEvents");
        List<String> myList = new ArrayList<String>(Arrays.asList(getData.split(",")));
        myList.remove(0);
        myList.remove(myList.size() - 1);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, myList);
        eventList.setAdapter(arrayAdapter);



        return view;
    }

}