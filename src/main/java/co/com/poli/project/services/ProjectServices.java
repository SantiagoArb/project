package co.com.poli.project.services;

import co.com.poli.project.domain.Project;

import java.util.List;

public interface ProjectServices {
    List<Project> getProjectAll();

    Project createProject(Project project);

    Project updateProject(Project project);

    Project deleteProject(Project project);

    Project getProject(Long id);

    Project getProjectByIdentifier(String identifier);
}
