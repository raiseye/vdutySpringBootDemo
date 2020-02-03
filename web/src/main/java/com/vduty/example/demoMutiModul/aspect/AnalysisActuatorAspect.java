package com.vduty.example.demoMutiModul.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vduty.example.demoMutiModul.annotation.AnalysisActuator;
/**
 * AOP example
 * define a aspect
 * @author 职道 lx.ye
 *
 */
@Aspect
@Component
public class AnalysisActuatorAspect {
    final static Logger log = LoggerFactory.getLogger(AnalysisActuatorAspect.class);

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    /**
     * define a pointcut
     * @param analysisActuator
     */
    @Pointcut("@annotation(analysisActuator)")
    public void serviceStatistics(AnalysisActuator analysisActuator) {
    }

    //@Before(value = "切入点表达式或命名切入点", argNames = "参数列表参数名")  
    @Before("serviceStatistics(analysisActuator)")
    public void doBefore(JoinPoint joinPoint, AnalysisActuator analysisActuator) {
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        log.info("cy666 note:{}", analysisActuator.note());
    }

//    @After (   		value="切入点表达式或命名切入点",   		argNames="参数列表参数名") 
    @After("serviceStatistics(analysisActuator)")
    public void doAfter(AnalysisActuator analysisActuator) {
        log.info("cy666 statistic time:{}, note:{}", System.currentTimeMillis() - beginTime.get(), analysisActuator.note());
    }
    
}
