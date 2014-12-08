package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.gym.cache.CurrentProgramCache;
import org.gym.dao.DatabaseHelper;
import org.gym.object.Exercise;
import org.gym.object.Workout;
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
    private DatabaseHelper databaseHelper;
    private CurrentProgramCache cache;

    public HistorySectionFragment() {
        cache = CurrentProgramCache.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(this.getActivity());

        workoutItem = cache.getWorkoutList().get(getArguments().getInt(ARG_SECTION_NUMBER));
        rootView = inflater.inflate(R.layout.history_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.history_workout_title);
        workoutNameTextView.setText(workoutItem.getName());
        workoutHistoryView = (ListView) rootView.findViewById(R.id.history_workout_list_view);

        ArrayAdapter<Exercise> historyWorkoutAdapter = new HistoryListItemAdapter(this.getActivity(),
            R.layout.history_list_item_layout,
            databaseHelper.getExerciseAdapter().getExerciseListByParentId(workoutItem.getId()));

        workoutHistoryView.setAdapter(historyWorkoutAdapter);
        return rootView;
    }
}
