package com.mdagis.atribcor;

/**
 *
 * @author agis
 */
public class RunnerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CorrelationFinder cor = new CorrelationFinder();
        cor.createPearsonsCorrelation();

//        cor.populateScoreMap();
//        cor.setBooleanCalculation(true);
//        cor.findCorrelation();
//        cor.populateResultMap();
//        cor.printAllVariablesReport();

    }

}
