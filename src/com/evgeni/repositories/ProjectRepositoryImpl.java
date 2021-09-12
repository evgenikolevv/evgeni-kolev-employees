package com.evgeni.repositories;

import com.evgeni.models.Employee;
import com.evgeni.models.Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final String fileName;

    public ProjectRepositoryImpl(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.isEmpty()) {
            throw new FileNotFoundException("Filename must not be null or empty.");
        }

        this.fileName = fileName;
    }

    @Override
    public Map<Project, ArrayList<Employee>> findAll() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Map<Project, ArrayList<Employee>> projects = new HashMap<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitData = line.split(", ");
                Project project = new Project(Integer.parseInt(splitData[1]));
                Employee employee = buildEmployeeData(splitData);
                ArrayList<Employee> employees;

                if (projects.containsKey(project)) {
                    employees = projects.get(project);
                } else {
                    employees = new ArrayList<>();
                }

                employees.add(employee);
                projects.put(project, employees);
            }

            return projects;

        } catch (IOException | ParseException e) {
            throw new IllegalArgumentException("Error occurred while reading file.");
        }
    }

    /**
     * Creates employee object from the array splitData
     *
     * @param splitData array with data from text file.
     * @return object Employee
     */
    private Employee buildEmployeeData(String[] splitData) throws ParseException {
        Integer employeeId = Integer.parseInt(splitData[0]);
        Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(splitData[2]);

        Date dateTo = (splitData[3].equals("NULL") || splitData[3].equals("null"))
                ? new Date()
                : new SimpleDateFormat("yyyy-MM-dd").parse(splitData[3]);

        return new Employee(employeeId, dateFrom, dateTo);
    }
}
