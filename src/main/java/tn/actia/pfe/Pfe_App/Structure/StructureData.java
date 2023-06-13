package tn.actia.pfe.Pfe_App.Structure;

import java.util.List;

public class StructureData {
    private String name;
    private List<FolderData> subfolders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FolderData> getSubfolders() {
        return subfolders;
    }

    public void setSubfolders(List<FolderData> subfolders) {
        this.subfolders = subfolders;
    }
}



