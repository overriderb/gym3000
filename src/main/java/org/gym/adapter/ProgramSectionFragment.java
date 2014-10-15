package org.gym.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.gym.object.Workout;
import org.gym.WorkoutFactory;
import org.gym.activity.R;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
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

    public ProgramSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = (Workout) WorkoutFactory.getTestExercisesCollection().get(getArguments().getInt(ARG_SECTION_NUMBER));

        rootView = inflater.inflate(R.layout.program_pages, container, false);
        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout);

        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        imageView.setImageResource(workoutItem.getPictureId());

        return rootView;
    }
}
