package study.spring.start.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect //aop로 쓰려면 붙여줘야하는 어노테이션
public class TimeTraceAop {

    @Around("execution(* study.spring..*(..))")//타겟팅 범위, 문법에 맞추어서 package명과 그 이후에 맞춰서
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("[START] " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("[END] " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
