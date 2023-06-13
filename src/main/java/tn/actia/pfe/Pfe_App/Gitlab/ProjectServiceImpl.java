package tn.actia.pfe.Pfe_App.Gitlab;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.actia.pfe.Pfe_App.BrancheGitlab.Branche;
import tn.actia.pfe.Pfe_App.BrancheGitlab.BrancheRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService  {
    private final ProjectRepository projectRepository;
    @Autowired
    BrancheRepository brancheRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;

    }
    //GitLabApi gitLabApi = new GitLabApi("https://your-gitlab-url.com", project.getToken());
    /*@Override
    public Project saveProject(Project project) {
        // Create a GitLabApi instance using your GitLab server URL and personal access token
        GitLabApi gitLabApi = new GitLabApi("https://gitlab.com", project.getToken());

        // Create a GitLab4J Project instance and set its properties
        org.gitlab4j.api.models.Project projectSpec = new org.gitlab4j.api.models.Project();
        projectSpec.setName(project.getName());
        projectSpec.setDescription(project.getDescription());
        projectSpec.setVisibility(Visibility.valueOf(project.getVisibility().toString()));
        projectSpec.setIssuesEnabled(true);
        projectSpec.setMergeRequestsEnabled(true);
        projectSpec.setWikiEnabled(true);

        // Create the project in GitLab using the GitLab4J API
        try {
            org.gitlab4j.api.models.Project createdProject = gitLabApi.getProjectApi().createProject(projectSpec);

            // Set the properties of the saved project entity
            project.setId(createdProject.getId());

            // Save the project entity
            return projectRepository.save(project);
        } catch (GitLabApiException e) {
            // Handle any exceptions or errors
            e.printStackTrace();
            return null;
        }
    }
*/
    @Override
    public Project saveProject(Project project) {
        // Create a GitLabApi instance using your GitLab server URL and personal access token
        GitLabApi gitLabApi = new GitLabApi("https://gitlab.com", project.getToken());

        // Create a GitLab4J Project instance and set its properties
        org.gitlab4j.api.models.Project projectSpec = new org.gitlab4j.api.models.Project();
        projectSpec.setName(project.getName());
        projectSpec.setDescription(project.getDescription());
        projectSpec.setVisibility(Visibility.valueOf(project.getVisibility().toString()));
        projectSpec.setIssuesEnabled(true);
        projectSpec.setMergeRequestsEnabled(true);
        projectSpec.setWikiEnabled(true);

        // Create the project in GitLab using the GitLab4J API
        try {
            org.gitlab4j.api.models.Project createdProject = gitLabApi.getProjectApi().createProject(projectSpec);

            // Set the properties of the saved project entity
            project.setId(createdProject.getId());
            project.setGitlabProjectId(createdProject.getId()); // Set the GitLab project ID

            // Save the project entity
            return projectRepository.save(project);
        } catch (GitLabApiException e) {
            // Handle any exceptions or errors
            e.printStackTrace();
            return null;
        }
    }

   @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProjectInfoById(Long id) {
         projectRepository.deleteById(id);

    }
    /*@Override
    public List<Project> getAllProject() {
        try {
            List<Project> projects = new ArrayList<>();

            // Retrieve all projects from the database
            List<Project> dbProjects = projectRepository.findAll();

            // Create a GitLabApi instance using your GitLab server URL and personal access token
            GitLabApi gitLabApi = new GitLabApi("https://gitlab.com", dbProjects.get(0).getToken());

            // Get the list of projects from GitLab using the GitLab4J API
            List<org.gitlab4j.api.models.Project> gitLabProjects = gitLabApi.getProjectApi().getProjects();

            // Convert the GitLab projects to your entity projects
            for (org.gitlab4j.api.models.Project gitLabProject : gitLabProjects) {
                Project project = new Project();
                project.setId(gitLabProject.getId());
                project.setName(gitLabProject.getName());
                project.setDescription(gitLabProject.getDescription());
                // Set other project properties if needed
                projects.add(project);
            }

            // Return the combined list of projects
            projects.addAll(dbProjects);
            return projects;
        } catch (GitLabApiException e) {
            // Handle any exceptions or errors
            e.printStackTrace();
            return Collections.emptyList();
        }
    }*/

  /*  @Transactional
    public void assignBrancheToProject(Long id, Branche branche) {
        Project project =projectRepository.findById(id).orElse(null);
        branche.setProject(project);
        brancheRepository.save(branche);
    }*/
  @Override
  public void assignBrancheToProject(Long id, Branche branche) {
      Project project = projectRepository.findById(id).orElse(null);
      branche.setProject(project);

      // Récupérer le gitlabProjectId du projet
      Long gitlabProjectId = project.getGitlabProjectId();

      // Utiliser le gitlabProjectId pour créer la branche dans le projet GitLab
      if (gitlabProjectId != null) {
          // Utiliser GitLabApi pour créer la branche dans le projet GitLab
          GitLabApi gitLabApi = new GitLabApi("https://gitlab.com", project.getToken());
          try {
              gitLabApi.getRepositoryApi().createBranch(gitlabProjectId, branche.getBrancheNom(), branche.getBrancheRef());
          } catch (GitLabApiException e) {
              e.printStackTrace();
              // Gérer les exceptions ou erreurs éventuelles
          }
      }

      // Sauvegarder la branche dans la base de données
      brancheRepository.save(branche);
  }
  /*  @Override
    public Project getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setToken(null); // Ignorer l'attribut token lors de la réponse
        }
        return project;
    }

    @Override
    public List<Project> getAllProject() {
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            project.setToken(null); // Ignorer l'attribut token lors de la réponse
        }
        return projects;
    }*/

}
