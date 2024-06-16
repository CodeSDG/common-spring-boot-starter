package top.codesdg.common.whitelist.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.codesdg.common.whitelist.annotation.DoWhiteList;
import top.codesdg.common.whitelist.config.WhiteListProperties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午10:42
 * @description
 * @Copyright
 */
@Aspect
public class WhiteListAspect {

    private final Logger logger = LoggerFactory.getLogger(WhiteListAspect.class);

    @Resource
    private WhiteListProperties whiteListProperties;


    @Pointcut("@annotation(top.codesdg.common.whitelist.annotation.DoWhiteList)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        // 获取注解
        Method method = getMethod(jp);
        DoWhiteList whiteList = method.getAnnotation(DoWhiteList.class);
        // 获取属性值
        CodeSignature signature = (CodeSignature) jp.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = jp.getArgs();
        HashMap<String, Object> param = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            param.put(parameterNames[i], args[i]);
        }
        String filedValue = getFiledValue(whiteList.key(), param);
        logger.info("middleware whitelist handler method：{} value：{}", method.getName(), filedValue);
        if(StrUtil.isBlank(filedValue)) {
            return jp.proceed();
        }
        // 白名单校验
        if (whiteListProperties.getUsers().contains(filedValue)) {
            return jp.proceed();
        }
        //拦截
        return returnObject(whiteList, method);

    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    // 返回对象
    private Object returnObject(DoWhiteList whiteList, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = whiteList.returnValue();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    // 获取属性值
    private String getFiledValue(String filed, HashMap<String, Object> args) {
        try {
            return BeanUtils.getProperty(args, filed);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
