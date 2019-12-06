package org.yinxianren.springboot.aop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.yinxianren.springboot.aop.annotation.MgdbGetOne;
import org.yinxianren.springboot.table.MerchantInfo;
import org.yinxianren.springboot.tools.Println;

import java.lang.reflect.Field;


@Aspect
@Component
public class MgdbGetOneAspect implements Println {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 定义一个切点
     */
    @Pointcut(value = "@annotation(org.yinxianren.springboot.aop.annotation.MgdbGetOne)")
    public void getOnePoint() {
    }

    @Around("getOnePoint()")
    public  Object getOne(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Object arg01 = args[0];
        Field[]  fields = arg01.getClass().getDeclaredFields();
        Query query=new Query();
        for(Field field :fields){
            field.setAccessible(true);
            String name = field.getName();
            Object obj = field.get(arg01);
            if( null == obj )  continue;
            if( obj instanceof String && !((String) obj).trim() .equalsIgnoreCase(""))
                query.addCriteria(  Criteria.where(name).is(obj) );
            else
                query.addCriteria(  Criteria.where(name).is(obj) );
        }
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        MgdbGetOne mgdbGetOne = signature.getMethod().getAnnotation(MgdbGetOne.class);
        String collectionName = mgdbGetOne.collectionName();
        Object  object = mongoTemplate.findOne(query,arg01.getClass(),collectionName);
        if( null ==object ){
            println("进入数据库查询！！！！！");
            object =  pjp.proceed();
            if(null != object ){
                mongoTemplate.save(object,collectionName);
            }
        }
        return  object;
    }

}
