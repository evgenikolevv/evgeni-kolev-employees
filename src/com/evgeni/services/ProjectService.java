package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Project;

import java.util.ArrayList;
import java.util.Map;

public interface ProjectService {

    Map<Project, ArrayList<Employee>> findAll();
}
