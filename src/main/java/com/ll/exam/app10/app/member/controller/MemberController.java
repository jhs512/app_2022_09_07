package com.ll.exam.app10.app.member.controller;

import com.ll.exam.app10.app.auth.PrincipalDetails;
import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder passwordEncoder;

  // 회원가입 창
  @GetMapping("/join")
  public String showJoin() {
    return "member/join";
  }

  // 로그인 창
  @GetMapping("/login")
  public String showLogin() {
    return "login";
  }

  // 회원가입 진행
  @PostMapping("/join")
  public String Join(String username, String email, String password, MultipartFile profileImg) {

    Member oldMember = memberService.fingByUsername(username);

    if (oldMember != null) {
      return "redirect:/?errorMsg=Already Joined!";
    }

    String encodedPwd = passwordEncoder.encode(password);

    Member member = memberService.join(username, encodedPwd, email, profileImg);

    return "redirect:/";
  }

//  @GetMapping("/profile") // session 방식
//  public String showProFile(HttpSession session, Model model) {
//    Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");
//
//    boolean isLogined = loginedMemberId != null;
//
//    if (isLogined == false) {
//      return "redirect:/?errorMsg=Please Join";
//    }
//
//    Member loginedMember = memberService.findMemberById(loginedMemberId);
//
//    model.addAttribute("loginedMember", loginedMember);
//
//    return "member/profile";
//  }

  @RequestMapping("/profile") // SpringBoot Security 방식
  public String showProFile(Authentication authentication, Model model) {

    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

    Member member = principal.getMember();

    model.addAttribute("loginedMember", member);

    return "member/profile";
  }
}
