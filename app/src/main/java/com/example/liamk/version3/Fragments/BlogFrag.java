package com.example.liamk.version3.Fragments;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liamk.version3.Activities.Blog;
import com.example.liamk.version3.Activities.BlogPostActivity;
import com.example.liamk.version3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by liamk on 22/01/2017.
 */
public class BlogFrag extends Fragment{

    Button blogPost;
    private DatabaseReference blogDB;
    EditText postTitleTxt;
    EditText postDescTxt;
    RecyclerView blogRV;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View blogview =  inflater.inflate(R.layout.blogfrag, container, false);

        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");
        postTitleTxt = (EditText) getActivity().findViewById(R.id.titleInput);
        postDescTxt = (EditText) getActivity().findViewById(R.id.descInput);

        blogPost = (Button) blogview.findViewById(R.id.openBlogBtn);
        blogPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BlogPostActivity.class));
            }
        });

        blogRV = (RecyclerView) blogview.findViewById(R.id.blogRecycle);
        blogRV.setHasFixedSize(true);
        blogRV.setLayoutManager(new LinearLayoutManager(getActivity()));



        return blogview;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blogcard,
                BlogViewHolder.class,
                blogDB
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
            }
        };

        blogRV.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView postTitle = (TextView) mView.findViewById(R.id.blogCardTitleTxt);
            postTitle.setText(title);
        }

        public void setDesc(String desc){
            TextView postDesc = (TextView) mView.findViewById(R.id.blogCardDescTxt);
            postDesc.setText(desc);
        }

    }


}
