package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.entity.codeDTO;
import com.godcoder.myhome.entity.fileDTO;
import com.godcoder.myhome.service.fileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.godcoder.myhome.service.baseballService;
import com.godcoder.myhome.service.codeService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/baseball")
public class BaseBallController  implements WebMvcConfigurer {

    @Autowired
    baseballService baseballservice;

    @Autowired
    codeService codeservice;

    @Autowired
    fileService fileservice;

    @GetMapping("/listposition")
    public String listPosition(Model model) {

        baseballDTO baseballEmptyDto = new baseballDTO();

        List<baseballDTO> baseballistDto = baseballservice.retrieveBaseballist();
        List<codeDTO> codeDto = codeservice.retrieveCode("01");

        model.addAttribute("baseball", baseballEmptyDto);
        model.addAttribute("baseballist", baseballistDto);
        model.addAttribute("pos", codeDto);

        return "baseball/listposition";
    }

    @GetMapping("/baseballfiledownload")
    public void baseballfiledownload(@RequestParam(required = true) String fileid
                                     , HttpServletResponse response ) {

        if (fileid == null) throw new RuntimeException("올바르지 않은 접근입니다.");

        fileDTO fileInfo = fileservice.retrieveFile(fileid);
        if (fileInfo == null) {
            throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
        }

        //"C:\\develop\\server-file\\";
        String uploadPath = Paths.get("C:", "develop", "server-file").toString();

        String filename = fileInfo.getOrgFileName();
        File file = new File(uploadPath, fileInfo.getWriteFileName());

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType("application/octet-stream");
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");

            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드에 실패하였습니다.");

        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }

    }

    @GetMapping("/baseballmodal")
    public String baseballmodal(Model model
            , @RequestParam(required = false) String manageid) {

        baseballDTO baseballDto = null;

        if (manageid == null) {
            baseballDto = new baseballDTO();
        } else {
            baseballDto = baseballDto = baseballservice.retrieveBaseball(manageid);;
        }

        List<codeDTO> codeDto = codeservice.retrieveCode("01");

        model.addAttribute("baseballOne", baseballDto);
        model.addAttribute("pos", codeDto);

        return "baseball/baseballmodal";
    }

    @PostMapping("/listposition")
    public String listPosition(@Valid @ModelAttribute("baseballDto") baseballDTO baseballDto
                             //, @RequestPart MultipartFile files
                             //,  @RequestParam("files") MultipartFile files
                             , MultipartHttpServletRequest files
                             //, HttpServletRequest files
                             , Model model) {

        if (baseballDto.getCheckBox() == null) {
            baseballDto.setCheckBox("N");
        }

        String path = "C:\\develop\\server-file\\";

        MultipartFile mfile = files.getFile("files");
        String orgFileName = mfile.getOriginalFilename();
        long fileSize = mfile.getSize();
        String contentType = mfile.getContentType();
        String writeFileName = String.format("%s%s", System.currentTimeMillis(), orgFileName);
        String writePhysicalPathAndName = String.format("%s%s", path, writeFileName);

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

//        Iterator iter = files.getFileNames();
//        while (iter.hasNext()) {
//            fieldName = iter.next().toString(); // 내용을 가져와서
//             mfile = files.getFile(fieldName);
//
//        }

        if (baseballDto.getManageId().isEmpty()) {
            HashMap hashValue = baseballservice.insertBaseball(baseballDto);
            baseballDto.setManageId(hashValue.get("manageId").toString());
        } else {
            baseballservice.updateBaseball(baseballDto);
        }

        return "redirect:/baseball/listposition";
    }

    @PostMapping("/deleteposition")
    public String listPosition(Model model
                             , @RequestParam(required = false) String manageid
                             , @RequestParam(required = false) String manageid1) {

        baseballservice.deleteBaseball(manageid);

        return "redirect:/baseball/listposition";
    }
}
