package com.coderunning.aspect;

import com.coderunning.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class MyAspect {


    @Before(value = "execution( *  doSomething())")
    public void doBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        for (Object s : args) {
            System.out.println(s);
        }
        System.out.println(joinPoint.getSignature().getDeclaringTypeName().toString() + System.currentTimeMillis());
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
            res = pjp.proceed(new String[]{"222"});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(res);
        return res;
    }

    @Pointcut(value ="execution( *   doOtherThing(..))" )
    public void myPointCut() {

    }

}
