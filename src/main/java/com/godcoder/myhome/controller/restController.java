package com.godcoder.myhome.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/grid")
public class restController {

    @GetMapping(value = "/members.do")
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

    @GetMapping(value = "/members1.do/{contId}")
    public List<Object> members(@PathVariable("contId") String contId) {

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

    @GetMapping(value = "/custom.do")
    public List<Object> custom() {

        ArrayList<Object> list = new ArrayList<Object>();

        for (int i = 1; i <= 5; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("custId", i);
            member.put("custName", "삼프로" + i);
            member.put("phone", "010-5896-125" + i);
            member.put("email", "global" + i + "@sampro.com");
            list.add(member);
        }

        return list;
    }

    @GetMapping(value = "/customlist.do/{custId}")
    public List<Object> customlist(@PathVariable("custId") String custId) {

        ArrayList<Object> list = new ArrayList<Object>();

        for (int i = 1; i <= 5; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("custId", custId);
            member.put("orderId", "ORDER20210629" + i);
            member.put("product", "삼프로" + i);
            member.put("quantity", i + 30);
            member.put("date", "2021-06-29");
            list.add(member);
        }

        return list;
    }

}
