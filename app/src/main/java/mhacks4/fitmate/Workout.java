package mhacks4.fitmate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drew on 9/6/2014.
 */
public class Workout {
    private List<Integer> heartRate;
    private List<Double> speed;

    public Workout(){
        heartRate = new ArrayList<Integer>();
        speed = new ArrayList<Double>();
    }

    public void addHeartRate(int heartRate) {
        this.heartRate.add(heartRate);
    }

    public void addSpeed(double speed) {
        this.speed.add(speed);
    }

    public List<Integer> getHeartRate() {
        return heartRate;
    }

    public List<Double> getSpeed() {
        return speed;
    }
}
