package com.example.dex.onboardscreen;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlidePager;
    private LinearLayout mDotLayout;

    private Button mNextbtn;
    private Button mBackBtn;

    private int mCurrentPage;

    private TextView[] mDots;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            mCurrentPage = position;

            if (position == 0) {
                mNextbtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.GONE);

                mNextbtn.setText("NEXT");
                mBackBtn.setText("");

            } else if (position == mDots.length - 1) {

                mNextbtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextbtn.setText("FINISH");
                mBackBtn.setText("BACK");

            } else {
                mNextbtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextbtn.setText("NEXT");
                mBackBtn.setText("BACK");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private SlideAdapter slideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextbtn = findViewById(R.id.nextBtn);
        mBackBtn = findViewById(R.id.prevBtn);

        mSlidePager = findViewById(R.id.slideViewPager);

        mDotLayout = findViewById(R.id.dots_layout);

        slideAdapter = new SlideAdapter(this);

        mSlidePager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlidePager.addOnPageChangeListener(viewListener);


        mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlidePager.setCurrentItem(mCurrentPage + 1);

            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidePager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }
}
