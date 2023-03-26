package com.jwtproject.service;

import com.amazonaws.services.s3.model.S3Object;
import com.jwtproject.model.Design;
import com.jwtproject.model.MetaFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetaService {
    public void upload(MultipartFile file) throws IOException;
    public void uploadDesign(MultipartFile file) throws IOException;
    public S3Object download(String id);
    public List<MetaFile> list();
    public List<Design> listDesign();
}