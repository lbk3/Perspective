package com.example.liamk.version3.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liamk.version3.Activities.Blog;
import com.example.liamk.version3.Activities.BlogPostActivity;
import com.example.liamk.version3.Activities.PostInteractionActivity;
import com.example.liamk.version3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by liamk on 22/01/2017.
 */
public class BlogFrag extends Fragment{

    private DatabaseReference blogDB;
    EditText postTitleTxt;
    EditText postDescTxt;
    RecyclerView blogRV;
    private FloatingActionButton myFab;
    ImageButton replyBtn;
    private boolean registerVote = false;
    private DatabaseReference voteDB;
    private FirebaseAuth dbAuth;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View blogview =  inflater.inflate(R.layout.blogfrag, container, false);

        dbAuth = FirebaseAuth.getInstance();
        blogDB = FirebaseDatabase.getInstance().getReference().child("Blog");
        voteDB = FirebaseDatabase.getInstance().getReference().child("Engagement");
        blogDB.keepSynced(true);
        voteDB.keepSynced(true);

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
        if (mLayoutManager.getItemCount() != 0) {
            getActivity().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }

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

        final FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blogcard,
                BlogViewHolder.class,
                blogDB
        ) {
            @Override
            protected void populateViewHolder(final BlogViewHolder viewHolder, Blog model, int position) {
                final String uniquePostID = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setVote(uniquePostID);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Snackbar.make(view, uniquePostID, Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(view.getContext(), PostInteractionActivity.class);
                        intent.putExtra("importPostInfo", uniquePostID);
                        view.getContext().startActivity(intent);
                    }
                });

                viewHolder.postVoteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerVote = true;

                            voteDB.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (registerVote) {
                                        if (dataSnapshot.child(uniquePostID).hasChild(dbAuth.getCurrentUser().getUid())) {
                                            voteDB.child(uniquePostID).child(dbAuth.getCurrentUser().getUid()).removeValue();
                                            voteDB.child(uniquePostID).child("Likers").child(dbAuth.getCurrentUser().getUid()).removeValue();

                                            final Animation unlikeRotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotatedislike);
                                            viewHolder.postVoteBtn.startAnimation(unlikeRotate);

                                            registerVote = false;
                                        }
                                        else {
                                            String user = dbAuth.getCurrentUser().getEmail().toString();
                                            voteDB.child(uniquePostID).child(dbAuth.getCurrentUser().getUid()).setValue(user);
                                            voteDB.child(uniquePostID).child("Likers").child(dbAuth.getCurrentUser().getUid()).setValue(user);

                                            final Animation likeRotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotatelike);
                                            final Animation likeZoom = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomlike);
                                            AnimationSet multiLike = new AnimationSet(false);
                                            multiLike.addAnimation(likeRotate);
                                            multiLike.addAnimation(likeZoom);
                                            viewHolder.postVoteBtn.startAnimation(multiLike);

                                            registerVote = false;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                    }
                });

                viewHolder.postCommentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(getView(),"Comment on this post from " + dbAuth.getCurrentUser().getUid(), Snackbar.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), PostInteractionActivity.class);
                        intent.putExtra("importPostInfo", uniquePostID);
                        view.getContext().startActivity(intent);
                    }
                });
            }
        };

        blogRV.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageButton postVoteBtn;
        DatabaseReference dbLike;
        FirebaseAuth dbAuth;
        ImageButton postCommentBtn;
        TextView voteCount;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            postVoteBtn = (ImageButton) mView.findViewById(R.id.postVote);
            postCommentBtn = (ImageButton) mView.findViewById(R.id.postComment);


            dbAuth = FirebaseAuth.getInstance();
            dbLike = FirebaseDatabase.getInstance().getReference().child("Engagement");
            dbLike.keepSynced(true);

            voteCount = (TextView) mView.findViewById(R.id.voteCount);
        }

        public void setTitle(String title){
            TextView postTitle = (TextView) mView.findViewById(R.id.blogCardTitleTxt);
            postTitle.setText(title);
        }

        public void setDesc(String desc){
            TextView postDesc = (TextView) mView.findViewById(R.id.blogCardDescTxt);
            postDesc.setText(desc);
        }

        public void setVote(final String uniquePostID){
            dbLike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(uniquePostID).hasChild(dbAuth.getCurrentUser().getUid())){
                        postVoteBtn.setImageResource(R.mipmap.ic_plus_one_green_24dp);

                        if(dataSnapshot.hasChild(uniquePostID)){
                            long numOfLikes =dataSnapshot.child(uniquePostID).child("Likers").getChildrenCount();
                            voteCount.setText(Long.toString(numOfLikes));
                        }
                    } else {
                        postVoteBtn.setImageResource(R.mipmap.ic_plus_one_black_24dp);

                        if(dataSnapshot.hasChild(uniquePostID)){
                            long numOfLikes =dataSnapshot.child(uniquePostID).child("Likers").getChildrenCount();
                            voteCount.setText(Long.toString(numOfLikes));
                        } else{
                            voteCount.setText("0");
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }




}
