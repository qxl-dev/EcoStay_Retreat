package com.ecostay.ui.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecostay.R;
import com.ecostay.data.model.ActivityItem;
import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    private Context context;
    private List<ActivityItem> activityList;

    public ActivityAdapter(Context context, List<ActivityItem> activityList) {
        this.context = context;
        this.activityList = activityList;
    }

    @Override
    public int getCount() {
        return activityList.size();
    }

    @Override
    public Object getItem(int position) {
        return activityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
        }

        ActivityItem activity = activityList.get(position);

        TextView title = convertView.findViewById(R.id.tvActivityTitle);
        TextView description = convertView.findViewById(R.id.tvActivityDescription);
        TextView price = convertView.findViewById(R.id.tvActivityPrice);

        title.setText(activity.title);
        description.setText(activity.description);
        price.setText("$" + activity.price);

        return convertView;
    }
}
