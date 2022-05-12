package study.spring.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.spring.start.domain.Member;
import study.spring.start.service.MemberService;

import java.util.List;


/**
 * packageName      : study.spring.start.controller
 * date             : 2022-03-24
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      : 멤버 관련 컨트롤러
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-03-24       SuJeong Gong        최초작성
 */
//Controller 어노테이션이 붙어있으면,
// 프로젝트가 실행될 떄 스프링 컨테이너라는 통이 생기는데,
// 이 통에 Controller 어노테이션이 붙은 클래스를 객체로 생성해서 넣어두고, 스프링이 관리함
// => "스프링 컨테이너에서 빈이 관리된다"고 말함
@Controller
public class MemberController {

    //이렇게 써도 되긴하는데,
    // 스프링이 관리를 하게 되면, 관리되는것들은??  스프링 컨테이너에 등록을 하고, 스프링 컨테이너에서 받아서 쓰도록 해야함
    // 이렇게 쓸 때의 문제점. 이 객체는 하나만 있어서 그걸로 사용을 해도 되는데
    // 만약 이 컨트롤러 말고 주문 컨트롤러 등 여러 컨트롤러에서 이렇게 사용을 한다면 new로 새로 생성하기 때문
   //private final MemberService memberService = new MemberService();



    //변경후
    private final MemberService memberService;

    @Autowired//@Autowired가 붙어있으면, 스프링이 스프링 컨테이너에 존재하는 MemberService를 가져다가 연결시켜줌
    private MemberController(MemberService memberService){//@Controller를 붙이면 객체를 생성해서 스프링 컨테이너에 등록하는데 이때, 이 생성자를 사용하도록 유돌함
        this.memberService = memberService;
    }

    /**
     * description      : 회원등록 페이지로 이동
     * @return          : createMemberFrom.html
     * author           : SuJeong Gong
     * date             : 2022-03-26
     */
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
