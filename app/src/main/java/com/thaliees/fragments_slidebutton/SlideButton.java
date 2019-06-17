package com.thaliees.fragments_slidebutton;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideButton extends RelativeLayout {
    public ImageView toSlider;
    public Integer widthToSlider;
    public Boolean isButtonOnTheRight;

    public SlideButton(Context context) {
        super(context);
        initSlideButton(context);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSlideButton(context);
    }

    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSlideButton(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSlideButton(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initSlideButton(Context context){
        RelativeLayout slideButton = new RelativeLayout(context);
        slideButton.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_rounded_background));
        LayoutParams layoutParamsSlideButton = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsSlideButton.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(slideButton, layoutParamsSlideButton);

        // The ImageView will behave like the sliding button
        final ImageView toSlider = new ImageView(context);
        this.toSlider = toSlider;
        isButtonOnTheRight = false;
        toSlider.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_rounded));
        LayoutParams layoutParamsToSlider = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsToSlider.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParamsToSlider.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        slideButton.addView(toSlider, layoutParamsToSlider);

        // The TextView's indicate the Fragment to show
        // TextView Left
        final TextView left = new TextView(context);
        left.setPadding(60, 0, 60, 0);
        left.setText(getResources().getString(R.string.textView_left));
        left.setTextColor(getResources().getColor(R.color.colorPrimary));
        left.setTextSize(20);
        left.setTypeface(null, Typeface.BOLD);
        LayoutParams layoutParamsLeft = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLeft.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        layoutParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        slideButton.addView(left, layoutParamsLeft);

        // TextView Right
        final TextView right = new TextView(context);
        right.setPadding(60, 0, 60, 0);
        right.setText(getResources().getString(R.string.textView_right));
        right.setTextColor(getResources().getColor(R.color.colorPrimary));
        right.setTextSize(20);
        right.setTypeface(null, Typeface.BOLD);
        LayoutParams layoutParamsRight = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRight.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        layoutParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        slideButton.addView(right, layoutParamsRight);
    }

    public void moveButtonLeft(){
        // Indicate the current point of our ImageView is and the point where it should arrive
        final ValueAnimator position = ValueAnimator.ofFloat(toSlider.getX(), 0);
        position.setDuration(1000);  // Also try animator.setDuration(1000);

        // Update the position of our ImageView
        position.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) position.getAnimatedValue();
                toSlider.setX(x);
            }
        });

        // Initialize animation
        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(position);
        animator.start();
    }

    public void moveButtonRight(){
        // Indicate the current point of our ImageView is and the point where it should arrive
        final ValueAnimator position = ValueAnimator.ofFloat(toSlider.getX(), getWidth() - widthToSlider);
        position.setDuration(1000);  // Also try animator.setDuration(1000);

        // Update the position of our ImageView
        position.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) position.getAnimatedValue();
                toSlider.setX(x);
            }
        });

        // Initialize animation
        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(position);
        animator.start();
    }
}
