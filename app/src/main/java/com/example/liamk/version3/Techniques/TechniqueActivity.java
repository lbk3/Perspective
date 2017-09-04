package com.example.liamk.version3.Techniques;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liamk.version3.Activities.MainActivity;
import com.example.liamk.version3.Activities.SignIn;
import com.example.liamk.version3.R;
import com.google.firebase.auth.FirebaseAuth;

public class TechniqueActivity extends AppCompatActivity {
    TextView header;
    TextView information;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_right, R.anim.hold);
        setContentView(R.layout.activity_technique);
        header = (TextView) findViewById(R.id.techTitle);
        information = (TextView) findViewById(R.id.techDescription);
        picture = (ImageView) findViewById(R.id.techHeader);

        String getTitle;
        String getInfo;
        getTitle = getIntent().getExtras().getString("importMedTitle");
        getInfo = getIntent().getExtras().getString("importMedDesc");

        header.setText(getTitle);
        if(header.getText().toString().equals("Meditation")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techmed, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Take a Break")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techtime, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Count Down")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techcount, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Reinforcement")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techpos, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Breathing")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.tech478, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Cardio")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techcardio, null);
            picture.setImageDrawable(res);
        }else if(header.getText().toString().equals("Open")){
            Drawable res = ResourcesCompat.getDrawable(getResources(), R.drawable.techopen, null);
            picture.setImageDrawable(res);
        }

        information.setText(getInfo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_settings){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(TechniqueActivity.this, SignIn.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.exit_right);
        super.onPause();
    }
}
