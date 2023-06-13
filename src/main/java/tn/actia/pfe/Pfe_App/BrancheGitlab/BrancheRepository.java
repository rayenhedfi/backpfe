package tn.actia.pfe.Pfe_App.BrancheGitlab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrancheRepository extends JpaRepository<Branche,Long> {
}
