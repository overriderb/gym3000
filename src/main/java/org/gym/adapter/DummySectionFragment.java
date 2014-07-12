package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.gym.Workout;
import org.gym.WorkoutFactory;
import org.gym.activity.R;

import java.util.Arrays;
import java.util.List;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class DummySectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public DummySectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Workout workoutItem = (Workout) WorkoutFactory.getExercisesCollection(getExerciseTitles(), getExerciseTexts())
                .get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);

        View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
        TextView workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        TextView workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        //workoutNameTextView.setText("Text " + Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        return rootView;
    }

    private List<String> getExerciseTitles() {
        return Arrays.asList(getResources().getStringArray(R.array.exerciseTitles));
    }

    private List<String> getExerciseTexts() {
        return Arrays.asList(getResources().getStringArray(R.array.exerciseTexts));
    }
}
