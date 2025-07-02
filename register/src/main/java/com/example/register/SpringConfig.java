package com.example.register;

import com.example.register.repository.JdbcMemberRepository;
import com.example.register.repository.MemberRepository;
import com.example.register.repository.MemoryMemberRepository;
import com.example.register.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 빈 등록 직접 설정법 //
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
//        return new MemoryMemberRepository();
    }

}
