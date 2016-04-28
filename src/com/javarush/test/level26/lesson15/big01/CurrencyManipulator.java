package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> res = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Map<Integer,Integer> copyOfDenominations=new HashMap<>(denominations);
        List<Integer> denominationsSorted = new ArrayList<>(denominations.keySet());
        Collections.sort(denominationsSorted);
        int i = denominationsSorted.size() - 1;
        int thisDenominationCount=0;
        while (i >= 0) {
            int maxDenomination = denominationsSorted.get(i);
            Integer banknotesLeft = denominations.get(maxDenomination);
            if (expectedAmount >= maxDenomination && banknotesLeft > 0) {
                expectedAmount -= maxDenomination;
                denominations.put(maxDenomination, banknotesLeft - 1);
                thisDenominationCount++;
            }
            else {
                if (thisDenominationCount!=0)
                    res.put(maxDenomination, thisDenominationCount);
                thisDenominationCount=0;
                i--;
            }
        }
        if (expectedAmount>0) {
            denominations.putAll(copyOfDenominations);
            throw new NotEnoughMoneyException();
        }
        return res;
    }
}
