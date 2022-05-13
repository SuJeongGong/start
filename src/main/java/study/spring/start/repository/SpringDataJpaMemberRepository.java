package study.spring.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.start.domain.Member;

import java.util.Optional;


/*
    방금까지 사용한 spring + jpa만 사용해서도 개발을 완료 할 수 있지만,
    data Jpa라는 프레임워크를 더하면 조금이라도 반복 되었던 개발 코드들이 확연하게 줄어들어
    개발자는 핵심 비즈니스 로직을 개발하는데에 집중할 수 있습니다.
 */

/*
    SpringDataJpaMemberRepository가 JpaRepository를 상속받고 있으면,
    SpringJpa가 SpringDataJpaMemberRepository의 구현체를 만들고 bean으로 등록해줌
    그러면 그것을 가져다 쓰기만 하면됨, SpringConfig에서 설정
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //interface 가 interface 를 상속받을 때에는 extends 라고함

    @Override
    Optional<Member> findByName(String name);
    
    /*
        spring data JPA에서 기본적인 CRUD가 제공이 됨
        따라서 findByName을 제외한 나머지는 구현하지 않아도 작동이 잘 됨
        또, findByName과 같이 findBy~ 라는 이름 규칙을 따르면 해당 이름 규칙을 통해 JPQL을 짜게됨
            ex ) findBy -> select m from Member m where m.~ = ?  
        또 페이징 기능도 자동으로 제공이 됨
        실무에서는 JPA와 스프링 JPA를 기본으로 사용을 하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용한다.
        Querydsl는 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다.
        이렇게도 해결하기 어려운 쿼리라면 JPA가 제공하는 네이티브 쿼리나 JdbcTemplate를 사용하면 된다.
     */
}
