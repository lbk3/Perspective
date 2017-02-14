package com.example.liamk.version3;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by liamk on 22/01/2017.
 */
public class BlogFrag extends Fragment{

    Button blogPost;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View blogview =  inflater.inflate(R.layout.blogfrag, container, false);

        blogPost = (Button) blogview.findViewById(R.id.openBlogBtn);
        blogPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BlogPostActivity.class));
            }
        });


        return blogview;
    }
}
