package com.coderunning.aspect;

import com.coderunning.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Aspect
public class MyAspect {


    @Before(value = "myPointCut()")
    public void doBefore(JoinPoint joinPoint) {

        System.out.println("doBefore");
    }
//
//    @AfterReturning(value = "execution( *   doOtherThing())", returning = "res")
//    public void doAfterReturning(Object res) {
//
//        User user = (User) res;
//        user.setAge(12);
//        System.out.println(res);
//    }

    @Around(value = "myPointCut())")
    public Object doAround(ProceedingJoinPoint pjp) {

        Object[] args = pjp.getArgs();
        for (Object s : args) {
            System.out.println(s);
        }
        Object res = null;
        try {
            res = pjp.proceed(new Integer[]{111,222});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
    @AfterReturning(value = "myPointCut())")
    public void getAfterReturning() {
        System.out.println("after returning");
    }

    @Pointcut(value ="execution(public int com.coderunning.service.MyCaculator.*(..))" )
    public void myPointCut() {

    }

}
