package com.example.register.repository;

import com.example.register.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Kim");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("Kim");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("Lee");
        repository.save(member1);

        Member result = repository.findByName("Kim").get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("Kim");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("Lee");
        repository.save(member1);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
