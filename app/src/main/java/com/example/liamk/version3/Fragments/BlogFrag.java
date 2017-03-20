package com.example.liamk.version3.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liamk.version3.Activities.Blog;
import com.example.liamk.version3.Activities.BlogPostActivity;
import com.example.liamk.version3.Activities.MainActivity;
import com.example.liamk.version3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.liamk.version3.R.id.animLayout;

/**
 * Created by liamk on 22/01/2017.
 */
public class BlogFrag extends Fragment{

    private DatabaseReference blogDB;
    EditText postTitleTxt;
    EditText postDescTxt;
    RecyclerView blogRV;
    private FloatingActionButton myFab;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View blogview =  inflater.inflate(R.layout.blogfrag, container, false);

        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");
        postTitleTxt = (EditText) getActivity().findViewById(R.id.titleInput);
        postDescTxt = (EditText) getActivity().findViewById(R.id.descInput);

        blogRV = (RecyclerView) blogview.findViewById(R.id.blogRecycle);
        blogRV.setHasFixedSize(true);
        blogRV.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0)
                    myFab.hide();
                else if (dy < 0)
                    myFab.show();
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        blogRV.setLayoutManager(mLayoutManager);

        myFab = (FloatingActionButton) blogview.findViewById(R.id.blogfab);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BlogPostActivity.class));
            }
        });



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
