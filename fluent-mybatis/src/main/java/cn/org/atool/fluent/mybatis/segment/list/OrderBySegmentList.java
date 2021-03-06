package cn.org.atool.fluent.mybatis.segment.list;

import cn.org.atool.fluent.mybatis.If;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.fragment.KeyFrag;

import static cn.org.atool.fluent.mybatis.segment.fragment.KeyFrag.ORDER_BY;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.COMMA_SPACE;

/**
 * Order By SQL 片段
 *
 * @author darui.wu
 */
public class OrderBySegmentList extends BaseSegmentList {
    public OrderBySegmentList() {
        super.segments.setDelimiter(COMMA_SPACE).setFilter(If::notBlank);
    }

    @Override
    public BaseSegmentList add(KeyFrag keyword, IFragment... sqlSegments) {
        super.segments.add(sqlSegments);
        return this;
    }

    /**
     * 示例: order by column1 asc, column2 desc
     *
     * @return sql
     */
    @Override
    public IFragment get() {
        return super.merge(ORDER_BY);
    }
}