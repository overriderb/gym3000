package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.gym.Workout;
import org.gym.WorkoutFactory;

import java.util.Locale;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ProgramPagerAdapter extends FragmentPagerAdapter {

    public final static String PAGE_TITLE_PREFIX = "Training program ";

    private int pageCount;

    public ProgramPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        // hardcoded value
        this.pageCount = WorkoutFactory.getExercisesCollection().size();
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        Fragment fragment = new DummySectionFragment();
        Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return PAGE_TITLE_PREFIX + (position + 1);
        Workout workoutItem = (Workout) WorkoutFactory.getExercisesCollection().get(position);
        return position + 1 + ". " + workoutItem.getName();
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
