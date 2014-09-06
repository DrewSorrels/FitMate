package mhacks4.fitmate;

import java.util.List;

/**
 * Highest tier of the Analyzer type.
 */
public abstract class Analyzer {
    protected List<Integer> restStart;
    protected List<Integer> restEnd;

    protected List<Integer> data;
    protected List<Integer> spikePoints;

    public abstract void populateRestRange();
}
