package study.spring.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.start.repository.JdbcMemberRepository;
import study.spring.start.repository.MemberRepository;
import study.spring.start.repository.MemoryMemberRepository;
import study.spring.start.service.MemberService;

import javax.sql.DataSource;

/**
 * packageName      : study.spring.start
 * date             : 2022-04-04
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      :
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-04-04       SuJeong Gong        최초작성
 */

@Configuration
public class SpringConfig {

    /**
     * application.properties 파일을 읽으면서, DataSource부분을 다 load하면서 DataSource는 생성해서 스프링 컨테이너에 넣어둠
     * 이미 생성한 DataSource를 여기 있는 dataSource로 연결
     */
    private DataSource dataSource;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);

        /**
         * spring을 쓰는 이유
         * : 객체지향적인 설계를 훨씬 쉽게 하도록 스프링 컨테이너가 지원을 해줌
         * 다형성을 활용한 예시
         * : 원래는 인터페이스를 두고, 구현체는 MemoryMemberRepository만 JdbcMemberRepository로 변경
         */
    }
}
