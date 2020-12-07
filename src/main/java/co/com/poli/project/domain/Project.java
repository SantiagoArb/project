package co.com.poli.project.domain;

import co.com.poli.project.model.Backlog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;
    @NotEmpty(message = "El identificador no debe ser vacio")
    @Size(min = 5, max = 7)
    private String identifier;
    @NotEmpty(message = "La descripcion no debe ser vacio")
    private String description;
    private Date startDate;
    private Date endDate;
    @Column(name = "backlog_id")
    private Long backlogId;
    @Transient
    private Backlog backlog;


}
