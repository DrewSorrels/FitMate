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

        final ListView listview = (ListView) findViewById(android.R.id.list);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        final ArrayList<Workout> list = new ArrayList<Workout>();
        for (int i = 0; i < values.length; ++i) {
            list.add(new Workout(values[i]));
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        final Button btn = (Button)findViewById(R.id.new_workout);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                list.add(new Workout("New Workout"));
                listview.invalidateViews();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
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
