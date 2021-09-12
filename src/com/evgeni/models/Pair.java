package com.evgeni.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pair {

    private final Employee first;
    private final Employee second;
    private Map<Project, Date[]> projects = new HashMap<>();

    public Pair(Employee first, Employee second) {
        this.first = first;
        this.second = second;
    }

    public Employee getFirst() {
        return first;
    }

    public Employee getSecond() {
        return second;
    }

    public Map<Project, Date[]> getProjects() {
        return projects;
    }

    public void addProject(Project project, Date[] period) {
        this.projects.put(project, period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair pair = (Pair) o;

        return first.getId().equals(pair.first.getId())
                && second.getId().equals(pair.second.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
