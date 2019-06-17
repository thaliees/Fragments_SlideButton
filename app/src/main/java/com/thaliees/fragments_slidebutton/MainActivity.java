package com.thaliees.fragments_slidebutton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SlideButton button;
    private float movementInitialX, halfToSlider;
    private Integer widthButton;
    private FragmentTransaction transaction;
    private Fragment two = new TwoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.slide);
        button.setOnTouchListener(slideButton);

        // Get transaction
        transaction = getSupportFragmentManager().beginTransaction();
        // Add the second fragment. Default, the first fragment already in container
        transaction.add(R.id.container_fragment, two);
        // Hide the second fragment
        transaction.hide(two);
        transaction.commit();
    }

    private View.OnTouchListener slideButton = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN: return true;

                case MotionEvent.ACTION_MOVE:
                    // Initialize parameters to evaluate
                    if (movementInitialX == 0) {
                        movementInitialX = button.toSlider.getX();              // Movement initial (when user move for first once the ImageView)
                        widthButton = button.getWidth();                        // Get width of our SlideButton
                        button.widthToSlider = button.toSlider.getWidth();      // Get width of our ImageView
                        halfToSlider = (float) button.toSlider.getWidth() / 2;  // Calculate half the width of our ImageView
                    }
                    // Move the button
                    if (event.getX() > (movementInitialX + halfToSlider) && (event.getX() + halfToSlider) < widthButton)
                        button.toSlider.setX(event.getX() - halfToSlider);
                    // Move to the end and avoid overflowing the limit
                    if ((event.getX() + halfToSlider) > widthButton && (button.toSlider.getX() + halfToSlider) < widthButton)
                        button.toSlider.setX(widthButton - button.widthToSlider);
                    // Move to the start and avoid overflowing the limit
                    if (event.getX() < halfToSlider)
                        button.toSlider.setX(0);

                    return true;

                case MotionEvent.ACTION_UP:
                    // What animation to do?
                    if (button.isButtonOnTheRight) {
                        // If the position of the movement is lower of the width of the SlideButton, move to the left
                        if (button.toSlider.getX() < button.widthToSlider){
                            button.isButtonOnTheRight = false;
                            button.moveButtonLeft();
                            showFragment1();
                        }
                        else button.moveButtonRight();
                    }
                    else {
                        // If the position of the movement exceeds 50% of the width of the SlideButton, slider
                        if ((button.toSlider.getX() + button.widthToSlider) > (widthButton * 0.5)) {
                            button.isButtonOnTheRight = true;
                            button.moveButtonRight();
                            showFragment2();
                        } else button.moveButtonLeft();
                    }
            }
            return false;
        }
    };

    protected void showFragment1() {
        // To show the first fragment, only hide the second
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.hide(two).commit();
    }

    protected void showFragment2() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.show(two).commit();
    }
}
