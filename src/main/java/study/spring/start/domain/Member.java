package study.spring.start.domain;

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
public class Member {
    private Long id;        //아이디, 시스템이 저장하는 값
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