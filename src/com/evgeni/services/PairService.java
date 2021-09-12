package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Pair;
import com.evgeni.models.Project;

import java.util.List;
import java.util.Map;

public interface PairService {

    List<Pair> createPairs(Project project, List<Employee> employees);

    List<Pair> mergeDuplicatePairs(List<Pair> pairs, List<Pair> generated);

    Map<Pair,Long> getPairsWithTime(List<Pair> pairs);

    Map.Entry<Pair, Long> findLongestTimeWorkedPair(Map<Pair, Long> timeWorkedTogether);
}
