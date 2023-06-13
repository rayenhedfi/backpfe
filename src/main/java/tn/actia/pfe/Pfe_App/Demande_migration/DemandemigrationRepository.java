package tn.actia.pfe.Pfe_App.Demande_migration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandemigrationRepository extends JpaRepository<Demandemigration,Long> {
}
