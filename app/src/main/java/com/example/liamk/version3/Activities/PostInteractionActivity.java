package com.example.liamk.version3.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.liamk.version3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PostInteractionActivity extends AppCompatActivity {

    private DatabaseReference blogDB;
    private String postID;

    private TextView setPostData;
    private TextView singleTitle;
    private TextView singleDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_interaction);

        setPostData = (TextView) findViewById(R.id.postData);
        singleTitle = (TextView) findViewById(R.id.singleTitleTxt);
        singleDesc = (TextView) findViewById(R.id.singleDescTxt);

        postID = getIntent().getExtras().getString("importPostInfo");
        setPostData.setText(postID);

        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");

        blogDB.child(postID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = (String) dataSnapshot.child("title").getValue();
                String desc = (String) dataSnapshot.child("desc").getValue();

                singleTitle.setText(title);
                singleDesc.setText(desc);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
