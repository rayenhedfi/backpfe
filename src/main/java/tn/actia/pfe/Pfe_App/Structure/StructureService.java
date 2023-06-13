package tn.actia.pfe.Pfe_App.Structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import java.io.IOException;

@Service
public class StructureService {
    public String generateJson(FolderData folder) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(folder);
    }

    public Folder parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Folder.class);
    }
}
