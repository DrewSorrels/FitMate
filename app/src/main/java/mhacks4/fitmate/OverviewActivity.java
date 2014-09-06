package mhacks4.fitmate;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class OverviewActivity extends ListActivity {
    // The list representation of workouts
    private ListView list;
    //private WorkoutAdapter adapter;
    private List<Workout> workouts = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Create list view for index of previous workouts
        final ListView listview = (ListView) findViewById(android.R.id.list);
        String[] values = new String[]{"Workout 9/6", "Morning Run 9/2"};

        // Dummy data for list with above names.
        final ArrayList<Workout> list = new ArrayList<Workout>();
        for (int i = 0; i < values.length; ++i) {
            list.add(new Workout(values[i]));
        }
        // Adapter for display of listview
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        // Create button to create a new workout
        final Button btn = (Button)findViewById(R.id.new_workout);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add new Workout with title 'new workout'.  Temporary measure.
                list.add(new Workout("New Workout"));
                listview.invalidateViews();
            }
        });

        // When clicking an item, bring up the activity view for that workout.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /*
             * Pass through essential data from that workout for recreation in the new view.
             */
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Workout item = (Workout) parent.getItemAtPosition(position);
                Intent intent = new Intent(OverviewActivity.this, WorkoutSummaryActivity.class);
                intent.putExtra("SPEEDS", item.getSpeed().toArray());
                intent.putExtra("HEART_RATES", item.getHeartRate().toArray());
                intent.putExtra("TITLE", item.getTitle());
                startActivity(intent);
            }

        });
    }
}
