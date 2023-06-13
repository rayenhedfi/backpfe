package tn.actia.pfe.Pfe_App.SvnRepo;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

@Service
public class FolderInfoServiceImpl implements FolderInfoService {
    private final FolderInfoRepository folderInfoRepository;

    public FolderInfoServiceImpl(FolderInfoRepository folderInfoRepository) {
        this.folderInfoRepository = folderInfoRepository;
    }

    @Override
    public FolderInfoEntity saveFolderInfo(FolderInfoEntity folderInfo) {
        return folderInfoRepository.save(folderInfo);
    }

    @Override
    public FolderInfoEntity getFolderInfoById(Long id) {
        return folderInfoRepository.findById(id).orElse(null);
    }

    @Override
    public List<FolderInfoEntity> getAllFolderInfo() {
        return folderInfoRepository.findAll();
    }

    @Override
    public void deleteFolderInfoById(Long id) {
        folderInfoRepository.deleteById(id);
    }

    /* public String generateCheckoutScript(FolderInfoEntity folder) {
         String scriptContent = "@echo off\n";
         scriptContent += "svn checkout " + folder.getUrl() + " " + folder.getWorkingCopyPath() + "\n";
         return scriptContent;
     }*/
   /*private String generateCheckoutScript(Long id) {
       FolderInfoEntity folder = folderInfoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Folder not found"));

       // Récupérez l'URL du dépôt SVN et le workingCopyPath à partir de l'entité FolderInfoEntity
       String url = folder.getUrl();
       String workingCopyPath = folder.getWorkingCopyPath();

       // Générez le contenu du script en utilisant l'URL du dépôt SVN et le workingCopyPath
       StringBuilder scriptContent = new StringBuilder();
       scriptContent.append("@echo off\n");
       scriptContent.append("svn checkout ").append(url).append(" ").append(workingCopyPath).append("\n");
       return scriptContent.toString();
   }*/
  /* public String generateCheckoutScript(Long id) {
       FolderInfoEntity folder = folderInfoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Folder not found"));

       // Récupérez l'URL du dépôt SVN et le workingCopyPath à partir de l'entité FolderInfoEntity
       String url = folder.getUrl();
       String workingCopyPath = folder.getWorkingCopyPath();

       // Générez le contenu du script en utilisant l'URL du dépôt SVN et le workingCopyPath
       StringBuilder scriptContent = new StringBuilder();
       scriptContent.append("#!/bin/bash\n");
       scriptContent.append("svn checkout ").append(url).append(" ").append(workingCopyPath).append("\n");
       return scriptContent.toString();
   }
    @Override
    public void createStandardStructure(Long id) {
        FolderInfoEntity folder = folderInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        String url = folder.getUrl();
        String username = "your_svn_username";
        String password = "your_svn_password";

        // Create the branches, tags, and trunk directories using WebDAV
        createDirectory(url + "/branches", username, password);
        createDirectory(url + "/tags", username, password);
        createDirectory(url + "/trunk", username, password);
    }

    private void createDirectory(String url, String username, String password) {
        try {
            HttpClient client = new HttpClient();
            client.getState().setCredentials(
                    new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
                    new UsernamePasswordCredentials(username, password)
            );

            MkColMethod mkColMethod = new MkColMethod(url);
            int statusCode = client.executeMethod(mkColMethod);
            mkColMethod.releaseConnection();

            if (statusCode != HttpStatus.SC_CREATED) {
                throw new RuntimeException("Failed to create directory: " + url);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create directory: " + url, e);
        }
    }*/
    @Override
    public void performSVNCheckout(Long id) {
        FolderInfoEntity folder = folderInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        String url = folder.getUrl();
        String workingCopyPath = folder.getWorkingCopyPath();

        SVNURL svnUrl;
        try {
            svnUrl = SVNURL.parseURIEncoded(url);
        } catch (SVNException e) {
            throw new RuntimeException("Failed to parse SVN URL: " + url, e);
        }

        SVNClientManager clientManager = SVNClientManager.newInstance();
        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false); // Optional, if you want to include externals

        try {
            SVNRevision revision = SVNRevision.HEAD;
            updateClient.doCheckout(svnUrl, new File(workingCopyPath), revision, revision, SVNDepth.INFINITY, false);
        } catch (SVNException e) {
            throw new RuntimeException("SVN checkout failed", e);
        } finally {
            clientManager.dispose();
        }
    }

    /*@Override
    public void addStandardStructure(Long id) {
        FolderInfoEntity folder = folderInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // Perform the SVN checkout
        performSVNCheckout(id);

        // Add the standard structure to the checked-out repository
        String workingCopyPath = folder.getWorkingCopyPath();

        // Create 'branches', 'tags', and 'trunk' directories within the working copy
        String branchesDir = workingCopyPath + "/branches";
        String tagsDir = workingCopyPath + "/tags";
        String trunkDir = workingCopyPath + "/trunk";

        // Create the directories using Java's File class
        File branches = new File(branchesDir);
        File tags = new File(tagsDir);
        File trunk = new File(trunkDir);

        if (!branches.exists() && branches.mkdirs()) {
            System.out.println("Directory 'branches' created successfully.");
        }

        if (!tags.exists() && tags.mkdirs()) {
            System.out.println("Directory 'tags' created successfully.");
        }

        if (!trunk.exists() && trunk.mkdirs()) {
            System.out.println("Directory 'trunk' created successfully.");
        }
    }*/

    @Override
    public void addStandardStructure(Long id) {
        try {
            FolderInfoEntity folder = folderInfoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Folder not found"));

            String workingCopyPath = folder.getWorkingCopyPath();
            String[] folderNames = {"branches", "tags", "trunk"};

            for (String folderName : folderNames) {
                String subFolderPath = workingCopyPath + File.separator + folderName;
                File subFolder = new File(subFolderPath);

                if (!subFolder.exists() && subFolder.mkdirs()) {
                    System.out.println("Subfolder '" + folderName + "' created successfully.");
                } else {
                    System.out.println("Subfolder '" + folderName + "' already exists.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // You can choose to throw an exception or handle the error accordingly
        }
    }



}
