package tn.actia.pfe.Pfe_App.Gitlab;

import tn.actia.pfe.Pfe_App.BrancheGitlab.Branche;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    Project getProjectById(Long id);
    List<Project> getAllProject();
    void deleteProjectInfoById(Long id);
    void assignBrancheToProject(Long id, Branche branche);

}
