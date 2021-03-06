package com.joindata.inf.common.support.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.joindata.inf.common.basic.annotation.JoindataComponent;
import com.joindata.inf.common.support.mybatis.support.MapperHelperProps;

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

    /** 是否启用驼峰命名转换 */
    boolean autoCamel() default false;

    /** 默认数据源（用于多数据源路由） */
    String defaultDatasource() default "";

    /** 额外添加的 Mapper */
    Class<?>[] mappers() default {};

    /** 通用 Mapper 参数 */
    MapperHelperProps mapperHelperProps() default @MapperHelperProps;
}