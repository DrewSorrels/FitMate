package mhacks4.fitmate;

import java.util.List;

/**
 * Analysis of heart rate to identify rest periods, and times of extreme strain.
 */
public class HeartRateAnalyzer extends Analyzer {

    public HeartRateAnalyzer(List<Integer> data){
        this.data = data;
    }

    @Override
    public void populateRestRange() {
        for(int i = 0; i < data.size(); i++){
            if(i >= 2){
                // Make sure you have enough data to see if you are not exererting yourself.
                if((data.get(i) * 1.05 < data.get(i - 2) && data.get(i) < data.get(i - 1)) || data.get(i - 2) <= 68){
                    // Have decreased over the last two time intervals
                    restStart.add(i - 2);
                } else if(this.restStart.size() > this.restEnd.size() && data.get(i) > data.get(i - 2) && data.get(i) > data.get(i - 1)){
                    restEnd.add(i);
                }

                if(data.get(i) >= data.get(i - 2) * 1.2){
                    spikePoints.add(i);
                }
            }
        }
    }
}
