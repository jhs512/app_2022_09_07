package com.ll.exam.app10.app.member.controller;

import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public String showProFile(HttpSession session, Model model) {
    Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

    boolean isLogined = loginedMemberId != null;

    if (isLogined == false) {
      return "redirect:/?errorMsg=Please Join";
    }

    Member loginedMember = memberService.findMemberById(loginedMemberId);

    model.addAttribute("loginedMember", loginedMember);

    return "member/profile";
  }
}
