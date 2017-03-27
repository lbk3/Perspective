package com.example.liamk.version3.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liamk.version3.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventsFrag extends Fragment {
    String getData;
    ListView eventList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eventfrag, container, false);

        eventList = (ListView) view.findViewById(R.id.eventList);


        getData = getActivity().getIntent().getExtras().getString("importEvents");
        List<String> myList = new ArrayList<String>(Arrays.asList(getData.split(",")));

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_row, R.id.rowText, myList);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_row, R.id.rowText, myList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView

                View view = super.getView(position,convertView,parent);
                if(position %2 == 1)
                {
                    // Set a background color for ListView regular row/item
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(3);

                    if(randomInt == 0) {
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#d37070"));
                    }
                    else if(randomInt == 1){
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#92b5f3"));
                    } else{
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#98e5a6"));
                    }
                }
                else
                {
                    // Set the background color for alternate row/item
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(3);

                    if(randomInt == 0) {
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#d37070"));
                    }
                    else if(randomInt == 1){
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#92b5f3"));
                    } else{
                        TextView row = (TextView) view.findViewById(R.id.rowColour);
                        row.setBackgroundColor(Color.parseColor("#98e5a6"));
                    }
                }
                return view;
            }
        };

        // DataBind ListView with items from ArrayAdapter


        eventList.setAdapter(arrayAdapter);
        return view;
    }

}