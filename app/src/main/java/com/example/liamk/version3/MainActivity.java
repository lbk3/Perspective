package com.example.liamk.version3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentTransaction fragTrans;
    private RelativeLayout relLayout;
    private RelativeLayout animLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        animLayout = (RelativeLayout) findViewById(R.id.animLayout);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomerAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (animLayout.getVisibility() == View.INVISIBLE) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        // get the center for the clipping circle
                        int cx = animLayout.getRight();
                        int cy = animLayout.getBottom();

                        // get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

                        // create the animator for this view (the start radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(animLayout, cx, cy, 0, finalRadius);

                        // make the view visible and start the animation
                        relLayout.setVisibility(View.INVISIBLE);
                        animLayout.setVisibility(View.VISIBLE);
                        anim.setDuration(1000);
                        anim.start();

                        Snackbar.make(v, "Let's make an event!", Snackbar.LENGTH_SHORT)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // get the center for the clipping circle
                                        int cx = animLayout.getRight();
                                        int cy = animLayout.getBottom();

                                        // get the initial radius for the clipping circle
                                        float initialRadius = (float) Math.hypot(cx, cy);

                                        // create the animation (the final radius is zero)
                                        Animator anim =
                                                ViewAnimationUtils.createCircularReveal(animLayout, cx, cy, initialRadius, 0);

                                        // make the view invisible when the animation is done
                                        anim.addListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                animLayout.setVisibility(View.INVISIBLE);
                                                relLayout.setVisibility(View.VISIBLE);
                                            }
                                        });

                                        //Start the animation
                                        anim.start();
                                    }
                                }).show();
                    } else {
                        relLayout.setVisibility(View.INVISIBLE);
                        animLayout.setVisibility(View.VISIBLE);
                    }
                } else{
                    // get the center for the clipping circle
                    int cx = animLayout.getRight();
                    int cy = animLayout.getBottom();

                    // get the initial radius for the clipping circle
                    float initialRadius = (float) Math.hypot(cx, cy);

                    // create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(animLayout, cx, cy, initialRadius, 0);

                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            animLayout.setVisibility(View.INVISIBLE);
                            relLayout.setVisibility(View.VISIBLE);
                        }
                    });

                    //Start the animation
                    anim.start();
                }
            }
        });
    }

    private class CustomerAdapter extends FragmentPagerAdapter {
        private String fragments [] = {"Events", "Techniques", "Links", "Blog",};
        public CustomerAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new EventsFrag();
                case 1:
                    return new TechFrag();
                case 2:
                    return new LinksFrag();
                case 3:
                    return new BlogFrag();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
}
