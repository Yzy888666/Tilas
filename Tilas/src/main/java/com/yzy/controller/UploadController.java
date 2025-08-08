package com.yzy.controller;

import com.aliyuncs.exceptions.ClientException;
import com.yzy.pojo.Result;
import com.yzy.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile  file) throws IOException {
//        log.info("上传文件");
//        log.info("{},{},{}",name,age,file.getOriginalFilename());
//
//        //uuid+文件后缀
//        String newfilename = UUID.randomUUID().toString();
//        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
//        newfilename = newfilename + suffix;
//
//
//        String path = "D:/upload/" + newfilename;
//        file.transferTo(new File(path));
//        //保存文件到磁盘里面
//
//        return Result.success();
//    }

    @Autowired
    AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException, ClientException {
        log.info("文件上传");
        //将file转为byte[]
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        String fileURL = null;
        if (fileName != null) {
            fileURL = aliyunOSSOperator.upload(bytes, fileName);
        }
        return Result.success(fileURL);


    }
}
