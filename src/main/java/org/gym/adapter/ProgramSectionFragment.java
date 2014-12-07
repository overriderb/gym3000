package org.gym.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import org.gym.component.GymNumberPicker;
import org.gym.domain.Workout;
import org.gym.activity.R;

import java.util.List;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class ProgramSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "org.gym.adapter.ProgramSectionFragment.ARG_SECTION_NUMBER";
    Workout workoutItem;
    View rootView;
    TextView workoutNameTextView;
    TextView workoutDescrTextView;
    ImageView imageView;
    FrameLayout frameLayout;
    private List<Workout> workoutList;


    public ProgramSectionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = workoutList.get(getArguments().getInt(ARG_SECTION_NUMBER));


        rootView = inflater.inflate(R.layout.program_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout);

        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        imageView.setImageResource(workoutItem.getPictureId());

        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.bigLinear);
        GymNumberPicker numberPicker = buildNumberPicker(layout.getContext());
        layout.addView(numberPicker);

        return rootView;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    /**
     * Demonstration implementation
     * Build and prepare custom number picker
     * TODO: organize controls according to view.
     *
     * @param context parent layout context
     * @return created gym number picker
     */
    private GymNumberPicker buildNumberPicker(Context context) {
        GymNumberPicker numberPicker = new GymNumberPicker(context);
        numberPicker.configureRange(20, 100, 2.5f);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.scaleSize(0.6f);
        return numberPicker;
    }
}
