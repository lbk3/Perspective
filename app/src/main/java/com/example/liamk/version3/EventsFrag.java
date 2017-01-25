package com.example.liamk.version3;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

/**
 * Created by liamk on 22/01/2017.
 */
public class EventsFrag extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.eventfrag, container, false);
    }
}
