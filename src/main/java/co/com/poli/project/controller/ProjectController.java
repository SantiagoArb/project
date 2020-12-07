package co.com.poli.project.controller;

import co.com.poli.project.domain.Project;
import co.com.poli.project.model.ErrorMessage;
import co.com.poli.project.services.ProjectServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectServices projectServices;

    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }


    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project, BindingResult result){

        if ( result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));

        }
        Project projectBD = this.projectServices.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectBD);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        System.out.println(id);
        Project projectBD  = projectServices.getProject(id);
        if (null == projectBD) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(projectBD);
    }

    @GetMapping
    public ResponseEntity<List<Project>> listTaskById() {
        List<Project> projects = projectServices.getProjectAll();
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
