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

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class HistorySectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "org.gym.adapter.HistorySectionFragment.ARG_SECTION_NUMBER";
    Workout workoutItem;
    View rootView;
    TextView workoutNameTextView;


    public HistorySectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = (Workout) WorkoutFactory.getExercisesCollection().get(getArguments().getInt(ARG_SECTION_NUMBER));
        rootView = inflater.inflate(R.layout.history_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutNameTextView.setText(workoutItem.getName());
        return rootView;
    }
}
