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

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @GetMapping("/list")
    public String list(Model model
                    , @RequestParam(required = false) Integer pageNo
                    , @RequestParam(required = false) String searchVal){

        int searchDataIdx = 0;
        int pageDataCnt = 2;

        if (pageNo == null) {
            pageNo = 1;
        } else {
            searchDataIdx = (pageNo - 1) * pageDataCnt;
        }
        if (searchVal == null) {
            searchVal = "";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("searchDataIdx", searchDataIdx);
        map.put("pageDataCnt", pageDataCnt);
        map.put("searchVal", searchVal);

        int iContentTotCnt = contentservice.getContentCnt(map);
        int iPageCnt = (iContentTotCnt / pageDataCnt) + ((iContentTotCnt % pageDataCnt) > 0 ? 1 : 0);

        List<contentDTO> contentDto =  contentservice.retrieveContents(map);
        model.addAttribute("board", contentDto);
        model.addAttribute("pageCnt", iPageCnt);
        model.addAttribute("curPageNo", pageNo);
        model.addAttribute("searchVal", searchVal);

        return "board/list";
    }

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @GetMapping("/form")
    public String form(Model model
            , @RequestParam(required = false) Integer id
            , @RequestParam(required = false) String flag) {

        if (flag == null) {
            contentDTO contentDto = new contentDTO();
            if (id != null) {
                contentDto = contentservice.retrieveContent(id);
            }
            model.addAttribute("contentDto", contentDto);

            return "board/form";
        }
        else {
            contentservice.deleteContent(id);

            return "redirect:/board/list";
        }
    }

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @PostMapping("/delete")
    public String delete(contentDTO contentDto) {

        contentservice.deleteContent(contentDto.getId());
        return "redirect:/board/list";
    }

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @PostMapping("/form")
    //public String form(@ModelAttribute contentDTO contentDto) {
    public String form(@Valid @ModelAttribute("contentDto") contentDTO contentDto, BindingResult bindingResult) {
    //public String form(@Valid contentDTO contentDto, BindingResult bindingResult) {
        boardvalidator.validate(contentDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        contentservice.insertContent(contentDto);
        return "redirect:/board/list";
    }

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @GetMapping("/test")
    public String form(@ModelAttribute("contentDto") contentDTO contentdto) {
        //model.addAttribute("contentDto", new contentDTO());
        return "board/test";
    }

    @SuppressWarnings({"unchecked", "unused", "RedundantSuppression"})
    @PostMapping("/test")
    public String test(@Valid @ModelAttribute("contentDto") contentDTO contentdto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/test";
        }
        contentservice.insertContent(contentdto);
        return "redirect:/board/list";
    }
}
