package com.task.split;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class MoneySplitService {

    private static final double[] bills = {200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
    private Map<Double,Integer> lastSplit = new HashMap<>();
    //Flag for checking if it is a first Calculation
    private boolean isFirstCalculation = true;

    /**
     * Calculates either split information for the first operation or difference information between last and current amount for next operations
     * @param money Money amount in Double, which should be split in bills
     * @return A SplitEntity, which encapsulates split result and result type
     * */
    public SplitEntity getSplitInfo(double money){
        Map<Double, Integer> resultSplit = new LinkedHashMap<>();
        if(isFirstCalculation){

            resultSplit = calculateSplit(money);
            lastSplit = Map.copyOf(resultSplit);
            isFirstCalculation = false;

            return new SplitEntity(resultSplit,"Anzahl");
        }
        else {
            resultSplit = calculateSplitDifference(lastSplit, calculateSplit(money));
            lastSplit = calculateSplit(money);
            return new SplitEntity(resultSplit, "Differenz");
        }
    }

    /**
     * Helping Method for calculation of split information
     * @param money Money amount in Double, which should be split in bills
     * @return A Map<Double, Integer>, which holds bill as key and amount of bills as value
     * */
    private Map<Double,Integer> calculateSplit(double money){

        Map<Double, Integer> resultSplit = new LinkedHashMap<>();
        double currentMoney = money;
        int amountBills;

        //Using of BigDecimal to avoid rounding errors in division operation
        BigDecimal bigDecimalCurrentAmount;
        BigDecimal currentBill;

        for(double bill: bills){
            currentBill = new BigDecimal(bill);

            //Calculate amount of bills needed and transform result from BigDecimal to int
            bigDecimalCurrentAmount = BigDecimal.valueOf(currentMoney)
                    .divide(currentBill,2, RoundingMode.HALF_DOWN);
            amountBills = bigDecimalCurrentAmount.intValue();

            //Substract counted bills from original sum
            if(amountBills > 0) {
                resultSplit.put(bill,amountBills);
                currentMoney = currentMoney - currentBill.doubleValue()*amountBills;
            }

        }
        return resultSplit;
    }
    /**
     * Helping Method for calculation of difference between splits
     * @param lastSplit,currentSplit Splits of the last and current Calculation as Map<Double, Integer>
     * @return A Map<Double, Integer>, which holds bill as key and difference as value
     * */
    private Map<Double, Integer> calculateSplitDifference(Map<Double,Integer> lastSplit, Map<Double,Integer> currentSplit){
        Map<Double, Integer> resultDifference = new LinkedHashMap<>();

        TreeSet<Double> appearedBills = new TreeSet<>();

        //Add appeared bills of last and current splitting in one ordered Set
        appearedBills.addAll(lastSplit.keySet());
        appearedBills.addAll(currentSplit.keySet());

        //Calculate difference for last (highest) bill and add it to result map
        while(!appearedBills.isEmpty()){
            Double highestBill = appearedBills.pollLast();
            int lastAmount = lastSplit.getOrDefault(highestBill,0);
            int currentAmount = currentSplit.getOrDefault(highestBill,0);

            resultDifference.put(highestBill,lastAmount-currentAmount);

        }

        return resultDifference;
    }
}
