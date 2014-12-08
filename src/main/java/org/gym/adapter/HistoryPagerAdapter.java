package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;

import java.util.List;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class HistoryPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;
    private CurrentProgramCache cache;

    public HistoryPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        cache = CurrentProgramCache.getInstance();
        this.pageCount = cache.getWorkoutList().size();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment currentFragment = new HistorySectionFragment();
        Bundle args = new Bundle();
        args.putInt(HistorySectionFragment.ARG_SECTION_NUMBER, position);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Workout workoutItem = cache.getWorkoutList().get(position);
        return position + 1 + ". " + workoutItem.getName();
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
