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

/**
 * Created by liamk on 29/01/2017.
 */
public class MyLinkAdapter extends RecyclerView.Adapter<MyLinkAdapter.MyViewHolder> {
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
    public MyLinkAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyLinkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
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

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                {
                    Snackbar.make(view, "Opening MoodGym", Snackbar.LENGTH_LONG).show();
                    String url = "https://moodgym.anu.edu.au/welcome";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                } else if(position == 1){
                    Snackbar.make(view, "Opening BetterHelp", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.betterhelp.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 2){
                    Snackbar.make(view, "Opening turn2me", Snackbar.LENGTH_LONG).show();
                    String url = "https://turn2me.org/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 3){
                    Snackbar.make(view, "Opening 7cups", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.7cups.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 4){
                    Snackbar.make(view, "Opening NHS", Snackbar.LENGTH_LONG).show();
                    String url = "http://www.nhs.uk/conditions/stress-anxiety-depression/pages/depression-help-groups.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}