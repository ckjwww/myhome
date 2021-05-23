package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.contentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.godcoder.myhome.service.contentService;

@Controller
@RequestMapping("/board")
public class BrandController {

    @Autowired
    contentService contentservice;

    @GetMapping("/list")
    public String list(Model model){

        contentDTO contentdto =  contentservice.retrieveContent();

        model.addAttribute("board", contentdto);
        return "board/list";
    }
}
