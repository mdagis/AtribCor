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

        cor.populateMap();
        cor.setBooleanCalculation(true);
        cor.findCorrelation();
        for (int i = 0; i < 10; i++){
            System.out.println("---- Printing results for " + String.valueOf(i) + "  ------- ");
            cor.printColResult(i);
            System.out.println("");
            System.out.println("------------------------------------ ");
        }
        
        
    }

}
