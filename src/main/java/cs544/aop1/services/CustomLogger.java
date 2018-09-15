package cs544.aop1.services;

/**
 * User: franc
 * Date: 12/09/2018
 * Time: 4:17
 */


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Aspect
public class CustomLogger {
    @After("execution(* EmailSender.sendEmail(..))")
    public void logBefore(JoinPoint joinPoint) throws ParseException {
        Object[] signatureArgs = joinPoint.getArgs();
        Object name  =    signatureArgs[0];
        Object email  =   signatureArgs[1];
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = new Date();


        System.out.printf("%s method= %s",dateFormat.format(date),joinPoint.getSignature().getName());


        System.out.printf(" address=%s message= Welcome %s as a new customer \n %s",email,name,((EmailSender)joinPoint.getTarget()) .getOutgoingMailServer());
    }
    @Around("execution(* cs544.aop1.dao.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        // print the time to the console
        System.out.printf("Time to execute save = %s ms\n",totaltime);
        return retVal;

    }


}
