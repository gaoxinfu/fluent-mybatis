package cn.org.atool.fluent.mybatis.segment;

import cn.org.atool.fluent.mybatis.base.crud.IWrapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.segment.fragment.Column;

/**
 * BaseApply
 *
 * @param <SEGMENT> BaseApply子类
 * @param <W>       查询（更新）器
 * @author darui.wu
 */
@SuppressWarnings({"rawtypes", "unused"})
public abstract class BaseApply<
    SEGMENT extends BaseSegment,
    W extends IWrapper<?, W, ?>
    > {

    public final SEGMENT segment;

    /**
     * 当前被操作的字段
     *
     * @return FieldMapping
     */
    public FieldMapping current() {
        return this.segment.current;
    }

    /**
     * 当前被操作的字段
     *
     * @return Column
     */
    public Column column() {
        return Column.set(this.segment.wrapper, this.segment.current);
    }

    BaseApply(SEGMENT segment) {
        this.segment = segment;
    }
}