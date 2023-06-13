package tn.actia.pfe.Pfe_App.BrancheGitlab;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.actia.pfe.Pfe_App.Gitlab.Project;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Branche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long brancheid;
    private String brancheNom;
    private String brancheRef;
    @ManyToOne
    private Project project;

}
