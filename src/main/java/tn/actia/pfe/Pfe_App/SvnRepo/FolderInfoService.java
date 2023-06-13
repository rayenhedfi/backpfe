package tn.actia.pfe.Pfe_App.SvnRepo;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.File;
import java.util.List;

public interface FolderInfoService {
    FolderInfoEntity saveFolderInfo(FolderInfoEntity folderInfo);
    FolderInfoEntity getFolderInfoById(Long id);
    List<FolderInfoEntity> getAllFolderInfo();
    void deleteFolderInfoById(Long id);
   // String generateCheckoutScript(Long id);
   // void createStandardStructure(Long id);
  // void createStandardStructure(Long id);
    void performSVNCheckout(Long id);
    void addStandardStructure(Long id);

}
