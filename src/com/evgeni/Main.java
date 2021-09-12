package com.evgeni;

import com.evgeni.repositories.ProjectRepository;
import com.evgeni.repositories.ProjectRepositoryImpl;
import com.evgeni.services.*;
import com.evgeni.utils.PairPresenter;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ProjectRepository projectRepository = new ProjectRepositoryImpl("employees.txt");
        ProjectService projectService = new ProjectServiceImpl(projectRepository);
        PairService employeesPairService = new PairServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeesPairService);
        PairPresenter presenter = new PairPresenter();
        presenter.print(employeeService.findLongestTimeWorkedPair(projectService.findAll()));
    }
}
