package tn.actia.pfe.Pfe_App.BrancheGitlab;

import java.util.List;

public interface BrancheService {
    Branche saveBranche(Branche branche);
    void deleteBranche(Long id);
    Branche retrieveBranche(Long id);
    List<Branche>retrieveAllBranche();

}
