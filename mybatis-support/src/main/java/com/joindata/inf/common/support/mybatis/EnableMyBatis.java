package com.joindata.inf.common.support.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.plugin.Interceptor;

import com.joindata.inf.common.basic.annotation.JoindataComponent;

/**
 * 启用 MyBatis
 * 
 * @author <a href="mailto:songxiang@joindata.com">宋翔</a>
 * @date 2016年12月2日 下午5:33:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@JoindataComponent(bind = ConfigHub.class, name = "RDBMS ORM 支持")
public @interface EnableMyBatis
{
    /** 要扫描的顶级包，默认是当前包 */
    String[] value() default {};

    /** 自定义 MyBatis 插件 */
    Class<? extends Interceptor>[] plugins() default {};
    
    /** 是否启用驼峰命名转换 */
    boolean autoCamel() default false;
}