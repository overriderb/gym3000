package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.gym.object.Workout;
import org.gym.WorkoutFactory;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ProgramPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;



    public ProgramPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.pageCount = WorkoutFactory.getWorkoutsFromDb().size();

    }

    @Override
    public Fragment getItem(int position) {
        Fragment currentFragment = new ProgramSectionFragment();
        Bundle args = new Bundle();

        args.putInt(ProgramSectionFragment.ARG_SECTION_NUMBER, position);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Workout workoutItem = WorkoutFactory.getWorkoutsFromDb().get(position);
        return position + 1 + ". " + workoutItem.getName();
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
