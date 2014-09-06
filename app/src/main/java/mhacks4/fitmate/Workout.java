package mhacks4.fitmate;

import android.app.LauncherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drew on 9/6/2014.
 */
public class Workout extends LauncherActivity.ListItem {
    private List<Integer> heartRate;
    private List<Double> speed;
    private String title;

    public Workout(){
        heartRate = new ArrayList<Integer>();
        speed = new ArrayList<Double>();
        title = "";
    }

    public Workout(String title){
        heartRate = new ArrayList<Integer>();
        speed = new ArrayList<Double>();
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }
    public String toString(){
        return title;
    }
}
