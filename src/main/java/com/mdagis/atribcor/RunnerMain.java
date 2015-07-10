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

        cor.populateScoreMap();
        cor.setBooleanCalculation(true);
        cor.findCorrelation();
        cor.populateResultMap();
        cor.printAllVariablesReport();

//        for (int i = 0; i < 10; i++){
//            System.out.println("---- Printing results for " + String.valueOf(i) + "  ------- ");
//            cor.printColResult(i);
//            System.out.println("");
//            System.out.println("------------------------------------ ");
//        }
    }

}
