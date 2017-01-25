package com.example.liamk.version3;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by liamk on 22/01/2017.
 */
public class LinksFrag extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linksfrag, container, false);

        String[] menuItems = {"MoodGym", "BetterHelp", "turn2me", "7cups", "NHS", "MoodGym", "BetterHelp", "turn2me", "7cups", "NHS"};
        ListView listView = (ListView) view.findViewById(R.id.linksList);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_expandable_list_item_1,
                menuItems);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Snackbar.make(view, "Opening MoodGym", Snackbar.LENGTH_LONG).show();
                    String url = "https://moodgym.anu.edu.au/welcome";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (position == 1) {
                    Snackbar.make(view, "Opening BetterHelp", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.betterhelp.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (position == 2) {
                    Snackbar.make(view, "Opening Turn2Me", Snackbar.LENGTH_LONG).show();
                    String url = "https://turn2me.org/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (position == 3) {
                    Snackbar.make(view, "Opening 7Cups", Snackbar.LENGTH_LONG).show();
                    String url = "https://www.7cups.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (position == 4) {
                    Snackbar.make(view, "Opening NHS", Snackbar.LENGTH_LONG).show();
                    String url = "http://www.nhs.uk/conditions/stress-anxiety-depression/pages/depression-help-groups.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
