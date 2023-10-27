package com.carrot.easy.file;

import com.carrot.easy.controller.UploadFile;
import com.carrot.easy.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileStore {


    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFiles.isEmpty()){
                uploadFiles.add(storeFile(multipartFile));
            }
        }

        return uploadFiles;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));
        return new UploadFile(originalFilename, storeFilename);
    }

    private String createStoreFilename(String originalFilename) {

        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid+"."+ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }
}