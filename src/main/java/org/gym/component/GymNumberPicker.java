package org.gym.component;

//import android.support.annotation.NonNull;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom number picker for gym3000 application
 */
public class GymNumberPicker extends NumberPicker {

    private static final int DEFAULT_FONT_SIZE = 25;
    private static final int DEFAULT_FONT_COLOR = Color.parseColor("white");

    private Context context;

    public GymNumberPicker(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void addView(/*@NonNull */View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(/*@NonNull */View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(/*@NonNull */View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Configure values range for gym number picker
     *
     * @param minValue minimum value of range
     * @param maxValue maximum value of range
     * @param step step of range
     */
    public void configureRange(float minValue, float maxValue, float step) {
        float nextValue = minValue;

        List<String> values = new ArrayList<String>();

        for (int i = 0; nextValue <= maxValue; i++) {
            values.add(String.valueOf(nextValue));
            nextValue = nextValue + step;
        }

        this.setDisplayedValues(values.toArray(new String[values.size()]));
        this.setMaxValue(values.size() - 1);
    }

    /**
     * Set proportional size for component, same for axis X and Y
     *
     * @param scale
     */
    public void scaleSize(float scale) {
        this.setScaleX(scale);
        this.setScaleY(scale);
    }

    private void updateView(View view) {
        if(view instanceof EditText){
            ((EditText) view).setTextSize(DEFAULT_FONT_SIZE);
            ((EditText) view).setTextColor(DEFAULT_FONT_COLOR);
        }
    }
}
