package com.jwtproject.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Value("${aws.credentials.profile-name}")
    private String profileName;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3() {
    	ProfileCredentialsProvider p=new ProfileCredentialsProvider(profileName);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(p)
                .build();
    }
}