package tech.thegamedefault.notification.config;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

  @Pointcut("within(tech.thegamedefault.notification.*.controller..*)"
      + " || within(tech.thegamedefault.notification.*.framework..*)"
      + " || within(tech.thegamedefault.notification.*.config..*)"
      + " || within(tech.thegamedefault.notification.*.service..*)")
  public void applicationPackagePointcut() {
  }

  @Pointcut("within(@org.springframework.stereotype.Repository *)"
      + " || within(@org.springframework.stereotype.Service *)"
      + " || within(@org.springframework.stereotype.Component *)"
      + " || within(@org.springframework.stereotype.Controller *)"
      + " || within(@org.springframework.web.bind.annotation.RestController *)")
  public void springBeanPointcut() {
  }

  @Around("applicationPackagePointcut() && springBeanPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopwatch = new StopWatch();
    stopwatch.start();
    log.info("Enter: {}.{}() with argument[s] = {}",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    try {
      Object result = joinPoint.proceed();
      stopwatch.stop();
      log.info(" [Time taken (Millis): {}] Exit: {}.{}() with result = {}",
          stopwatch.getTotalTimeMillis(),
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), result);

      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
          joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
      throw e;
    }
  }

  @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    log.error("Exception in {}.{}() with cause = {}, details: {}",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL",
        Arrays.stream(e.getStackTrace())
            .limit(1000)
            .map(StackTraceElement::toString)
            .collect(Collectors.joining("\n")));
  }


}
