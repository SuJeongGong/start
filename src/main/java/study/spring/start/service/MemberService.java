package study.spring.start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.spring.start.domain.Member;
import study.spring.start.repository.MemberRepository;
import study.spring.start.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

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
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member 가입할 회원
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은X
        
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{  //ifPresent란 null이 아니라 값이 있으면! m에는 해당하는 값이 들어감?
            //orElseGet 값이 있으면 꺼내고, 값이 없으면 메서드를 실행해!
            throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{  //ifPresent란 null이 아니라 값이 있으면! m에는 해당하는 값이 들어감?
                //orElseGet 값이 있으면 꺼내고, 값이 없으면 메서드를 실행해!
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 아이디로 회원조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
