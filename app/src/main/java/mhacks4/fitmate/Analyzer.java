package mhacks4.fitmate;

import java.util.List;

/**
 * Highest tier of the Analyzer type.
 */
public abstract class Analyzer {
    private List<Integer> restRange;

    public abstract void populateRestRange();
}
