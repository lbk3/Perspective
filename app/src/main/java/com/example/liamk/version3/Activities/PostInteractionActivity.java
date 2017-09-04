package com.example.liamk.version3.Activities;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
                commitPost();
                finish();
                //commentText.getText().clear();
                //commentText.clearFocus();
            }
        });

        commentDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(postID)) {
                    String comment = (String) dataSnapshot.child("Comments").getValue();

                    List<String> myList = new ArrayList<String>(Arrays.asList(comment));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_row, R.id.rowText, myList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void commitPost() {
        commentProgress.setMessage("Posting");
        commentProgress.show();
        final String commentInput = commentText.getText().toString().trim();

        if(TextUtils.isEmpty(commentInput)){
            Snackbar.make(commentLayout,"You haven't finished your post yet",Snackbar.LENGTH_SHORT);
        }else{

            commentDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String user = dbAuth.getCurrentUser().getEmail().toString();
                    commentDB.child(postID).child("Comments").child("comment").setValue(commentInput);
                    //commentDB.child(postID).child("Comments").child("email").setValue(user);
                    commentProgress.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //DatabaseReference newPost = commentDB.push();
            //newPost.child(postID).child("Comments").child("comment").setValue(commentInput);
            //newPost.child(postID).child("Comments").child("email").setValue(dbAuth.getCurrentUser().getEmail());
            //commentProgress.dismiss();
        }
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.exit_right);
        super.onPause();
    }
}
