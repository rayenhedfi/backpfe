package tn.actia.pfe.Pfe_App.Structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tmatesoft.svn.core.internal.wc17.db.Structure;

import javax.mail.Folder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/structure")
public class StructureController {
    @Value("${json.output.path}")
    private String jsonOutputPath;

    private final StructureService structureService;

    @Autowired
    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }

   /* @PostMapping("/save")
    public ResponseEntity<String> saveStructure(@RequestBody Folder folder) {
        try {
            String json = structureService.generateJson(folder);
            String filePath = jsonOutputPath + "/structure.json";
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
            return ResponseEntity.ok("Structure saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save structure");
        }
    }*/

   @PostMapping("/save")
   @ResponseBody
   public ResponseEntity<String> saveStructure(@RequestBody StructureData structureData) {
       try {
           FolderData folder = new FolderData();
           folder.setName(structureData.getName());
           folder.setSubfolders(structureData.getSubfolders());
           String json = structureService.generateJson(folder);
           String filePath = jsonOutputPath + "/structure.json";
           File file = new File(filePath);
           FileOutputStream fos = new FileOutputStream(file);
           fos.write(json.getBytes());
           fos.close();

           return ResponseEntity.ok("Structure saved successfully");
       } catch (IOException e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save structure");
       }
   }



    @PostMapping("/upload")
    public ResponseEntity<String> uploadStructure(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        try {
            byte[] bytes = file.getBytes();
            String json = new String(bytes);
            Folder folder = structureService.parseJson(json);
            // Do something with the folder structure
            return ResponseEntity.ok("Structure uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload structure");
        }
    }
}
