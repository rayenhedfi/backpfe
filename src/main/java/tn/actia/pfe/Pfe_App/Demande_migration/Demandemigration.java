package tn.actia.pfe.Pfe_App.Demande_migration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Demandemigration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_projet;
    private String plateforme_migration;
    private String type_migration;
    private String status;
    private String UrlSvn;
    private String UrlGit;
    private String Token;
    private String SvnUsername;
    private String SvnPassword;


}


