package org.gym.dao;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by AndreyNick on 13.11.2014.
 */
public class WorkoutAdapter extends BaseAdapter {

    public static String TABLE_NAME = "workout";
    public static String ID = "_id";
    public static String PARENT_PROGRAM_ID = "patent_program_id";
    public static String NAME = "name";
    public static String PICTURE_ID = "picture_id";
    public static String DESCRIPTION = "description";

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
