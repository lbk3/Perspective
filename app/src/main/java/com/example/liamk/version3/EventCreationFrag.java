package com.example.liamk.version3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventCreationFrag extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.eventcreatefrag, container, false);
    }
}
