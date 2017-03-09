package com.example.liamk.version3.Adapters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liamk.version3.CardInfo.TechData;
import com.example.liamk.version3.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class MyTechniqueAdapter extends RecyclerView.Adapter<MyTechniqueAdapter.MyViewHolder> {
   private LayoutInflater inflater;
    List<TechData> data = Collections.emptyList();



    public MyTechniqueAdapter(Context context, List<TechData> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.techcard, parent,false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TechData current = data.get(position);
        holder.techTitle.setText(current.cardTitle);
        holder.techImage.setImageResource(current.imageID);

        holder.techCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                {
                    Snackbar.make(view, "Take a time out, grab a coffee", Snackbar.LENGTH_LONG).show();
                } else if(position == 1){
                    Snackbar.make(view, "Let's count down from 10", Snackbar.LENGTH_LONG).show();
                }else if(position == 2){
                    Snackbar.make(view, "Let's meditate", Snackbar.LENGTH_LONG).show();
                }else if(position == 3){
                    Snackbar.make(view, "Good job!", Snackbar.LENGTH_LONG).show();
                }else if(position == 4){
                    Snackbar.make(view, "Try this breathing technique", Snackbar.LENGTH_LONG).show();
                }else if(position == 5){
                    Snackbar.make(view, "Let's get control of your breathing", Snackbar.LENGTH_LONG).show();
                }else if(position == 6){
                    Snackbar.make(view, "Let's do some cardio", Snackbar.LENGTH_LONG).show();
                }else if(position == 7){
                    Snackbar.make(view, "Talk to someone", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView techImage;
        TextView techTitle;
        CardView techCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            techImage = (ImageView) itemView.findViewById(R.id.techImage);
            techTitle = (TextView) itemView.findViewById(R.id.techText);
            techCard = (CardView) itemView.findViewById(R.id.techCardView);
        }
    }
}