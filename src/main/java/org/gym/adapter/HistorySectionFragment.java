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

import java.util.List;

/**
 * A section fragment representing history
 */
public class HistorySectionFragment extends Fragment {
    
    public static final String ARG_SECTION_NUMBER = "org.gym.adapter.HistorySectionFragment.ARG_SECTION_NUMBER";

    private View rootView;
    private TextView exerciseNameTextView;
    private ListView exerciseHistoryView;

    private CurrentProgramCache cache;
    private ExerciseService exerciseService;

    public HistorySectionFragment() {
        this.cache = CurrentProgramCache.getInstance();
        this.exerciseService = ExerciseService.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ExerciseType exerciseType = cache.getExerciseTypes().get(getArguments().getInt(ARG_SECTION_NUMBER));
        rootView = inflater.inflate(R.layout.history_pages, container, false);
        exerciseNameTextView = (TextView) rootView.findViewById(R.id.history_workout_title);
        exerciseNameTextView.setText(exerciseType.getName());
        exerciseHistoryView = (ListView) rootView.findViewById(R.id.history_workout_list_view);

        List<Exercise> exercises = exerciseService.findByType(exerciseType);

        ArrayAdapter<Exercise> historyExerciseTypeAdapter = new HistoryArrayAdapter(this.getActivity(),
            R.layout.history_list_item_layout,
            exercises);

        exerciseHistoryView.setAdapter(historyExerciseTypeAdapter);
        return rootView;
    }
}
