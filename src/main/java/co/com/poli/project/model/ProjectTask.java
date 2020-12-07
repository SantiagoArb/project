package co.com.poli.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectTask {
    private Long id;
    private String name;
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private int priority;
    private double hours;
    private Date startDate;
    private Date endDate;
    private String projectIdentifier;
    private Backlog backlog;
}
