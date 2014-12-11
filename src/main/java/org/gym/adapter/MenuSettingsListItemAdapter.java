package org.gym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.gym.activity.R;
import org.gym.domain.Program;

import java.util.List;

/**
 * Created by anni0913 on 11.12.2014.
 */
public class MenuSettingsListItemAdapter extends ArrayAdapter<Program> {

    List<Program> programList;

    public MenuSettingsListItemAdapter(Context context, int resource, List<Program> objects) {
        super(context, resource, objects);
        programList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Program program = programList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.settings_list_item_layout, null);
        }
        ((TextView) convertView.findViewById(R.id.settings_list_item_text_view))
                .setText(position + ". " + program.getName());
        return convertView;
    }
}
