package co.com.poli.project.services;

import co.com.poli.project.domain.Project;
import co.com.poli.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServicesImpl implements ProjectServices{

    private final ProjectRepository projectRepository;

    public ProjectServicesImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProjectAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        Project projectBD = getProjectByIdentifier(project.getIdentifier());
        if(projectBD!=null){
            return projectBD;
        }
        return this.projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Project projectBD = getProjectByIdentifier(project.getIdentifier());
        if(projectBD == null) {
            return null;
        }
        return this.projectRepository.save(project);
    }

    @Override
    public Project deleteProject(Project project) {
        return null;
    }

    @Override
    public Project getProject(Long id) {
        return this.projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project getProjectByIdentifier(String identifier) {
        return this.projectRepository.findByIdentifier(identifier);
    }
}
