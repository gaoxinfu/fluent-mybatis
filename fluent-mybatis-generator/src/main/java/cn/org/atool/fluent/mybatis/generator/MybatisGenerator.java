package cn.org.atool.fluent.mybatis.generator;

import cn.org.atool.fluent.mybatis.generator.template.dao.BaseDaoTemplate;
import cn.org.atool.fluent.mybatis.generator.template.dao.DaoImplTemplate;
import cn.org.atool.fluent.mybatis.generator.template.dao.DaoIntfTemplate;
import cn.org.atool.fluent.mybatis.generator.template.entity.EntityTemplate;
import cn.org.atool.fluent.mybatis.generator.template.helper.EntityHelperTemplate;
import cn.org.atool.fluent.mybatis.generator.template.helper.EntityWrapperHelperTemplate;
import cn.org.atool.fluent.mybatis.generator.template.helper.MappingTemplate;
import cn.org.atool.fluent.mybatis.generator.template.mapper.MapperTemplate;
import cn.org.atool.fluent.mybatis.generator.template.mapper.SharingTemplate;
import cn.org.atool.fluent.mybatis.generator.template.wrapper.EntityQueryTemplate;
import cn.org.atool.fluent.mybatis.generator.template.wrapper.EntityUpdateTemplate;
import lombok.extern.slf4j.Slf4j;
import org.test4j.generator.mybatis.DataMapGenerator;
import org.test4j.generator.mybatis.config.IGlobalConfig;
import org.test4j.generator.mybatis.template.BaseTemplate;
import org.test4j.generator.mybatis.template.DataMapTemplateList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库表列表
 *
 * @author wudarui
 */
@Slf4j
public class MybatisGenerator extends DataMapGenerator {
    private MybatisGenerator() {
    }

    /**
     * 生成fluent mybatis文件
     *
     * @return
     */
    public static IGlobalConfig build() {
        return new MybatisGenerator();
    }

    static List<BaseTemplate> list = new ArrayList<>();

    static {
        list.addAll(Arrays.asList(
            new MappingTemplate(),
            new EntityTemplate(),
            new EntityHelperTemplate(),
            new MapperTemplate(),
            new SharingTemplate(),
            new EntityWrapperHelperTemplate(),
            new EntityQueryTemplate(),
            new EntityUpdateTemplate(),
            new BaseDaoTemplate(),
            new DaoIntfTemplate(),
            new DaoImplTemplate()
        ));
        list.addAll(DataMapTemplateList.datamap_list);
    }

    @Override
    protected List<BaseTemplate> getAllTemplates() {
        return list;
    }
}