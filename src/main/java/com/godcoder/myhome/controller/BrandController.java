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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BrandController implements WebMvcConfigurer {

    @Autowired
    contentService contentservice;

    @Autowired
    BoardValidator boardvalidator;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false) Integer pageNo){

        int pageDataCnt = 2;
        int searchDataIdx = 0;
        int iContentTotCnt = contentservice.getContentCnt();
        int iPageCnt = (iContentTotCnt / pageDataCnt) + ((iContentTotCnt % pageDataCnt) > 0 ? 1 : 0);

        if (pageNo == null) {
            searchDataIdx = 0;
            pageNo = 1;
        } else {
            searchDataIdx = (pageNo - 1) * pageDataCnt;
        }
        Map map = new HashMap();
        map.put("searchDataIdx", searchDataIdx);
        map.put("pageDataCnt", pageDataCnt);

        List<contentDTO> contentdto =  contentservice.retrieveContents(map);
        model.addAttribute("board", contentdto);
        model.addAttribute("pageCnt", iPageCnt);
        model.addAttribute("curPageNo", pageNo);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model
            , @RequestParam(required = false) Integer id
            , @RequestParam(required = false) String flag) {

        if (flag == null) {
            contentDTO contentdto = null;
            if (id == null) {
                contentdto = new contentDTO();
            } else {
                contentdto = contentservice.retrieveContent(id);
            }
            model.addAttribute("contentdto", contentdto);

            return "board/form";
        }
        else {
            int inCnt = contentservice.deleteContent(id);

            return "redirect:/board/list";
        }
    }

    @PostMapping("/delete")
    public String delete(contentDTO contentdto) {

        int inCnt = contentservice.deleteContent(contentdto.getId());
        return "redirect:/board/list";
    }

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
