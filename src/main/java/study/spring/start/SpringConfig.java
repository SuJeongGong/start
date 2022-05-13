package study.spring.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.start.aop.TimeTraceAop;
import study.spring.start.repository.*;
import study.spring.start.service.MemberService;

import javax.persistence.EntityManager;
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
//    private final DataSource dataSource;
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /*
    //jpa 사용시
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    */

    //dataJpa 사용시
    private final MemberRepository memberRepository;//Spring Jpa가 구현해놓은 구현체가 자동으로 등록이 된다.

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());

        return new MemberService(memberRepository);
    }

    
    /*
        aop의 경우 aop가 정의된 class위에 @Component 어노테이션을 붙여서 Bean등록을 해주는 것도 좋지만,
        Service 등과 같은 것들은 spring을 쓰면 정형화된 것 처럼 사용해야하는 거지만,
        aop는 그런 것이 아니기 때문에 이렇게 따로 등록을 해서 사람이 코드만 보고도 인지가 더 잘 되게 하는 것이 좋음
     */
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    /*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemeberRepository(em);
    }
    */
        /**
         * spring을 쓰는 이유
         * : 객체지향적인 설계를 훨씬 쉽게 하도록 스프링 컨테이너가 지원을 해줌
         * 다형성을 활용한 예시
         * : 원래는 인터페이스를 두고, 구현체는 MemoryMemberRepository만 JdbcMemberRepository로 변경
         */
}
