package org.gym.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.gym.Workout;
import org.gym.WorkoutFactory;
import org.gym.activity.R;

import java.io.File;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class DummySectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public DummySectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Workout workoutItem = (Workout) WorkoutFactory.getExercisesCollection().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);

        View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
        TextView workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        TextView workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.workout_picture);


        workoutNameTextView.setText(workoutItem.getName());
        workoutDescrTextView.setText(workoutItem.getDescription());
        //imageView.setImageDrawable(Drawable.createFromPath("pictures/photo_training/crunch.png"));
        //imageView.setImageResource(R.drawable.crunch);
        //imageView.setImageDrawable(Drawable.c);

        return rootView;
    }


}
