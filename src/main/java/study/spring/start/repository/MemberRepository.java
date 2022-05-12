package study.spring.start.repository;

import study.spring.start.domain.Member;

import java.util.List;
import java.util.Optional;

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
public interface MemberRepository {
    Member  save(Member member);    // 회원저장
    Optional<Member> findById(Long id);         // 회원조회 - 아이디로
    Optional<Member> findByName(String name);         // 회원조회 -이름으로
    List<Member> findAll(); //회원조회 - 모든회원
}
