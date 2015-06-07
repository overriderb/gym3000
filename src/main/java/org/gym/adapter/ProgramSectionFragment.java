package org.gym.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.gym.cache.CurrentProgramCache;
import org.gym.component.GymValuePicker;
import org.gym.activity.R;
import org.gym.model.Exercise;
import org.gym.model.ExerciseType;
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

    private ExerciseType exerciseType;
    View rootView;
    TextView exerciseTypeNameTextView;
    TextView exerciseTypeDescrTextView;
    ImageView imageView;
    FrameLayout frameLayout;
    CurrentProgramCache cache;
    private GymValuePicker weightPicker;
    private GymValuePicker countPicker;
    private GymValuePicker attemptTypePicker;

    private Long exerciseId;
    private Long attemptId;

    private AttemptService attemptService;
    private ExerciseService exerciseService;

    public ProgramSectionFragment() {
        cache = CurrentProgramCache.getInstance();
        attemptService = AttemptService.getInstance();
        exerciseService = ExerciseService.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseType = cache.getExerciseTypes().get(getArguments().getInt(ARG_SECTION_NUMBER));

        rootView = inflater.inflate(R.layout.program_pages, container, false);
        exerciseTypeNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        exerciseTypeDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout);

        exerciseTypeNameTextView.setText(exerciseType.getName());
        exerciseTypeDescrTextView.setText(exerciseType.getDescription());
        imageView.setImageResource(exerciseType.getPictureId());

        // Configuration of exercise type section
        /*final LinearLayout attemptPickerLayout = (LinearLayout) rootView.findViewById(R.id.attemptPickerLayout);
        final LinearLayout exerciseTypeLayout = (LinearLayout) rootView.findViewById(R.id.exerciseTypeLayout);
        attemptTypePicker = buildAttemptTypePicker(exerciseTypeLayout.getContext());
        exerciseTypeLayout.addView(attemptTypePicker);
        final Button saveExerciseButton = (Button) rootView.findViewById(R.id.saveExerciseType);
        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                persistExerciseType(saveExerciseButton.getContext());
                exerciseTypeLayout.setVisibility(View.GONE);
                attemptPickerLayout.setVisibility(View.VISIBLE);
            }
        });*/

        // Configuration of attempt save section
        /*LinearLayout countLayout = (LinearLayout) rootView.findViewById(R.id.countPickerLayout);
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
        });*/

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

    private GymValuePicker buildExerciseLevelPicker(Context context) {
        GymValuePicker exerciseLevelPicker = new GymValuePicker(context);
        exerciseLevelPicker.configureEnumValues(Exercise.Level.values());
        exerciseLevelPicker.scaleSize(0.6f);
        exerciseLevelPicker.setWrapSelectorWheel(false);
        exerciseLevelPicker.setDescendantFocusability(GymValuePicker.FOCUS_BLOCK_DESCENDANTS);
        return exerciseLevelPicker;
    }

    private void persistAttempt(Context context, Long exerciseId) {
        String weight = weightPicker.getDisplayedValues()[weightPicker.getValue()];
        int count = countPicker.getValue();
//        attemptId = attemptService.save(context, exerciseId, count, weight);
        Toast.makeText(context, "Attempt " + weight + "/" + count + " persisted", Toast.LENGTH_SHORT).show();
    }

    private void persistExerciseType(Context context) {
        String attemptType = attemptTypePicker.getDisplayedValues()[attemptTypePicker.getValue()];
//        exerciseId = exerciseService.save(context, workoutItem.getId(), attemptType);
        Toast.makeText(context, "Exercise persisted", Toast.LENGTH_SHORT).show();
    }
}
