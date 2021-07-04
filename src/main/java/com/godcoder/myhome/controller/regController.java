package com.godcoder.myhome.controller;

import com.godcoder.myhome.entity.textDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lab")
public class regController {

    @GetMapping("regsample")
    public String regsample(Model model) {

        String text1 = "";

        List<textDTO> textdto = new ArrayList<>();

        textDTO textadd = new textDTO();

        textadd.setText1("12");
        textadd.setText2("123");
        textadd.setText3("1234");

        textdto.add(textadd);

        model.addAttribute("textone", textadd);

        return "lab/regsample";
    }

    @PostMapping("regsample")
    //public String regsample(textDTO textparam, HttpSession session, HttpServletRequest request, Model model) {
    //public String regsample(Model model
    //                        ,@RequestPart MultipartRequest textdto
    //        , @ModelAttribute(value="textDTO") List<textDTO> textdto) {
    //public String regsample(@RequestPart MultipartRequest textdto) {
    public String regsample(@RequestParam String[] text1
                          , @RequestParam String[] text2
                          , @RequestParam String[] text3) {
        //String text1 = textdto[0].getText1();
        return "redirect:/lab/regsample";
    }

}
