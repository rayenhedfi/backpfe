package tn.actia.pfe.Pfe_App.Folder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Folder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class FolderController {

    @PostMapping("/api/save-structure")
    public void saveFolderStructure(@RequestBody Folder rootFolder) {
        // Générer le fichier JSON avec la structure sur la machine locale
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(rootFolder);
            Files.write(Paths.get("/chemin/vers/fichier.json"), json.getBytes());
            System.out.println("Fichier JSON généré avec succès");
        } catch (IOException e) {
            System.out.println("Erreur lors de la génération du fichier JSON : " + e.getMessage());
        }
    }
}

