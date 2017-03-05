package com.example.liamk.version3.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.liamk.version3.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BlogPostActivity extends AppCompatActivity {

    EditText blogTitleTxt;
    private EditText blogDescTxt;
    private Button submitBtn;
    private RelativeLayout postLayout;

    private ProgressDialog postProgress;

    private DatabaseReference blogDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);

        postLayout = (RelativeLayout) findViewById(R.id.postLayout);

        blogTitleTxt = (EditText) findViewById(R.id.titleInput);
        blogDescTxt = (EditText) findViewById(R.id.descInput);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        postProgress = new ProgressDialog(this);

        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commitPost();
            }
        });
    }

    private void commitPost() {
        postProgress.setMessage("Posting");
        postProgress.show();
        String blogTitleContent = blogTitleTxt.getText().toString().trim();
        String blogDescContent = blogDescTxt.getText().toString().trim();

        if(!TextUtils.isEmpty(blogTitleContent) && !TextUtils.isEmpty(blogDescContent)){
                    DatabaseReference newPost = blogDB.push();
                    newPost.child("title").setValue(blogTitleContent);
                    newPost.child("desc").setValue(blogDescContent);
                    postProgress.dismiss();
                    finish();
                }else{
            Snackbar.make(postLayout,"You haven't finished your post yet",Snackbar.LENGTH_SHORT);
        }

        }
    }
