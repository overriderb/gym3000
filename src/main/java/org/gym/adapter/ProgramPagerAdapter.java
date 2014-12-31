package org.gym.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;

import java.util.Map;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ProgramPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;
    private CurrentProgramCache cache;

    public ProgramPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        cache = CurrentProgramCache.getInstance();
        this.pageCount = cache.getWorkoutList().size();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment currentFragment = new ProgramSectionFragment();
        Bundle args = new Bundle();
        args.putInt(ProgramSectionFragment.ARG_SECTION_NUMBER, position);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    //http://tamsler.blogspot.com/2011/11/android-viewpager-and-fragments-part-ii.html
    public void destroyItem(View container, int position, Object object){

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
