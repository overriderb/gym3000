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
import org.gym.repository.DatabaseHelper;
import org.gym.domain.Exercise;
import org.gym.domain.Workout;
import org.gym.activity.R;

/**
 * A section fragment representing history
 */
public class HistorySectionFragment extends Fragment {
    
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

        ArrayAdapter<Exercise> historyWorkoutAdapter = new HistoryArrayAdapter(this.getActivity(),
            R.layout.history_list_item_layout,
            databaseHelper.getExerciseRepository().findExerciseListByParentId(workoutItem.getId()));

        workoutHistoryView.setAdapter(historyWorkoutAdapter);
        return rootView;
    }
}
