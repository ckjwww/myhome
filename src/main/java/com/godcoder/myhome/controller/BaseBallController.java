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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
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

        baseballDTO baseballDto = baseballservice.retrieveBaseball();
        List<baseballDTO> baseballistDto = baseballservice.retrieveBaseballist();
        List<codeDTO> codeDto = codeservice.retrieveCode("01");

        model.addAttribute("baseball", baseballDto);
        model.addAttribute("baseballist", baseballistDto);
        model.addAttribute("pos", codeDto);

        return "baseball/listposition";
    }

    @PostMapping("/listposition")
    public String listPosition(@Valid @ModelAttribute("baseballDto") baseballDTO baseballDto, Model model) {

        if (baseballDto.getCheckBox() == null) {
            baseballDto.setCheckBox("N");
        }

        if (baseballDto.getManageId().isEmpty()) {
            HashMap hashValue = baseballservice.insertBaseball(baseballDto);
            baseballDto.setManageId(hashValue.get("manageId").toString());
        } else {
            baseballservice.updateBaseball(baseballDto);
        }

        //List<codeDTO> codeDto = codeservice.retrieveCode("01");

        //model.addAttribute("baseball", baseballDto);
        //model.addAttribute("pos", codeDto);

        return "redirect:/baseball/listposition";
    }

    @PostMapping("/deleteposition")
    public String listPosition(Model model
                             , @RequestParam(required = false) String manageid) {

        baseballservice.deleteBaseball(manageid);

        return "redirect:/baseball/listposition";
    }
}
