package org.gym.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import org.gym.domain.HasNameAndDescription;

import java.util.HashMap;
import java.util.List;

/**
 * It was decided do NOT use this class
 * This class is a part of drag'n'drop lis value class
 */
public class SettingsArrayAdapter extends ArrayAdapter<String> {

    final int INVALID_ID = -1;

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public SettingsArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
