package com.evgeni.services;

import com.evgeni.models.Employee;
import com.evgeni.models.Project;
import com.evgeni.repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.Map;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Map<Project, ArrayList<Employee>> findAll() {
        return projectRepository.findAll();
    }
}
