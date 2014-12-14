package org.gym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.gym.activity.R;
import org.gym.repository.DatabaseHelper;
import org.gym.domain.Attempt;
import org.gym.domain.Exercise;

import java.util.List;

/**
 * This adapter is a part of history_pages.xml layout. It provides work of every item of layout.
 *
 */
public class HistoryArrayAdapter extends ArrayAdapter<Exercise> {

    private List<Exercise> exerciseList = null;
    private List<Attempt> attemptList = null;
    private DatabaseHelper databaseHelper;
    private Context context;

    public HistoryArrayAdapter(Context context, int resource, List<Exercise> objects) {
        super(context, resource, objects);
        this.context = context;
        exerciseList = objects;
        databaseHelper = new DatabaseHelper(this.getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Exercise exercise = exerciseList.get(position);
        attemptList = databaseHelper.getAttemptRepository().findAttemptListByParentId(exercise.getId());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.history_list_item_layout, null);
        }
        ((TextView) convertView.findViewById(R.id.history_workoutItem_date))
                .setText(exercise.getDate());
        ((TextView) convertView.findViewById(R.id.history_workoutItem_letter_S_M_L))
                .setText(exercise.getType().name());

        String result = "";
        for(Attempt attempt: attemptList){
            result = result + attempt.getWeight() + "/" + attempt.getCount() + " ";
        }
        ((TextView) convertView.findViewById(R.id.history_attempts_list))
                .setText(result);

        return convertView;
    }
}
