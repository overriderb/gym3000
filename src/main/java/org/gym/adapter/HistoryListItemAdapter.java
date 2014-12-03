package org.gym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.gym.activity.R;
import org.gym.dao.DatabaseHelper;
import org.gym.logging.Logger;
import org.gym.object.Attempt;
import org.gym.object.Exercise;

import java.util.List;

/**
 * This adapter is a part of history_pages.xml layout. It provides work of every item of layout.
 *
 */
public class HistoryListItemAdapter extends ArrayAdapter<Exercise> {

    private List<Exercise> exerciseList = null;
    private List<Attempt> attemptList = null;
    private DatabaseHelper databaseHelper;
    private Context context;

    private GridLayout attemptsLayout;
    private LinearLayout innerAttemptLayout;

    public HistoryListItemAdapter(Context context, int resource, List<Exercise> objects) {
        super(context, resource, objects);
        this.context = context;
        exerciseList = objects;
        databaseHelper = new DatabaseHelper(this.getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Exercise exercise = exerciseList.get(position);
        Logger.debug("exercise " + exercise.getDate(), HistoryListItemAdapter.class);
        attemptList = databaseHelper.getAttemptAdapter().getAttemptListByParentId(exercise.getId());
        Logger.debug("attemptList size: " + attemptList.size(), HistoryListItemAdapter.class);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.history_list_item_layout, null);
        }
        ((TextView) convertView.findViewById(R.id.history_workoutItem_date))
                .setText(exercise.getDate());
        ((TextView) convertView.findViewById(R.id.history_workoutItem_letter_S_M_L))
                .setText(exercise.getTypeOfExercise());

        /*attemptsLayout = (GridLayout) convertView.findViewById(R.id.history_attempts_linear_layout);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(Attempt attempt: attemptList){
            Logger.debug("attempt " + attempt.getWeight() + "/" + attempt.getTimes(), HistoryListItemAdapter.class);
            innerAttemptLayout = (LinearLayout) inflater.inflate(R.layout.history_list_item_inner_frame,
                    (ViewGroup) convertView.findViewById(R.layout.history_list_item_inner_frame));
            *//*((TextView) convertView.findViewById(R.id.history_inner_frame_weight))
                    .setText(attempt.getWeight() + " kg");
            ((TextView) convertView.findViewById(R.id.history_inner_frame_times))
                    .setText(attempt.getTimes());*//*
            attemptsLayout.addView(innerAttemptLayout);
        }*/
        return convertView;
    }
}
