package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Pair;
import com.evgeni.models.Project;

import java.util.ArrayList;
import java.util.Map;

public interface EmployeeService {

    Map.Entry<Pair, Long> findLongestTimeWorkedPair(Map<Project, ArrayList<Employee>> projects);
}
