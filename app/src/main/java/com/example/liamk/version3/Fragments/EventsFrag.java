package com.example.liamk.version3.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liamk.version3.Activities.APIActivity;
import com.example.liamk.version3.R;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventsFrag extends Fragment {
    TextView getList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eventfrag, container, false);
        getList = (TextView) view.findViewById(R.id.getView);
        getList.setText(getActivity().getIntent().getExtras().getString("importEvents"));



        return view;
    }

}