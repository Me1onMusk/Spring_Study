package com.example.register.service;

import com.example.register.domain.Member;
import com.example.register.repository.MemberRepository;
import com.example.register.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 서비스 //
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자 - DI 의존성 주입 //
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 //
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    // 중복 이름 검사 함수 //
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 //
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 ID 찾기 //
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
