package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Pair;
import com.evgeni.models.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final PairService pairService;

    public EmployeeServiceImpl(PairService pairService) {
        this.pairService = pairService;
    }

    @Override
    public Map.Entry<Pair, Long> findLongestTimeWorkedPair(Map<Project, ArrayList<Employee>> projects) {
        Map<Pair, Long> timeWorkedTogether;
        List<Pair> pairs = new ArrayList<>();

        for (Map.Entry<Project, ArrayList<Employee>> project : projects.entrySet()) {

            // If the number of workers which are working on this project is less than 2 we are skipping them.
            if (project.getValue().size() >= 2) {
                List<Pair> generated = pairService.createPairs(project.getKey(), project.getValue());
                pairs = pairService.mergeDuplicatePairs(pairs, generated);
            }
        }

        timeWorkedTogether = pairService.getPairsWithTime(pairs);

        return pairService.findLongestTimeWorkedPair(timeWorkedTogether);
    }
}