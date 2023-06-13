package tn.actia.pfe.Pfe_App.Demande_migration;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemandemigrationService {
    Demandemigration savedemandemigration(Demandemigration demandemigration);
    Demandemigration getdemandemigrationById(Long id);
    List<Demandemigration> getAllDemandemigration();
    Demandemigration updatestatus(Long id, String newStatus);
   // Demandemigration checkAndUpdateStatus(Long id);
    void deletedemandemigrationById(Long id);
}
