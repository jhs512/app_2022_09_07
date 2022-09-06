package com.ll.exam.app10.app.fileupload.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    @RequestMapping("")
    @ResponseBody
    public String upload(@RequestParam("img") MultipartFile img) {
        try {
            img.transferTo(new File(genFileDirPath + "/1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "업로드 완료!";
    }

//  // 세션 아이디를 이용한 저장 방식
//  @RequestMapping("")
//  @ResponseBody
//  public String upload(@RequestParam("img") MultipartFile img, HttpSession httpSession) {
//    try {
//      img.transferTo(new File(genFileDirPath +"/" + httpSession.getId() + ".png"));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//
//    return "업로드 완료!";
//  }
}