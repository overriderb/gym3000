package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.gym.Workout;
import org.gym.WorkoutFactory;
import org.gym.activity.R;

import java.util.LinkedList;
import java.util.List;

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
    ListView workoutHistoryView;
    List<String> listOfWorkoutResult = new LinkedList<String>();



    public HistorySectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listOfWorkoutResult.add("result1");
        listOfWorkoutResult.add("result2");
        listOfWorkoutResult.add("result3");
        listOfWorkoutResult.add("result4");


        ArrayAdapter<String> historyWorkoutAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.history_list_item_layout, R.id.workout_item_history_text, listOfWorkoutResult);

        workoutItem = (Workout) WorkoutFactory.getExercisesCollection().get(getArguments().getInt(ARG_SECTION_NUMBER));
        rootView = inflater.inflate(R.layout.history_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.history_workout_title);
        workoutNameTextView.setText(workoutItem.getName());
        workoutHistoryView = (ListView) rootView.findViewById(R.id.history_workout_list_view);

        workoutHistoryView.setAdapter(historyWorkoutAdapter);


        return rootView;
    }
}
