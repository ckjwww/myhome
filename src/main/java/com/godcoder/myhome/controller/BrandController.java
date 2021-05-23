package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.contentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.godcoder.myhome.service.contentService;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BrandController {

    @Autowired
    contentService contentservice;

    @GetMapping("/list")
    public String list(Model model){
        List<contentDTO> contentdto =  contentservice.retrieveContent();
        model.addAttribute("board", contentdto);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new contentDTO());
        return "board/form";
    }

    @PostMapping("/form")
    public String form(@ModelAttribute contentDTO contentdto) {
        int inCnt = contentservice.insertContent(contentdto);
        return "redirect:/board/list";
    }
}
