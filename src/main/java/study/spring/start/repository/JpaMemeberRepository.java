package study.spring.start.repository;

import org.springframework.transaction.annotation.Transactional;
import study.spring.start.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * packageName      : study.spring.start.repository
 * date             : 2022-05-12
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      :
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-05-12       SuJeong Gong        최초작성
 */
/*
    jpa는 모든 데이터 변경이 transaction 안에서 처리가 되어야함
    그래서 해당 어노테이션을 붙임
 */
@Transactional
public class JpaMemeberRepository implements MemberRepository{
    /*
        JPA는 EntityManager로 모든게 동작함
        build.gradle에서 data-jpa를 받았다면 라이브러리가 자동으로 다운로드 됨.
        EntityManager는 database 커넥션 정보 등을 모두 가지고 있음 (spring boot가 만들어줌)
     */
    private final EntityManager em;

    public JpaMemeberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        /*
            persist가 영속하다, 영원히 저장하다
         */
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// 조회할 타입과, 식별자를 넣어줌 -> id가 기본키라서 이렇게 검색하면됨
        return Optional.ofNullable(member);

    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
            :name은 뒤에서 setParameter 할때 name에 들어갈 문자열과 같아야함.
         */
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
            table을 대상으로 쿼리를 날리는 것이 아니라 객체를 대상으로 쿼리를 날림

            Member를 조회해! member자체를! Member m은 as가 생략된 것
         */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
