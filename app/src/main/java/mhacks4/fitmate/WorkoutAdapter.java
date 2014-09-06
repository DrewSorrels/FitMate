package mhacks4.fitmate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Drew on 9/6/2014.
 */
public class WorkoutAdapter extends ArrayAdapter<Workout> {

    private Activity activity;
    private ArrayList<Workout> lWorkout;
    private static LayoutInflater inflater = null;

    public WorkoutAdapter (Activity activity, int textViewResourceId,ArrayList<Workout> lWorkout) {
        super(activity, textViewResourceId, lWorkout);
        try {
            this.activity = activity;
            this.lWorkout = lWorkout;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return lWorkout.size();
    }

    public Workout getItem(Workout position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.activity_overview, null);
                holder = new ViewHolder();

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.display_name.setText(lWorkout.get(position).getTitle());


        } catch (Exception e) {


        }
        return vi;
    }
}
