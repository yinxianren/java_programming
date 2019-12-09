package org.yinxianren.springboot.aop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value={ElementType.METHOD})
public @interface MgdbGetOne {

    /**
     *  集合名称
     * @return
     */
    String collectionName();
}
