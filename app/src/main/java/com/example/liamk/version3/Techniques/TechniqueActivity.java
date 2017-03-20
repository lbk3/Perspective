package com.example.liamk.version3.Techniques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.liamk.version3.Activities.MainActivity;
import com.example.liamk.version3.Activities.SignIn;
import com.example.liamk.version3.R;
import com.google.firebase.auth.FirebaseAuth;

public class TechniqueActivity extends AppCompatActivity {
    TextView header;
    TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technique);
        header = (TextView) findViewById(R.id.techHeader);
        information = (TextView) findViewById(R.id.techDescription);

        String getData;
        getData = getIntent().getExtras().getString("importMeditation");

        header.setText(getData);
        techContent();
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

    public void techContent(){
        String content = "One aspect of anxiety is racing thoughts that won’t go away. Meditation helps with this part of the problem by quieting the overactive mind. Instead of buying into your fearful thoughts, you can start identifying with the silence that exists between every mental action. Through regular practice, you experience that you’re not simply your thoughts and feelings. You can detach yourself from these to rest in your own being. This involves remaining centered, and if a thought or outside trigger pulls you out of your center, your meditation practice allows you to return there again. " +
                "Being able to center yourself is a skill that anyone can learn, once they have the intention and the experience of what it feels like. Anxious people often shy away from meditation for various reasons. “I can’t meditate” is code for feeling too restless to sit still or having too many thoughts while trying to meditate. With a patient teacher, these objections can be overcome. Anyone can meditate, even if the first sessions are short and need to be guided. Being on tranquilizers, which for some anxious people is the only way they can cope, isn’t a block to meditation.";
        information.setText(content);
    }
}
