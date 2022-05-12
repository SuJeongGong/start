package study.spring.start.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import study.spring.start.domain.Member;

import java.util.List;

/**
 * packageName      : study.spring.start.repository
 * date             : 2022-03-09
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      :
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-03-09       SuJeong Gong        최초작성
 */
class MemoryRepositoryTest {
    //테스트 코드를 작성 할 때에는 순서에 영향을 받지 않도록 작성해야함

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){//테스트코드 하나가 끝날때 마다 한번씩 호출되는 메서드
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);        // 둘이 완전 같은지 비교하는것, member가 기준
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); // 위에는 순서와 상관 있음, 이건 상관없음
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1); // 위에는 순서와 상관 있음, 이건 상관없음
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
