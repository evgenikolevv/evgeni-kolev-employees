package com.evgeni.utils;

import com.evgeni.models.Pair;

import java.util.Map;

public class PairPresenter {

    /**
     * Prints in the console the couple of workers and their worked days together.
     */
    public void print(Map.Entry<Pair, Long> employeesPair) {
        System.out.println("Employee pair[" + employeesPair.getKey().getFirst() + ", " + employeesPair.getKey().getSecond() + "] worked days:"
                + employeesPair.getValue());
    }
}
