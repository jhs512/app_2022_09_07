package com.ll.exam.app10.app.member.controller;

import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import java.io.File;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/join")
  public String showJoin() {
    return "member/join";
  }

  @PostMapping("/join")
  public String Join(String username, String email, String password, MultipartFile profileImg, HttpSession session) {

    Member oldMember = memberService.fingByUsername(username);

    if (oldMember != null) {
      return "redirect:/?errorMsg=Already Joined!";
    }

    Member member = memberService.join(username, "{noop}" + password, email, profileImg);

    session.setAttribute("loginedMemberId", member.getId());

    return "redirect:/member/profile";
  }

  @GetMapping("/profile")
  public String showProFile(HttpSession session) {
    Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

    boolean isLogined = loginedMemberId != null;

    if (isLogined == false) {
      return "redirect:/?errorMsg=Please Join";
    }

    return "member/profile";
  }
}
