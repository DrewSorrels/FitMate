package mhacks4.fitmate;

import java.util.List;

/**
 * Highest tier of the Analyzer type.
 */
public class Analyzer {
    protected List<Integer> restStart;
    protected List<Integer> restEnd;

    protected List<Integer> data;
    protected List<Integer> spikePoints;

    protected List<Integer> exerciseStart;
    protected List<Integer> exerciseEnd;

    public List<Integer> getRestStart() {
        return restStart;
    }

    public void setRestStart(List<Integer> restStart) {
        this.restStart = restStart;
    }

    public List<Integer> getRestEnd() {
        return restEnd;
    }

    public void setRestEnd(List<Integer> restEnd) {
        this.restEnd = restEnd;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<Integer> getSpikePoints() {
        return spikePoints;
    }

    public void setSpikePoints(List<Integer> spikePoints) {
        this.spikePoints = spikePoints;
    }

    public List<Integer> getExerciseStart() {
        return exerciseStart;
    }

    public void setExerciseStart(List<Integer> exerciseStart) {
        this.exerciseStart = exerciseStart;
    }

    public List<Integer> getExerciseEnd() {
        return exerciseEnd;
    }

    public void setExerciseEnd(List<Integer> exerciseEnd) {
        this.exerciseEnd = exerciseEnd;
    }
}
