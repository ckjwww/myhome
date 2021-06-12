package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.entity.codeDTO;
import com.godcoder.myhome.entity.contentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.godcoder.myhome.service.baseballService;
import com.godcoder.myhome.service.codeService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
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

        MultipartFile mfile = files.getFile("files");
        String orgFileName = mfile.getOriginalFilename();
        long fileSize = mfile.getSize();
        String contentType = mfile.getContentType();


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
