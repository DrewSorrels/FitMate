package mhacks4.fitmate;

import java.util.ArrayList;
import java.util.List;

/**
 * Analysis of heart rate to identify rest periods, and times of extreme strain.
 */
public class HeartRateAnalyzer extends Analyzer {

    /*
     * Create a new Heart Rate Analyzer object.
     */
    public HeartRateAnalyzer(List<Integer> data){
        this.data = data;
        restStart = new ArrayList<Integer>();
        restEnd = new ArrayList<Integer>();
        spikePoints = new ArrayList<Integer>();
        exerciseStart = new ArrayList<Integer>();
        exerciseEnd = new ArrayList<Integer>();
    }

   /*
    * Populate the points where a rest break starts and ends.
    */
    public void populateRestRange() {
        // First clear the lists so there is no duplicate data
        restStart.clear();
        restEnd.clear();
        // Loop over all data and find points where heart rate has decreased substantially.
        for(int i = 0; i < data.size(); i++){
            if(i >= 2){
                // Make sure you have enough data to see if you are not exerting yourself.
                if((data.get(i) * 1.05 < data.get(i - 2) && data.get(i) < data.get(i - 1)) || data.get(i - 2) <= 68){
                    // Have decreased over the last two time intervals
                    restStart.add(i - 2);
                } else if(this.restStart.size() > this.restEnd.size() && data.get(i) > 1.05 * data.get(i - 1)){
                    restEnd.add(i);
                }

            }
        }
    }

    /*
     * Find points where heart rate is above 85% of maximum for a healthy 20 y/o or where a quick drop is detected.
     */
    public void populateDangerPoints(){
        // Clear list to avoid duplicates
        spikePoints.clear();
        for(int i = 0; i < data.size(); i++){
            // Find points of above healthy max
            if(data.get(i) >= (200 * 0.85)){
                spikePoints.add(i);
            }
            // Find points where there is a drop in 20 bp in 5 seconds (close to fainting?)
            if(i > 2){
                if(data.get(i) - data.get(i - 1) <= -20){
                    spikePoints.add(i);
                }
            }
        }
    }

    /*
     * Find instances of one exercise.  e.g. 4 sets would have 3 breaks in between, but we want to classify it all as one exercise.
     */
    public void populateExercises(){
        populateRestRange();
        int restPoints = 0;
        exerciseEnd.clear();
        exerciseStart.clear();

        for(int i = 0; i < restEnd.size(); i++){
            restPoints += restEnd.get(i) - restStart.get(i) - 1;
        }
        int exStart = 0;
        int exEnd;
        int setNumber = 0;
        int restRegion = 0;
        boolean exFinished = false;
        // Add points to setStart and setEnd based on break length and
        for(int i = 0; i < data.size(); i++){
            // Start first exercise on interval starting at 0
            if(exFinished){
                exFinished = false;
                setNumber = 0;
                exStart = restEnd.get(restRegion - 1) + 1;
            }

            if(setNumber == 0){
                exerciseStart.add(exStart);
            }

            // Calculate where we are in the set
            // We want to make sure that we do not collect points from before the set starts (in the
            // case that the last rest was >= 28 time intervals, which would automatically cause the
            // exercise to complete.  Nobody rests for that long unless they are a wimp. (or they
            // are changing equipment, in which case they are most likely changing exercises)
            if(i > exStart) {
                if (i >= restStart.get(restRegion)) {
                    // We started resting
                    if (i < restEnd.get(restRegion)) {
                        // We have not finished resting, check if we have been resting excessively.
                        if (restEnd.get(restRegion) - restStart.get(restRegion) >= 28 || setNumber >= 4) {
                            // We have been resting for 28 time intervals (~2min 20s given 5s intervals) or completed 5 sets
                            exFinished = true;
                            restRegion += 1;
                            exerciseEnd.add(i - 1);
                        }
                    } else {
                        // Finished resting, so increment restRegion and setNumber
                        restRegion += 1;
                        setNumber += 1;
                    }
                }
            }
        }
    }
}
