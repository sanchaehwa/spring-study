package hello.servlet.web.springmvc.v2;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3{
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }
    @PostMapping("/save")
    public String save(@RequestParam("username") String name, @RequestParam("age") Integer age, Model model) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }
    @GetMapping
    public String members(Model model)  {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

}
