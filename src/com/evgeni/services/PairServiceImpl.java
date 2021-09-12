package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Pair;
import com.evgeni.models.Project;
import com.evgeni.utils.PairTimeCalculator;

import java.util.*;

/**
 * This class is working with pairs of workers
 */
public class PairServiceImpl implements PairService{

    /**
     * Creates pair of workers who have worked together on a project.
     *
     * @param project   проект, по който employees са работили project
     * @param employees List служители, които са работили по project e
     * @return pairs of workers
     */
    @Override
    public List<Pair> createPairs(Project project, List<Employee> employees) {
        List<Pair> employeesPairs = new ArrayList<>();

        for (int index = 0; index < employees.size(); index++) {
            for (int j = index + 1; j <= employees.size() - 1; j++) {

                Pair employeesPair;
                if (employees.get(index).getId() < employees.get(j).getId()) {
                    employeesPair = new Pair(employees.get(index), employees.get(j));
                } else {
                    employeesPair = new Pair(employees.get(j), employees.get(index));
                }

                Date[] period = PairTimeCalculator.getPeriod(employeesPair);
                employeesPair.addProject(project, period);
                employeesPairs.add(employeesPair);
            }
        }

        return employeesPairs;
    }

    /**
     * If generated exists in pairs then the method is adding the projects from generated to pairs
     *
     * @param pairs unique pairs of workers
     * @param generated pairs of workers who are going to be added to @pairs
     * @return unique pairs of workers
     */
    @Override
    public List<Pair> mergeDuplicatePairs(List<Pair> pairs, List<Pair> generated) {
        if (!pairs.containsAll(generated)) {
            pairs.addAll(generated);
        } else {
            for (Pair pair : generated) {
                Integer index = pairs.indexOf(pair);
                for (Map.Entry<Project, Date[]> pairProject : pair.getProjects().entrySet()) {
                    pairs.get(index).getProjects().put(pairProject.getKey(), pairProject.getValue());
                }
            }
        }

        return pairs;
    }

    /**
     * Calculates the work time of pairs
     * @return Map<EmployeesPair, Long> - pairs and their time
     */
    @Override
    public Map<Pair, Long> getPairsWithTime(List<Pair> pairs) {
        Map<Pair, Long> timeWorkedTogether = new HashMap<>();

        for (Pair pair : pairs) {
            timeWorkedTogether.put(pair, PairTimeCalculator.getDays(pair));
        }

        return timeWorkedTogether;
    }

    /**
     * Finds the pair which have worked together for the longest period.
     * @param timeWorkedTogether - pair of employees and their time
     * @return returns the pair that have worked for the longest period.
     */
    @Override
    public Map.Entry<Pair, Long> findLongestTimeWorkedPair(Map<Pair, Long> timeWorkedTogether) {
        Map.Entry<Pair, Long> longestTime = null;

        for (Map.Entry<Pair, Long> entry : timeWorkedTogether.entrySet()) {
            if (longestTime == null || entry.getValue()
                    .compareTo(longestTime.getValue()) > 0) {
                longestTime = entry;
            }
        }

        return longestTime;
    }
}
