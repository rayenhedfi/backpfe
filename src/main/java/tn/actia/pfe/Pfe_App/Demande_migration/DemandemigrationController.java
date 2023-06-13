package tn.actia.pfe.Pfe_App.Demande_migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandemigration")
public class DemandemigrationController {
    @Autowired
    DemandemigrationService demandemigrationService;
    @PostMapping("/add_mig")
    public Demandemigration savedemandemigratoin(@RequestBody Demandemigration demandemigration){
        return demandemigrationService.savedemandemigration(demandemigration);
    }

    @GetMapping("/{id}")
    public Demandemigration getdemandemigrationById(@PathVariable Long id){
        return demandemigrationService.getdemandemigrationById(id);

    }
    @GetMapping
    public List<Demandemigration> getAllDemandemigration(){
        return demandemigrationService.getAllDemandemigration();
    }
    @DeleteMapping("/{id}")
    public void deletedemandemigrationById(@PathVariable Long id){
        demandemigrationService. deletedemandemigrationById(id);
    }

    @PutMapping("/{id}/status")
    public Demandemigration updateStatus(@PathVariable Long id, @RequestParam("status")String newStatus)
    {
        return demandemigrationService.updatestatus(id,newStatus);
    }
    /*@GetMapping("{id}/check")
    public Demandemigration checkAndUpdateStatus(@PathVariable Long id)
    {
        return demandemigrationService.checkAndUpdateStatus(id);
    }*/
}
