package org.gym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.gym.activity.R;
import org.gym.model.Attempt;
import org.gym.model.Exercise;
import org.gym.repository.DatabaseHelper;
import org.gym.service.AttemptService;

import java.util.List;

/**
 * This adapter is a part of history_pages.xml layout. It provides work of every item of layout.
 */
public class HistoryArrayAdapter extends ArrayAdapter<Exercise> {

    private List<Exercise> exercises;
    private Context context;
    private AttemptService attemptService;

    public HistoryListItemAdapter(Context context, int resource, List<Exercise> exercises) {
        super(context, resource, exercises);
        this.context = context;
        this.exercises = exercises;
        attemptService = new AttemptService();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Exercise exercise = exercises.get(position);
//        List<Attempt> attemptList = attemptService.getAttempts(context, exercise.getId());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.history_list_item_layout, null);
        }
        ((TextView) convertView.findViewById(R.id.history_workoutItem_date))
                .setText(exercise.getWorkout().getStartDate().toString());
        ((TextView) convertView.findViewById(R.id.history_workoutItem_letter_S_M_L))
                .setText(exercise.getLevel().name());

        String result = "";
        for(Attempt attempt: exercise.getAttempts()) {
            result = result + attempt.getWeight() + "/" + attempt.getCount() + " ";
        }
        ((TextView) convertView.findViewById(R.id.history_attempts_list))
                .setText(result);

        return convertView;
    }
}
