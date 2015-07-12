package com.mdagis.atribcor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 *
 * @author agis
 */
public class CorrelationFinder {

    /**
     * This flag indicates if the calculation will handle data as boolean values
     */
    private boolean booleanCalculation;

    /**
     * This is the array that we will try to calculate the correlations
     */
    private final double[][] multi = new double[][]{
        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 1, 0, 0, 0}
    };

    /**
     * This Map will hold the scoring of each variable corelation
     */
    private HashMap<Coord, Double> scoreMap;

    /**
     * This map will hold the results for printing
     *
     */
    private HashMap<Integer, VariableResult> resultMap;

    /**
     * the method will fill the scoreMap with all available keys based on multi
     * array
     */
    public void populateScoreMap() {

        scoreMap = new HashMap<>();

        for (int i = 0; i < multi[0].length; i++) {
            int pointer = i + 1;
            for (int j = pointer; j < multi[0].length; j++) {
                Coord coord = new Coord(i, j);
                scoreMap.put(coord, 0.0);
            }
        }

        System.out.println(scoreMap.keySet().size());

    }

    /**
     * Loops the 2D array to create score
     */
    public void findCorrelation() {

        for (double[] multi1 : multi) {
            scoreArray(multi1);
        }
    }

    /**
     * Calculates the score per line and updates global score
     *
     * @param line
     */
    private void scoreArray(double[] line) {

        Double val;
        val = 0.0;
        for (int i = 0; i < line.length; i++) {
            int pointer = i + 1;
            for (int j = pointer; j < line.length; j++) {

                Coord coord = new Coord(i, j);
                val = scoreMap.get(coord);
                if (line[i] > 0 && line[j] > 0) {
                    val = this.booleanCalculation ? val + 1 : val + line[i] + line[j];
                }
                scoreMap.put(coord, val);
            }
        }
    }

    public void populateResultMap() {

        resultMap = new HashMap<>();
        for (double[] ar1 : multi) {
            for (int j = 0; j < ar1.length; j++) {

                if (!resultMap.containsKey(j)) {
                    VariableResult result = new VariableResult(0.0);
                    resultMap.put(j, result);
                }

                if (ar1[j] > 0) {
                    double currentValue = resultMap.get(j).getOccurrences();
                    currentValue = this.booleanCalculation ? currentValue + 1 : currentValue + ar1[j];
                    resultMap.get(j).setOccurrences(currentValue);
                }

            }
        }

        for (int i = 0; i < multi[0].length; i++) {
            Map<Integer, Double> lineScoreMap = calculateColScore(i);
            resultMap.get(i).setLineScore(lineScoreMap);
        }
    }

    public void printAllVariablesReport() {

        Map<Integer, Double> lineScoreMap;
        for (Integer k : resultMap.keySet()) {

            lineScoreMap = resultMap.get(k).getLineScore();
            lineScoreMap = CollectionsUtil.sortByValue(lineScoreMap);

            for (Integer i : lineScoreMap.keySet()) {
                System.out.println(String.valueOf(i) + " Score ---> " + String.valueOf(lineScoreMap.get(i)) + " Percentage ---> " + 100 * lineScoreMap.get(i) / resultMap.get(k).getOccurrences() + "%");
            }

        }
    }

    /**
     * Calculates a variable scoring for every other variable
     *
     * @param col
     * @return
     */
    private Map<Integer, Double> calculateColScore(Integer col) {

        Map<Integer, Double> lineScoreMap = new TreeMap<>();
        for (Coord coord : scoreMap.keySet()) {
            if (coord.hasElement(col)) {
                Integer otherCoord = coord.getOtherCoord(col);
                lineScoreMap.put(otherCoord, (double) scoreMap.get(coord));
            }
        }

        return lineScoreMap;
    }
    
    
    public void createPearsonsCorrelation(){
        
        PearsonsCorrelation corr = new PearsonsCorrelation(multi);
        RealMatrix matrix = corr.getCorrelationMatrix();
        System.out.println(matrix.toString());
        
//double result = corr.correlation(x, y);
    }

    public boolean isBooleanCalculation() {
        return booleanCalculation;
    }

    public void setBooleanCalculation(boolean booleanCalculation) {
        this.booleanCalculation = booleanCalculation;
    }

}
