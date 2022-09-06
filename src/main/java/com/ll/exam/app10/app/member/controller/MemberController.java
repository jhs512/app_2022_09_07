package com.ll.exam.app10.app.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
  @GetMapping("/join")
  public String memberJoin() {
    return "member/join.html";
  }

  @PostMapping("/join")
  @ResponseBody
  public String memberJoinCheck() {
    return "회원가입을 축하합니다";
  }
}
