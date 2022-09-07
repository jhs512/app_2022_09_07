package com.ll.exam.app10.app.member.service;

import com.ll.exam.app10.app.auth.PrincipalDetails;
import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    private final MemberRepository memberRepository;

    public Member fingByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Member join(String username, String password, String email, MultipartFile profileImg) {
        String profileImgRelPath = "member/" + UUID.randomUUID().toString() + ".png";

        File profileImgFile = new File(genFileDirPath + "/" + profileImgRelPath);

        profileImgFile.mkdirs(); // 관련 폴더가 없으면 생성

        try {
            profileImg.transferTo(profileImgFile);
            // 업로드한 파일 데이터를 지정한 파일에 저장한다.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 방법 (1)
//        Member member = new Member(username, password, email, profileImgRelPath);
        // 방법 (2)
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .profileImg(profileImgRelPath)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member findMemberById(Long loginedMemberId) {
        return memberRepository.findById(loginedMemberId).orElse(null);
    }

    @Override
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member byUsername = memberRepository.findByUsername(username).orElse(null);

        if (byUsername != null) {
            return new PrincipalDetails(byUsername);
        }
        return null;
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }
}
