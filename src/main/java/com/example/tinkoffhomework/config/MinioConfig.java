package com.example.tinkoffhomework.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties properties) throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .endpoint(properties.getUrl(), properties.getPort(), properties.isSecure())
                .build();

        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucket()).build());
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucket()).build());
            System.out.println("Bucket created successfully.");
        } else {
            System.out.println("Bucket already exists.");
        }
        return minioClient;
    }
}
