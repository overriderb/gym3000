package org.gym.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.gym.animation.DropDownAnimation;
import android.widget.Toast;
import org.gym.cache.CurrentProgramCache;
import org.gym.component.GymValuePicker;
import org.gym.activity.R;
import org.gym.model.Exercise;
import org.gym.model.ExerciseType;
import org.gym.helper.SharedPreferencesHelper;
import org.gym.service.AttemptService;
import org.gym.service.ExerciseService;

import java.util.List;

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

    private Workout workoutItem;
    private View rootView;
    private TextView workoutNameTextView;
    private TextView workoutDescrTextView;
    private TextView workoutDescrTitleTextView;

    private Long exerciseId;
    private Long attemptId;
    private ImageView imageView;
    private ImageView openHidePictureImage;
    private ImageView openHideDescriptionImage;
    private FrameLayout frameLayout;

    private AttemptService attemptService;
    private ExerciseService exerciseService;

    private CurrentProgramCache cache;



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
//        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout_picture);
//        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
//        workoutDescrTitleTextView = (TextView) rootView.findViewById(R.id.description_title);
//        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        openHidePictureImage = (ImageView) rootView.findViewById(R.id.open_hide_picture_button);
        openHideDescriptionImage = (ImageView) rootView.findViewById(R.id.open_hide_description_button);

        exerciseTypeNameTextView.setText(exerciseType.getName());
        exerciseTypeDescrTextView.setText(exerciseType.getDescription());
        imageView.setImageResource(exerciseType.getPictureId());
//        workoutNameTextView.setText(workoutItem.getName());
//        workoutDescrTextView.setText(workoutItem.getDescription());
//
//        imageView.setImageResource(workoutItem.getPictureId());


        workoutNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPicture();
            }
        });
        workoutDescrTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDescription();
            }
        });

        fillPictureAndDescriptionHeight();
        fillOpenHideImages();

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

    private void fillPictureAndDescriptionHeight(){
        LinearLayout.LayoutParams workoutDescrLayoutParams = (LinearLayout.LayoutParams)workoutDescrTextView.getLayoutParams();
        workoutDescrLayoutParams.height = SharedPreferencesHelper.getInt(this.getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT);
        workoutDescrTextView.setLayoutParams(workoutDescrLayoutParams);

        LinearLayout.LayoutParams absoluteLayoutParams = (LinearLayout.LayoutParams)frameLayout.getLayoutParams();
        absoluteLayoutParams.height = SharedPreferencesHelper.getInt(this.getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT);
        frameLayout.setLayoutParams(absoluteLayoutParams);
    }

    private void fillOpenHideImages(){
        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_PICTURE_OPEN)){
            openHidePictureImage.setImageResource(R.drawable.minus_white);
        } else {
            openHidePictureImage.setImageResource(R.drawable.plus_white);
        }

        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_DESCRIPTION_OPEN)){
            openHideDescriptionImage.setImageResource(R.drawable.minus_white);
        } else {
            openHideDescriptionImage.setImageResource(R.drawable.plus_white);
        }
    }

    private void onClickPicture(){
        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_PICTURE_OPEN)){
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_PICTURE_OPEN, false);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT, 50);
            openHidePictureImage.setImageResource(R.drawable.plus_white);
        } else {
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_PICTURE_OPEN, true);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT, 200);
            openHidePictureImage.setImageResource(R.drawable.minus_white);
        }
        DropDownAnimation animation = new DropDownAnimation(frameLayout,
                SharedPreferencesHelper.getInt(getActivity(),
                        SharedPreferencesHelper.PICTURE_HEIGHT));
        frameLayout.startAnimation(animation);
    }

    private void onClickDescription(){
        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_DESCRIPTION_OPEN)){
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_DESCRIPTION_OPEN, false);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT, 1);
            openHideDescriptionImage.setImageResource(R.drawable.plus_white);

        } else {
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_DESCRIPTION_OPEN, true);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT, FrameLayout.LayoutParams.WRAP_CONTENT);
            openHideDescriptionImage.setImageResource(R.drawable.minus_white);
        }
        DropDownAnimation animation = new DropDownAnimation(workoutDescrTextView,
                SharedPreferencesHelper.getInt(getActivity(),
                        SharedPreferencesHelper.DESCRIPTION_HEIGHT));
        workoutDescrTextView.startAnimation(animation);
    }


}
