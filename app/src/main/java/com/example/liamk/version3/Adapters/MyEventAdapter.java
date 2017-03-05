package com.example.liamk.version3.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liamk.version3.R;

/**
 * Created by liamk on 29/01/2017.
 */
public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.MyViewHolder> {
    private String[] eventData;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView eventCard;
        public TextView eventText;
        public MyViewHolder(View v) {
            super(v);

            eventCard = (CardView) v.findViewById(R.id.eventCardView);
            eventText = (TextView) v.findViewById(R.id.eventCardTitleTxt);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyEventAdapter(String[] myDataset) {
        eventData = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventcard, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.eventText.setText(eventData[position]);
    }

    @Override
    public int getItemCount() {
        return eventData.length;
    }
}