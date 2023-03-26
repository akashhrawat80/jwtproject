package com.jwtproject.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.jwtproject.model.Design;
import com.jwtproject.model.MetaFile;
import com.jwtproject.repository.DesignRepository;
import com.jwtproject.repository.JwtRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class MetaServiceImpl implements MetaService {

    @Autowired
    private JwtAmazonService amazonService;

    @Autowired
    private JwtRepository jwtRepository;
    
    @Autowired
    private DesignRepository designRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Override
    public void upload(MultipartFile file) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonService.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        // Saving metadata to db
        jwtRepository.save(new MetaFile(fileName, path, putObjectResult.getMetadata().getVersionId()));

    }
    
    @Override
    public void uploadDesign(MultipartFile file) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> data = new HashMap<>();
        data.put("Content-Type", file.getContentType());
        data.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String title = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonService.upload(
                path, title, Optional.of(data), file.getInputStream());

        // Saving metadata to db
        designRepository.save(new Design(title));

    }
    
    @Override
    public S3Object download(String id) {
        MetaFile metaFile = jwtRepository.findById(id).get();
        return amazonService.download(metaFile.getFilePath(),metaFile.getFileName());
    }

    @Override
    public List<MetaFile> list() {
        List<MetaFile> metas = new ArrayList<>();
        jwtRepository.findAll().forEach(metas::add);
        return metas;
    }
    
    @Override
    public List<Design> listDesign() {
        List<Design> designs = new ArrayList<>();
        designRepository.findAll().forEach(designs::add);
        return designs;
    }
}
