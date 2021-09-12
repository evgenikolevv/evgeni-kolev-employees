package com.evgeni.models;

public class Project {

    private Integer id;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id;
    }

    @Override
    public boolean equals(Object o) {
        return ((Project) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
