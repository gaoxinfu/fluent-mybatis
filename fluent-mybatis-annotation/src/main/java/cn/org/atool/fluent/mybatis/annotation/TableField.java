package cn.org.atool.fluent.mybatis.annotation;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.annotation.*;

/**
 * 表字段标识
 *
 * @author darui.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableField {
    /**
     * 数据库字段名称
     * 默认采用驼峰命名转换为下划线命名
     */
    String value() default "";

    /**
     * 字段 update set 默认值
     */
    String update() default "";

    /**
     * insert的时候默认值
     */
    String insert() default "";

    /**
     * 是否非大字段
     * <p>使用 select(true, FieldMeta::notLarge)排除大字段查询</p>
     */
    boolean notLarge() default true;

    /**
     * JDBC类型
     */
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * 类型处理器 (该默认值不代表会按照该值生效)
     */
    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    /**
     * 指定小数点后保留的位数
     */
    String numericScale() default "";

    /**
     * 字段描述
     */
    String desc() default "";
}