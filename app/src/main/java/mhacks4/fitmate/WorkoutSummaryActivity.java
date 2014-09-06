package mhacks4.fitmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.achartengine.model.XYSeries;


public class WorkoutSummaryActivity extends Activity {
    private Workout summary;
    private XYSeries series = new XYSeries("London Temperature hourly");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_summary);

        // Reconstruct the workout summary
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        summary = new Workout(title);

        int[] heartRates = intent.getIntArrayExtra("HEART_RATES");
        double[] speeds = intent.getDoubleArrayExtra("SPEEDS");

        // Add values in to workout summary
        if(heartRates != null)
            for(int hr : heartRates){
                summary.addHeartRate(hr);
            }
        if(speeds != null)
            for(double s : speeds){
                summary.addSpeed(s);
            }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.workout_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
