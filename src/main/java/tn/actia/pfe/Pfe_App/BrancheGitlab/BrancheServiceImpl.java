package tn.actia.pfe.Pfe_App.BrancheGitlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrancheServiceImpl implements BrancheService {
    @Autowired
    BrancheRepository brancheRepository;
    @Override
    public Branche saveBranche(Branche branche) {
        brancheRepository.save(branche);
        return null;
    }

    @Override
    public void deleteBranche(Long id) {

    }

    @Override
    public Branche retrieveBranche(Long id) {
        return null;
    }

    @Override
    public List<Branche> retrieveAllBranche() {
       return  brancheRepository.findAll();
    }
}
