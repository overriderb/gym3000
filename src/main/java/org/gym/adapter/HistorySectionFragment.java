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
import org.gym.activity.R;
import org.gym.model.Exercise;
import org.gym.model.ExerciseType;
import org.gym.service.ExerciseService;

/**
 * A section fragment representing history
 */
public class HistorySectionFragment extends Fragment {
    
    public static final String ARG_SECTION_NUMBER = "org.gym.adapter.HistorySectionFragment.ARG_SECTION_NUMBER";

    private ExerciseType exerciseType;
    View rootView;
    TextView exerciseNameTextView;
    ListView exerciseHistoryView;
    private CurrentProgramCache cache;

    public HistorySectionFragment() {
        cache = CurrentProgramCache.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        exerciseType = cache.getExerciseTypes().get(getArguments().getInt(ARG_SECTION_NUMBER));
        rootView = inflater.inflate(R.layout.history_pages, container, false);
        exerciseNameTextView = (TextView) rootView.findViewById(R.id.history_workout_title);
        exerciseNameTextView.setText(exerciseType.getName());
        exerciseHistoryView = (ListView) rootView.findViewById(R.id.history_workout_list_view);

        List<Exercise> exercises = ExerciseService.getInstance().findByTypeId(exerciseType.getId());

        ArrayAdapter<Exercise> historyExerciseTypeAdapter = new HistoryArrayAdapter(this.getActivity(),
            R.layout.history_list_item_layout,
            exercises);

        exerciseHistoryView.setAdapter(historyExerciseTypeAdapter);
        return rootView;
    }
}
