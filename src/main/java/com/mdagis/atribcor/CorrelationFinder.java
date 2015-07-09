package com.mdagis.atribcor;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author agis
 */
public class CorrelationFinder {

    private boolean booleanCalculation;

    private final Integer[][] multi = new Integer[][]{
        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 1, 0, 0, 0}
    };

    private HashMap<Coord, Double> scoreMap;

    public void populateMap() {

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

    public void findCorrelation() {

        for (Integer[] multi1 : multi) {
            scoreArray(multi1);
        }
    }

    private void scoreArray(Integer[] line) {

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

    public void printColResult(Integer col) {

        Map<Integer, Double> lineScoreMap = calculateColScore(col);
        
        lineScoreMap = CorrelationFinder.sortByValue(lineScoreMap);

        for (Integer i : lineScoreMap.keySet()) {
            System.out.println(String.valueOf(i) + " ---> " + String.valueOf(lineScoreMap.get(i)));
        }

    }

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

    static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public boolean isBooleanCalculation() {
        return booleanCalculation;
    }

    public void setBooleanCalculation(boolean booleanCalculation) {
        this.booleanCalculation = booleanCalculation;
    }

}
