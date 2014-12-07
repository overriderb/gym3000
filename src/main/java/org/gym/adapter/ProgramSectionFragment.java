package org.gym.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import org.gym.component.GymNumberPicker;
import org.gym.dao.DatabaseHelper;
import org.gym.object.Attempt;
import org.gym.object.Exercise;
import org.gym.object.Workout;
import org.gym.activity.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A section fragment representing programs
 */
public class ProgramSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "org.gym.adapter.ProgramSectionFragment.ARG_SECTION_NUMBER";
    Workout workoutItem;
    View rootView;
    TextView workoutNameTextView;
    TextView workoutDescrTextView;
    ImageView imageView;
    FrameLayout frameLayout;
    private GymNumberPicker weightPicker;
    private GymNumberPicker countPicker;
    private List<Workout> workoutList;


    public ProgramSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = workoutList.get(getArguments().getInt(ARG_SECTION_NUMBER));


        rootView = inflater.inflate(R.layout.program_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout);

        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        imageView.setImageResource(workoutItem.getPictureId());

        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.bigLinear);
        countPicker = buildCountPicker(layout.getContext());
        layout.addView(countPicker);
        weightPicker = buildWeightPicker(layout.getContext());
        layout.addView(weightPicker);

        final Button button = (Button) rootView.findViewById(R.id.save_attempt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                persistAttempt(button.getContext());
            }
        });

        return rootView;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    /**
     * Demonstration implementation
     * Build and prepare custom number picker
     * TODO: organize controls according to view.
     *
     * @param context parent layout context
     * @return created gym number picker
     */
    private GymNumberPicker buildWeightPicker(Context context) {
        GymNumberPicker numberPicker = new GymNumberPicker(context);
//        numberPicker.configureRange(20, 100, 2.5f);
        numberPicker.setMaxValue(150);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.scaleSize(0.6f);
        return numberPicker;
    }

    private GymNumberPicker buildCountPicker(Context context) {
        GymNumberPicker numberPicker = new GymNumberPicker(context);
        numberPicker.setMaxValue(50);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.scaleSize(0.6f);
        return numberPicker;
    }

    private void persistAttempt(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Exercise exercise = new Exercise();
        exercise.setParentId(workoutItem.getId());
        exercise.setDate(getCurrentDate());
        exercise.setType(Exercise.TYPE.M);
        long exerciseId = databaseHelper.getExerciseRepository().storeExercise(exercise);
        Attempt attempt = new Attempt();
        attempt.setParentId(exerciseId);
        attempt.setCount(countPicker.getValue());
//        attempt.setWeight(Integer.parseInt(weightPicker.getDisplayedValues()[weightPicker.getValue()]));
        attempt.setWeight(weightPicker.getValue());
        databaseHelper.getAttemptRepository().storeAttempt(attempt);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
