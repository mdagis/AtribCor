package com.mdagis.atribcor;

import java.util.Map;

/**
 *
 * @author agis
 */
public class VariableResult {

    private double occurrences;
    private Map<Integer, Double> lineScore;

    public VariableResult(double occurrences) {
        this.occurrences = occurrences;
    }

    public double getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(double occurrences) {
        this.occurrences = occurrences;
    }

    public Map<Integer, Double> getLineScore() {
        return lineScore;
    }

    public void setLineScore(Map<Integer, Double> lineScore) {
        this.lineScore = lineScore;
    }

}
