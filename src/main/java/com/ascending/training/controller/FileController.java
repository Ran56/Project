package com.ascending.training.controller;

import com.ascending.training.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
                                                        //输入进来的文件的类型MULTIPART_FORM_DATA_VALUE
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("test file name:" + file.getOriginalFilename());
        return fileService.upLoadFile("ran-s3-bucket-1",file);
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public URL getObject(@RequestParam("fileName") String s3Key)
    {
        return fileService.getFileUrl(s3Key);
    }



}
