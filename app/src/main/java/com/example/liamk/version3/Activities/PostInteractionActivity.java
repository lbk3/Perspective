package com.example.liamk.version3.Activities;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liamk.version3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PostInteractionActivity extends AppCompatActivity {

    private DatabaseReference blogDB;
    private DatabaseReference commentDB;
    private String postID;
    private FirebaseAuth dbAuth;

    private TextView setPostData;
    private TextView singleTitle;
    private TextView singleDesc;
    private TextView singleVote;
    private EditText commentText;
    private ImageButton sendComment;
    private ListView commentList;

    private ProgressDialog commentProgress;
    private LinearLayout commentLayout;
    //private String[] arrayData = {"comment 1", "comment 2", "comment 3", "comment 4", "comment 5", "comment 6", };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_right, R.anim.hold);
        setContentView(R.layout.activity_post_interaction);

        commentText = (EditText) findViewById(R.id.commentInput);
        sendComment = (ImageButton) findViewById(R.id.commitComment);

        setPostData = (TextView) findViewById(R.id.postData);
        singleTitle = (TextView) findViewById(R.id.singleTitleTxt);
        singleDesc = (TextView) findViewById(R.id.singleDescTxt);
        singleVote = (TextView) findViewById(R.id.singleVoteCount);
        commentList = (ListView) findViewById(R.id.commentList);

        postID = getIntent().getExtras().getString("importPostInfo");
        setPostData.setText(postID);

        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");
        commentDB = FirebaseDatabase.getInstance().getReference().child("Engagement");
        blogDB.keepSynced(true);
        commentDB.keepSynced(true);
        dbAuth = FirebaseAuth.getInstance();

        commentProgress = new ProgressDialog(this);
        commentLayout = (LinearLayout) findViewById(R.id.commentLayout);

        //ArrayAdapter adapter = new ArrayAdapter(this, R.layout.custom_row, R.id.rowText, arrayData);
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayData);
        //commentList.setAdapter(adapter);

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


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference engagementRef = rootRef.child("Engagement").child("-Kne46iBe6ooNFKTv_8w").child("Comments");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String value = ds.getValue(String.class);
                    Log.d("TAG", value + ", ");
                    List<String> myList = new ArrayList<String>(Arrays.asList(value.split(",")));
                    ArrayAdapter adapter = new ArrayAdapter(PostInteractionActivity.this, android.R.layout.simple_list_item_1, myList);
                    commentList.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        engagementRef.addListenerForSingleValueEvent(eventListener);


        commentDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(postID)){
                    long numOfLikes =dataSnapshot.child(postID).child("Likers").getChildrenCount();
                    singleVote.setText(Long.toString(numOfLikes));
                } else{
                    singleVote.setText("0");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String commentInput = commentText.getText().toString().trim();
                if(commentInput.isEmpty())
                {
                    Snackbar.make(view,"There's nothing to share",Snackbar.LENGTH_SHORT).show();

                } else{
                    commitPost();
                }

            }
        });

    }

    private void commitPost() {
        final String commentInput = commentText.getText().toString().trim();
        commentProgress.setMessage("Posting");
        commentProgress.show();

        String uniqueId = UUID.randomUUID().toString();
        commentDB.child(postID).child("Comments").child(uniqueId).setValue(commentInput);
        commentProgress.dismiss();
        finish();
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.exit_right);
        super.onPause();
    }
}
