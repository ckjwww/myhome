package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.fileDTO;
import com.godcoder.myhome.service.fileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/lab")
public class labController {

    @Autowired
    fileService fileservice;

    @GetMapping("labfilesample")
    public String labfilesample () {

        return "lab/labfilesample";
    }

    @PostMapping("/labfilesample")
    public String labfilesample(MultipartHttpServletRequest files
                              , @RequestParam(required = false) List<String> inputvalue) {
        String path = Paths.get("C:", "develop", "server-file").toString();

        List<MultipartFile> mfiles = files.getFiles("files");

        String orgFileName = "";
        long fileSize = 0;
        String contentType = "";
        String writeFileName = "";
        String writePhysicalPathAndName = "";

        for (MultipartFile mfile: mfiles) {
            orgFileName = mfile.getOriginalFilename();
            fileSize = mfile.getSize();
            contentType = mfile.getContentType();
            writeFileName = String.format("%s%s", System.currentTimeMillis(), orgFileName);
            writePhysicalPathAndName = String.format("%s\\%s", path, writeFileName);

            try {
                mfile.transferTo(new File(writePhysicalPathAndName));
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            fileDTO fileDto = new fileDTO();
            fileDto.setOrgFileName(orgFileName);
            fileDto.setWriteFileName(writeFileName);
            fileDto.setFileSize(fileSize);
            fileDto.setContentType(contentType);

            fileservice.insertBaseball(fileDto);
        }

        return "redirect:/lab/labfilesample";
    }
}
