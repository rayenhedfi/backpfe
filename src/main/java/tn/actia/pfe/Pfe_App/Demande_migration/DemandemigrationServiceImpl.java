package tn.actia.pfe.Pfe_App.Demande_migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DemandemigrationServiceImpl implements DemandemigrationService {
    @Autowired
    DemandemigrationRepository demandemigrationRepository;

    @Override
    public Demandemigration savedemandemigration(Demandemigration demandemigration) {
        return demandemigrationRepository.save(demandemigration);
    }

    @Override
    public Demandemigration getdemandemigrationById(Long id) {
        return demandemigrationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Demandemigration> getAllDemandemigration() {
        return demandemigrationRepository.findAll();
    }

    @Override
    public void deletedemandemigrationById(Long id) {
        demandemigrationRepository.deleteById(id);
    }


    @Override
    public Demandemigration updatestatus(Long id, String newStatus) {
        Demandemigration demandemigration = demandemigrationRepository.findById(id).orElse(null);
        if (demandemigration != null) {
            demandemigration.setStatus(newStatus);
            return demandemigrationRepository.save(demandemigration);
        }
        return null;
    }
    /*   @Override
        public Demandemigration checkAndUpdateStatus(Long id) {
            Demandemigration demandemigration = demandemigrationRepository.findById(id).orElse(null);
            if (demandemigration != null && "approuvee".equals(demandemigration.getStatus())) {
                try {
                    // Chemin d'accès complet au script shell sur votre machine locale
                    String scriptPath = "/home/rayenhedfi/Bureau/testt/scriptk.sh";
                    // valeur à passer au script
                    // String chemin = "/home/rayenhedfi/Bureau/testtt";
                    // String nomProjet= demandemigration.getNom_projet();
                    String svn_repo_url = demandemigration.getUrlSvn();
                    String gitlab_url = demandemigration.getUrlGit();
                    String token = demandemigration.getToken();
                    String svn_username = demandemigration.getSvnUsername();
                    String svn_password = demandemigration.getSvnPassword();
                    // Exécuter le script shell
                    ProcessBuilder processBuilder= new ProcessBuilder(scriptPath,svn_repo_url,gitlab_url,token,svn_username,svn_password);
                    Process process= processBuilder.start();
                    //Process process = Runtime.getRuntime().exec(scriptPath;
    
                    // Attendre la fin de l'exécution du script
                    int exitCode = process.waitFor();
    
                    if (exitCode == 0) {
                        // Mise à jour du statut de la demande après l'exécution réussie du scrip
                        demandemigrationRepository.save(demandemigration);
                    } else {
                        // En cas d'échec de l'exécution du script, vous pouvez effectuer d'autres actions appropriées
                    }
                } catch (IOException | InterruptedException e) {
                    // Gérer les exceptions liées à l'exécution du script
                }
            }
            return demandemigration;
        }*/

    


}
