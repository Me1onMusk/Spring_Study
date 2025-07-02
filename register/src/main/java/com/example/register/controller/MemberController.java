package com.example.register.controller;

import com.example.register.domain.Member;
import com.example.register.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// Controller <- Service
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // http://localhost:8080/home-mvc?name=Kim
    // URL 파라미터 받기
    @GetMapping("/home-mvc")
    public String homeMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);

        return "home-template";
    }

    // URL 파라미터 String으로 그대로 출력
    @GetMapping("/home-string")
    @ResponseBody
    public String homeString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // URL 파라미터 JSON으로 출력
    @GetMapping("/home-api")
    @ResponseBody
    public Hello hello(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
