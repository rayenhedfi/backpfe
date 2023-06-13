package tn.actia.pfe.Pfe_App.SvnRepo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.actia.pfe.Pfe_App.SvnRepo.FolderInfoEntity;
import tn.actia.pfe.Pfe_App.SvnRepo.FolderInfoService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderInfoController {
    private final FolderInfoService folderInfoService;


    public FolderInfoController(FolderInfoService folderInfoService) {
        this.folderInfoService = folderInfoService;
    }

    @PostMapping
    public FolderInfoEntity saveFolderInfo(@RequestBody FolderInfoEntity folderInfo) {
        return folderInfoService.saveFolderInfo(folderInfo);
    }

    @GetMapping("/{id}")
    public FolderInfoEntity getFolderInfoById(@PathVariable Long id) {
        return folderInfoService.getFolderInfoById(id);
    }

    @GetMapping
    public List<FolderInfoEntity> getAllFolderInfo() {
        return folderInfoService.getAllFolderInfo();
    }

    @DeleteMapping("/{id}")
    public void deleteFolderInfoById(@PathVariable Long id) {
        folderInfoService.deleteFolderInfoById(id);
    }
    /*@GetMapping("/{id}/checkout-script")
    public ResponseEntity<Resource> generateCheckoutScript(@PathVariable Long id) {
        String scriptContent = folderInfoService.generateCheckoutScript(id);

        // Créer une ressource ByteArrayResource à partir du contenu du script
        ByteArrayResource resource = new ByteArrayResource(scriptContent.getBytes());

        // Définir les en-têtes de la réponse pour le téléchargement du fichier
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "script.sh");

        // Retourner la réponse avec la ressource en tant que fichier téléchargeable
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }*/
   /* @PostMapping("/{id}/create-standard-structure")
    public void createStandardStructure(@PathVariable Long id) {
        folderInfoService.createStandardStructure(id);
    }*/
    @PostMapping("/{id}/checkout")
    public ResponseEntity<String> performSVNCheckout(@PathVariable Long id) {
        try {
            folderInfoService.performSVNCheckout(id);
            return ResponseEntity.ok("SVN checkout successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SVN checkout failed: " + e.getMessage());
        }
    }
    @PostMapping("/{id}/add-structure")
    public ResponseEntity<String> addStandardStructure(@PathVariable Long id) {
        try {
            folderInfoService.addStandardStructure(id);
            return ResponseEntity.ok("Standard structure added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add standard structure: " + e.getMessage());
        }
    }
}
