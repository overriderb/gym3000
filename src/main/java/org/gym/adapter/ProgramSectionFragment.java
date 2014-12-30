package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.gym.animation.DropDownAnimation;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;
import org.gym.activity.R;
import org.gym.helper.SharedPreferencesHelper;

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
    ImageView openHidePicture;
    ImageView openHideDescription;
    FrameLayout frameLayout;
    CurrentProgramCache cache;


    public ProgramSectionFragment() {
        cache = CurrentProgramCache.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutItem = cache.getWorkoutList().get(getArguments().getInt(ARG_SECTION_NUMBER));

        rootView = inflater.inflate(R.layout.program_pages, container, false);

        workoutNameTextView = (TextView) rootView.findViewById(R.id.workout_title);
        workoutNameTextView.setText(workoutItem.getName());

        workoutDescrTextView = (TextView) rootView.findViewById(R.id.workout_descr);
        workoutDescrTextView.setText(workoutItem.getDescription());
        LinearLayout.LayoutParams workoutDescrLayoutParams = (LinearLayout.LayoutParams)workoutDescrTextView.getLayoutParams();
        workoutDescrLayoutParams.height = SharedPreferencesHelper.getInt(this.getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT);
        workoutDescrTextView.setLayoutParams(workoutDescrLayoutParams);

        imageView = (ImageView) rootView.findViewById(R.id.workout_picture);
        imageView.setImageResource(workoutItem.getPictureId());

        frameLayout = (FrameLayout)rootView.findViewById(R.id.absolute_layout_picture);
        LinearLayout.LayoutParams absoluteLayoutParams = (LinearLayout.LayoutParams)frameLayout.getLayoutParams();
        absoluteLayoutParams.height = SharedPreferencesHelper.getInt(this.getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT);
        frameLayout.setLayoutParams(absoluteLayoutParams);

        openHidePicture = (ImageView) rootView.findViewById(R.id.open_hide_picture_button);
        openHidePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPicture();
            }
        });

        openHideDescription = (ImageView) rootView.findViewById(R.id.open_hide_description_button);
        openHideDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDescription();
            }
        });

        return rootView;
    }

    private void onClickPicture(){
        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_PICTURE_OPEN)){
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_PICTURE_OPEN, false);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT, 40);
            openHidePicture.setImageResource(R.drawable.circule_blue_normal_plus);
        } else {
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_PICTURE_OPEN, true);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.PICTURE_HEIGHT, 200);
            openHidePicture.setImageResource(R.drawable.circule_blue_normal_minus);
        }
        DropDownAnimation animation = new DropDownAnimation(frameLayout,
                SharedPreferencesHelper.getInt(getActivity(),
                        SharedPreferencesHelper.PICTURE_HEIGHT), true);
        frameLayout.startAnimation(animation);
    }

    private void onClickDescription(){
        if(SharedPreferencesHelper.getBool(getActivity(),SharedPreferencesHelper.IS_DESCRIPTION_OPEN)){
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_DESCRIPTION_OPEN, false);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT, 1);
            openHideDescription.setImageResource(R.drawable.circule_blue_normal_plus);

        } else {
            SharedPreferencesHelper.setBool(getActivity(), SharedPreferencesHelper.IS_DESCRIPTION_OPEN, true);
            SharedPreferencesHelper.setInt(getActivity(), SharedPreferencesHelper.DESCRIPTION_HEIGHT, FrameLayout.LayoutParams.WRAP_CONTENT);
            openHideDescription.setImageResource(R.drawable.circule_blue_normal_minus);
        }
        DropDownAnimation animation = new DropDownAnimation(workoutDescrTextView,
                SharedPreferencesHelper.getInt(getActivity(),
                        SharedPreferencesHelper.DESCRIPTION_HEIGHT), true);
        workoutDescrTextView.startAnimation(animation);
    }

}
