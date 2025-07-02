package com.example.register;

import com.example.register.repository.MemberRepository;
import com.example.register.repository.MemoryMemberRepository;
import com.example.register.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 빈 등록 직접 설정법 //
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
