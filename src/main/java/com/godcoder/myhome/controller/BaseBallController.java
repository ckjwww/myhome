package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.entity.codeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.godcoder.myhome.service.baseballService;
import com.godcoder.myhome.service.codeService;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/baseball")
public class BaseBallController {

    @Autowired
    baseballService baseballservice;

    @Autowired
    codeService codeservice;

    @GetMapping("/listPosition")
    public String listPosition(Model model) {

        baseballDTO baseballDto = baseballservice.retrieveBaseball();
        List<codeDTO> codeDto = codeservice.retrieveCode("01");

        model.addAttribute("baseball", baseballDto);
        model.addAttribute("pos", codeDto);

        return "baseball/listPosition";
    }
}
