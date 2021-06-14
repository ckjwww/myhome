package com.godcoder.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lab")
public class labController {

    @GetMapping("labfilesample")
    public String labfilesample () {
        return "lab/labfilesample";
    }
}
