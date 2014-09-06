package mhacks4.fitmate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

/*
 * Summary page for a workout.  Displays a heartrate / movement speed graph and textual analysis of that graph for user information.
 */
public class WorkoutSummaryActivity extends Activity {
    private Workout summary;
    private GraphicalView mChartView;
    private XYMultipleSeriesDataset dataset;
    private XYMultipleSeriesRenderer renderer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_summary);

        // Instantiate instance variables
        dataset = new XYMultipleSeriesDataset();
        renderer = new XYMultipleSeriesRenderer();

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

    protected void onResume() {
        super.onResume();

        // Add heart rates to the graph
        XYSeries heartRates = new XYSeries("Heart Rate");
        XYSeriesRenderer ren = new XYSeriesRenderer();
        List<Integer> rates = summary.getHeartRate();
        for(int i = 0; i < rates.size(); i++){
            heartRates.add(i, rates.get(i));
        }

        // Update the dataset
        dataset.clear();
        dataset.addSeries(heartRates);

        // Check if the chart is already displayed
        if (mChartView == null) {
            // If not, add it to the view
            // Only want to add one renderer
            renderer.addSeriesRenderer(ren);
            renderer.setApplyBackgroundColor(true);
            renderer.setBackgroundColor(Color.LTGRAY);
            renderer.setMarginsColor(Color.LTGRAY);

            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getLineChartView(this, dataset,
                    renderer);
            layout.addView(mChartView, new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        } else {
            // Repaint it if it has already been added.
            mChartView.repaint();
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
