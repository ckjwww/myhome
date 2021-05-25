package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.contentDTO;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.godcoder.myhome.service.contentService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BrandController implements WebMvcConfigurer {

    @Autowired
    contentService contentservice;

    @Autowired
    BoardValidator boardvalidator;

    @GetMapping("/list")
    public String list(Model model){
        List<contentDTO> contentdto =  contentservice.retrieveContents();
        model.addAttribute("board", contentdto);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(@ModelAttribute("contentdto") contentDTO contentdto, @RequestParam(required = false) Integer id) {
        if (id == null) {
            contentdto = new contentDTO();
        } else {
            contentdto =  contentservice.retrieveContent(id);
        }
        return "board/form";
    }
//    public String form(Model model, @RequestParam(required = false) Integer id) {
//        if (id == null) {
//            model.addAttribute("board", new contentDTO());
//        } else {
//            contentDTO contentdto =  contentservice.retrieveContent(id);
//            model.addAttribute("board", contentdto);
//        }
//        return "board/form";
//    }

    @PostMapping("/form")
    //public String form(@ModelAttribute contentDTO contentdto) {
    public String form(@Valid @ModelAttribute("contentdto") contentDTO contentdto, BindingResult bindingResult) {
    //public String form(@Valid contentDTO contentdto, BindingResult bindingResult) {
        //boardvalidator.validate(contentdto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        int inCnt = contentservice.insertContent(contentdto);
        return "redirect:/board/list";
    }

    @GetMapping("/test")
    public String form(@ModelAttribute("contentdto") contentDTO contentdto) {
        //model.addAttribute("contentdto", new contentDTO());
        return "board/test";
    }

    @PostMapping("/test")
    public String test(@Valid @ModelAttribute("contentdto") contentDTO contentdto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/test";
        }
        int inCnt = contentservice.insertContent(contentdto);
        return "redirect:/board/list";
    }
}
