package com.yzy;

import java.io.*;
import java.nio.file.Files;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;

public class OssUploadTest {
    public static void main(String[] args) throws com.aliyuncs.exceptions.ClientException {
        // Endpoint以北京为例
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 填写Bucket名称
        String bucketName = "tilas-yzy";
        // 填写Bucket所在地域
        String region = "cn-beijing";



        // 从环境变量中获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        //输出访问凭证来进行调试
        System.out.println("AccessKeyId: " + credentialsProvider.getCredentials().getAccessKeyId());
        System.out.println("AccessKeySecret: " + credentialsProvider.getCredentials().getSecretAccessKey());

        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        try {
            // 上传文件
            String filePath = "D:\\upload\\特斯拉Model_3_2023款_长续航焕新版_双电机全轮驱动_Model_3_2023.38.8_2025-07-16_22-53-22_exported_20250805_173441.det";
            File  file = new File(filePath);
            //将file转化为二进制流
            byte[] bytes = Files.readAllBytes(file.toPath());


            // 通过字符串内容上传
            ossClient.putObject(bucketName, filePath, new ByteArrayInputStream(bytes));
            System.out.println("文件 " + filePath + " 上传成功。");

            // 如果要上传本地文件，可以使用下面的方式：
            // String localFile = "D:/upload/test.txt";
            // ossClient.putObject(new PutObjectRequest(bucketName, objectName, new File(localFile)));

        } catch (OSSException oe) {
            System.out.println("OSS异常: " + oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("客户端异常: " + ce.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
