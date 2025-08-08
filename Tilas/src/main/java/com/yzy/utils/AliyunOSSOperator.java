package com.yzy.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {

    // Endpoint以北京为例
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 填写Bucket名称
    String bucketName = "tilas-yzy";
    // 填写Bucket所在地域
    String region = "cn-beijing";

    public String upload(byte[] content, String originalFileName) throws ClientException {
        // 从环境变量中获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } catch (OSSException | com.aliyun.oss.ClientException e) {
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
        //https://tilas-yzy.oss-cn-beijing.aliyuncs.com/001.jpg
        return "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + objectName;
    }
}
