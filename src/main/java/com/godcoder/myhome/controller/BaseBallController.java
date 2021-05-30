package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.entity.codeDTO;
import com.godcoder.myhome.entity.contentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.godcoder.myhome.service.baseballService;
import com.godcoder.myhome.service.codeService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/baseball")
public class BaseBallController  implements WebMvcConfigurer {

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

    @PostMapping("/listPosition")
    public String listPosition(@Valid @ModelAttribute("baseballDto") baseballDTO baseballDto) {

        if (baseballDto.getManageId() == null) {
            baseballservice.insertBaseball(baseballDto);
        } else {
            baseballservice.updateBaseball(baseballDto);
        }

        return "redirect:/baseball/listPosition";
    }
}
