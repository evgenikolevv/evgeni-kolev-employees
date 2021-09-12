package com.evgeni.repositories;

import com.evgeni.models.Employee;
import com.evgeni.models.Project;

import java.util.ArrayList;
import java.util.Map;

public interface ProjectRepository {

    Map<Project, ArrayList<Employee>> findAll();
}
