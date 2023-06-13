package tn.actia.pfe.Pfe_App.Gitlab;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.actia.pfe.Pfe_App.BrancheGitlab.Branche;

import java.util.List;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/Project")
public class ProjectController {
    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

   @PostMapping("/add-project")
   @ResponseBody
    public Project saveProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/retrieve-project-byid/{id}")
    @ResponseBody
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/retrieve-all-projects")
    @ResponseBody
    public List<Project> getAllProject() {
        return projectService.getAllProject();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteProjectInfoById(@PathVariable Long id) {
        projectService.deleteProjectInfoById(id);
    }

    @PutMapping("/assignbrancheToProject/{project-id}")
    @ResponseBody
    public void assignBrancheToProject(@PathVariable("project-id") Long projectId,@RequestBody Branche branche)
    {
        projectService.assignBrancheToProject(projectId, branche);
    }
}
