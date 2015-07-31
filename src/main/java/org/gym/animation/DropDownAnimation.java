package org.gym.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Animation class for changing size elements(Views).
 * Currently it provides work with change height elements.
 */
public class DropDownAnimation extends Animation {
    private final int targetHeight;
    private final View view;


    public DropDownAnimation(View view, int targetHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().height = targetHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
