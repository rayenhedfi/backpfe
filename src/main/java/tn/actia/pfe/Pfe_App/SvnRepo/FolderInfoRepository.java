package tn.actia.pfe.Pfe_App.SvnRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderInfoRepository  extends JpaRepository<FolderInfoEntity, Long> {

}
