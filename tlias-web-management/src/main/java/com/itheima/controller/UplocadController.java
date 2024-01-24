package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UplocadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //文件上传
//    @PostMapping("/upload")
//    public Result uplocad(String username, Integer age, MultipartFile image) throws IOException {
//        log.info(" 文件上传:",username, age, image);
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构造唯一的文件名（不能重复）--uuid（通用唯一识别码）
//        int index = originalFilename.lastIndexOf(".");
//        String extName = originalFilename.substring(index);//
//        String newFileName = UUID.randomUUID().toString() + extName;
//        log.info(" 新的文件名:",newFileName);
//
//        //将文件存储到服务器的磁盘目录中
//        image.transferTo(new File("D:\\images\\ )"+newFileName));

//        return Result.success();

        //调用阿里云
        @PostMapping("/upload")
        public Result uplocad(MultipartFile image) throws IOException {
            log.info("文件上传 " + image.getOriginalFilename());
            String url=aliOSSUtils.upload(image);
           log.info("文件上传成功 "+ url);
            return Result.success(url);
}
}
