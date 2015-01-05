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
import android.widget.TextView;
import android.widget.Toast;
import org.gym.cache.CurrentProgramCache;
import org.gym.component.GymValuePicker;
import org.gym.domain.Exercise;
import org.gym.domain.Workout;
import org.gym.activity.R;
import org.gym.service.AttemptService;
import org.gym.service.ExerciseService;

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
    CurrentProgramCache cache;
    private GymValuePicker weightPicker;
    private GymValuePicker countPicker;
    private GymValuePicker exerciseTypePicker;

    private Long exerciseId;
    private Long attemptId;

    private AttemptService attemptService;
    private ExerciseService exerciseService;

    public ProgramSectionFragment() {
        cache = CurrentProgramCache.getInstance();
        attemptService = new AttemptService();
        exerciseService = new ExerciseService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = cache.getWorkoutList().get(getArguments().getInt(ARG_SECTION_NUMBER));

        rootView = inflater.inflate(R.layout.program_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout);

        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        imageView.setImageResource(workoutItem.getPictureId());

        // Configuration of exercise type section
        final LinearLayout attemptPickerLayout = (LinearLayout) rootView.findViewById(R.id.attemptPickerLayout);
        final LinearLayout exerciseTypeLayout = (LinearLayout) rootView.findViewById(R.id.exerciseTypeLayout);
        exerciseTypePicker = buildExerciseTypePicker(exerciseTypeLayout.getContext());
        exerciseTypeLayout.addView(exerciseTypePicker);
        final Button saveExerciseButton = (Button) rootView.findViewById(R.id.saveExerciseType);
        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                persistExerciseType(saveExerciseButton.getContext());
                exerciseTypeLayout.setVisibility(View.GONE);
                attemptPickerLayout.setVisibility(View.VISIBLE);
            }
        });

        // Configuration of attempt save section
        LinearLayout countLayout = (LinearLayout) rootView.findViewById(R.id.countPickerLayout);
        LinearLayout wightLayout = (LinearLayout) rootView.findViewById(R.id.wightPickerLayout);
        countPicker = buildCountPicker(countLayout.getContext());
        countLayout.addView(countPicker);
        weightPicker = buildWeightPicker(wightLayout.getContext());
        wightLayout.addView(weightPicker);
        final Button saveAttemptButton = (Button) rootView.findViewById(R.id.saveAttempt);
        saveAttemptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                persistAttempt(saveAttemptButton.getContext(), exerciseId);
            }
        });

        return rootView;
    }

    /**
     * Demonstration implementation
     * Build and prepare custom number picker
     * TODO: organize controls according to view.
     *
     * @param context parent layout context
     * @return created gym number picker
     */
    private GymValuePicker buildWeightPicker(Context context) {
        GymValuePicker weightPicker = new GymValuePicker(context);
        weightPicker.configureRange(10, 100, 2.5f);
        weightPicker.scaleSize(0.6f);
        weightPicker.setWrapSelectorWheel(false);
        weightPicker.setDescendantFocusability(GymValuePicker.FOCUS_BLOCK_DESCENDANTS);
        return weightPicker;
    }

    private GymValuePicker buildCountPicker(Context context) {
        GymValuePicker countPicker = new GymValuePicker(context);
        countPicker.setMaxValue(50);
        countPicker.scaleSize(0.6f);
        countPicker.setWrapSelectorWheel(false);
        countPicker.setDescendantFocusability(GymValuePicker.FOCUS_BLOCK_DESCENDANTS);
        return countPicker;
    }

    private GymValuePicker buildExerciseTypePicker(Context context) {
        GymValuePicker exerciseTypePicker = new GymValuePicker(context);
        exerciseTypePicker.configureEnumValues(Exercise.TYPE.values());
        exerciseTypePicker.scaleSize(0.6f);
        exerciseTypePicker.setWrapSelectorWheel(false);
        exerciseTypePicker.setDescendantFocusability(GymValuePicker.FOCUS_BLOCK_DESCENDANTS);
        return exerciseTypePicker;
    }

    private void persistAttempt(Context context, Long exerciseId) {
        String weight = weightPicker.getDisplayedValues()[weightPicker.getValue()];
        int count = countPicker.getValue();
        attemptId = attemptService.persistAttempt(context, exerciseId, count, weight);
        Toast.makeText(context, "Attempt " + weight + "/" + count + " persisted", Toast.LENGTH_SHORT).show();
    }

    private void persistExerciseType(Context context) {
        String exerciseType = exerciseTypePicker.getDisplayedValues()[exerciseTypePicker.getValue()];
        exerciseId = exerciseService.persistExercise(context, workoutItem.getId(), exerciseType);
        Toast.makeText(context, "Exercise persisted", Toast.LENGTH_SHORT).show();
    }
}
