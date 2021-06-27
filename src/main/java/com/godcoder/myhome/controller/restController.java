package com.godcoder.myhome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grid")
public class restController {

    @GetMapping(value = "/members")
    public Map<Integer, Object> testByResponseBody() {

        Map<Integer, Object> members = new HashMap<>();

        for (int i = 1; i <= 20; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("idx", i);
            member.put("nickname", i + "길동");
            member.put("height", i + 20);
            member.put("weight", i + 30);
            members.put(i, member);
        }

        return members;
    }

    @GetMapping(value = "/members1")
    public List<Object> members() {

        ArrayList<Object> list = new ArrayList<Object>();

        for (int i = 1; i <= 20; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("idx", i);
            member.put("nickname", i + "길동");
            member.put("height", i + 20);
            member.put("weight", i + 30);
            list.add(member);
        }

        return list;
    }

}
