package com.example.liamk.version3;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardmodel, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset[position]);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                {
                    Snackbar.make(view, "Take a time out", Snackbar.LENGTH_LONG).show();
                } else if(position == 1){
                    Snackbar.make(view, "Countdown", Snackbar.LENGTH_LONG).show();
                }else if(position == 2){
                    Snackbar.make(view, "Meditation", Snackbar.LENGTH_LONG).show();
                }else if(position == 3){
                    Snackbar.make(view, "Positive Reinforcement", Snackbar.LENGTH_LONG).show();
                }else if(position == 4){
                    Snackbar.make(view, "4-7-8 Breathing", Snackbar.LENGTH_LONG).show();
                }else if(position == 5){
                    Snackbar.make(view, "Breath Regulation", Snackbar.LENGTH_LONG).show();
                }else if(position == 6){
                    Snackbar.make(view, "Aerobic Exercise", Snackbar.LENGTH_LONG).show();
                }else if(position == 7){
                    Snackbar.make(view, "Open Up", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}