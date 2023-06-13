package tn.actia.pfe.Pfe_App.SvnRepo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class FolderInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workingCopyPath;
    private String folderName;
    private String url;
    private LocalDateTime creationDate;

    public FolderInfoEntity(String workingCopyPath, String folderName, String url, LocalDateTime creationDate) {
        this.workingCopyPath = workingCopyPath;
        this.folderName = folderName;
        this.url = url;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "FolderInfoEntity{" +
                "id=" + id +
                ", workingCopyPath='" + workingCopyPath + '\'' +
                ", folderName='" + folderName + '\'' +
                ", url='" + url + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }



}

