package org.gym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.gym.activity.R;
import org.gym.dao.DatabaseHelper;
import org.gym.object.Attempt;
import org.gym.object.Exercise;

import java.util.List;

/**
 * This adapter is a part of history_pages.xml layout. It provides work of every item of layout.
 *
 */
public class HistoryListItemAdapter extends ArrayAdapter<Exercise> {

    public HistoryListItemAdapter(Context context, int resource, List<Exercise> objects) {
        super(context, resource, objects);
        this.context = context;
        exerciseList = objects;
        databaseHelper = new DatabaseHelper(this.getContext());
    }

    private List<Exercise> exerciseList = null;
    private List<Attempt> attemptList = null;
    private DatabaseHelper databaseHelper;
    private Context context;

    private LinearLayout attemptsLayout;
    private LinearLayout innerAttemptLayout;

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

        //attemptsLayout = (LinearLayout) convertView.findViewById(R.id.history_attempts_linear_layout);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*for(Attempt attempt: attemptList){
            innerAttemptLayout = (LinearLayout) inflater.inflate(R.layout.history_list_item_inner_frame, (ViewGroup) convertView.findViewById(R.layout.history_list_item_inner_frame));
            attemptsLayout.addView(innerAttemptLayout);
        }*/
        //innerAttemptLayout = (LinearLayout) inflater.inflate(R.layout.history_list_item_inner_frame, (ViewGroup) convertView.findViewById(R.layout.history_list_item_inner_frame));
        //attemptsLayout.addView(innerAttemptLayout);
        return convertView;
    }
}
