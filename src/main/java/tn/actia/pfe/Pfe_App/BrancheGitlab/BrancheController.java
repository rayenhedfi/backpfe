package tn.actia.pfe.Pfe_App.BrancheGitlab;

import org.gitlab4j.api.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/branche")
public class BrancheController {
@Autowired
BrancheService brancheService;
    @PostMapping("/add-branche")
    @ResponseBody
    public Branche saveBranche(@RequestBody Branche branche){
        return brancheService.saveBranche(branche);
    }



    @GetMapping("/retrieve-all-branche")
    @ResponseBody
    public List<Branche> getAllBranche(){
        return  brancheService.retrieveAllBranche();
    }
}
