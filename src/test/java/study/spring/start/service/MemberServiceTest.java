package study.spring.start.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.spring.start.domain.Member;
import study.spring.start.repository.MemberRepository;
import study.spring.start.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName      : study.spring.start.service
 * date             : 2022-03-10
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      :
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-03-10       SuJeong Gong        최초작성
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){//테스트코드 하나가 끝날때 마다 한번씩 호출되는 메서드
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 이런상황이 주어져서
        Member member = new Member();
        member.setName("hello");


        //when : 이렇게 실행했을떄
        Long saveId = memberService.join(member);


        //then : 이런 결과가 나와야한다
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원가입검사(){
        /* 내가 짠 코드
        //given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        //when
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);

        //then
        //오류 메세지가 나와야함
        */
        
        
        
        //given
        Member member3 = new Member();
        member3.setName("hello");

        Member member4 = new Member();
        member4.setName("hello");
        
        
        //when
        memberService.join(member3);
/*
        try{
            memberService.join(member4);
            fail(); // 여기가 실행되면 실패 오류가 안 터진거니까
        }catch (IllegalStateException e){
            //성공
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        IllegalStateException e = assertThrows(
                IllegalStateException.class,        //이런 오류가 터져야함
                () -> memberService.join(member4)   // 이거를 실행했을 때에에
        );
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
        
    }


    @Test
    void 회원찾기() {
    }

    @Test
    void findOne() {
    }
}