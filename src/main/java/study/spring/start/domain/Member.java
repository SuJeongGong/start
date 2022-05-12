package study.spring.start.domain;

import javax.persistence.*;

/**
 * packageName      : study.spring.start.domain
 * date             : 2022-03-09
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      : 회원객체
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-03-09       SuJeong Gong        최초작성
 */
@Entity//jpa가 관리하는 엔티티라고 표시
public class Member {
    /*
        id 어노테이션은 기본키 설정
        GeneratedValue는 id 값이 어떻게 되는지, 설정을 IDENTITY로 하면 DB가 알아서 자동 증가해 주는 것을 의미
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;        //아이디, 시스템이 저장하는 값

    @Column(name = "name") // 실제 DB에 있는 컬럼 이름과 맵핑해주는 것
    private String name;    //이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}