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

import com.example.liamk.version3.CardInfo.LinkData;
import com.example.liamk.version3.CardInfo.TechData;
import com.example.liamk.version3.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class MyLinkAdapter extends RecyclerView.Adapter<MyLinkAdapter.MyLinkViewHolder>{
    private LayoutInflater linkInflater;
    List<LinkData> linkdata = Collections.emptyList();

    public MyLinkAdapter(Context context, List<LinkData> linkdata){
        linkInflater = LayoutInflater.from(context);
        this.linkdata = linkdata;
    }

    @Override
    public MyLinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = linkInflater.inflate(R.layout.cardmodel, parent,false);
        MyLinkViewHolder holder = new MyLinkViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyLinkViewHolder holder, final int position) {
        LinkData current = linkdata.get(position);
        holder.linkTitle.setText(current.cardTitle);
        holder.linkImage.setImageResource(current.imageID);

        holder.linkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                {
                    Snackbar.make(view, "MoodGym", Snackbar.LENGTH_LONG).show();
                    String url = "https://moodgym.anu.edu.au/welcome";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                } else if(position == 1){
                    Snackbar.make(view, "BetterHelp", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.betterhelp.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 2){
                    Snackbar.make(view, "turn2me", Snackbar.LENGTH_LONG).show();
                    String url = "https://turn2me.org/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 3){
                    Snackbar.make(view, "7cups", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.7cups.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 4){
                    Snackbar.make(view, "SAMH", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.samh.org.uk/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    view.getContext().startActivity(i);
                }else if(position == 5){
                    Snackbar.make(view, "NHS", Snackbar.LENGTH_LONG).show();
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
        return linkdata.size();
    }

    class MyLinkViewHolder extends RecyclerView.ViewHolder{
        ImageView linkImage;
        TextView linkTitle;
        CardView linkCard;

        public MyLinkViewHolder(View itemView) {
            super(itemView);
            linkImage = (ImageView) itemView.findViewById(R.id.linkIcon);
            linkTitle = (TextView) itemView.findViewById(R.id.linkTitle);
            linkCard = (CardView) itemView.findViewById(R.id.linkCardView);
        }
    }
}