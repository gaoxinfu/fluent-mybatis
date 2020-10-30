package cn.org.atool.fluent.mybatis.entity.generator;

import cn.org.atool.fluent.mybatis.If;
import cn.org.atool.fluent.mybatis.base.impl.BaseQuery;
import cn.org.atool.fluent.mybatis.entity.FluentEntityInfo;
import cn.org.atool.fluent.mybatis.entity.base.AbstractGenerator;
import cn.org.atool.fluent.mybatis.segment.model.ParameterPair;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static cn.org.atool.fluent.mybatis.entity.base.ClassNames.*;
import static cn.org.atool.fluent.mybatis.mapper.FluentConst.Pack_Wrapper;
import static cn.org.atool.fluent.mybatis.mapper.FluentConst.Suffix_Query;

public class QueryGenerator extends AbstractGenerator {

    public static String getClassName(FluentEntityInfo fluentEntityInfo) {
        return fluentEntityInfo.getNoSuffix() + Suffix_Query;
    }

    public static String getPackageName(FluentEntityInfo fluentEntityInfo) {
        return fluentEntityInfo.getPackageName(Pack_Wrapper);
    }

    public QueryGenerator(TypeElement curElement, FluentEntityInfo fluentEntityInfo) {
        super(curElement, fluentEntityInfo);
        this.packageName = getPackageName(fluentEntityInfo);
        this.klassName = getClassName(fluentEntityInfo);
        this.comment = "查询构造";
    }

    @Override
    protected void staticImport(JavaFile.Builder builder) {
        builder.addStaticImport(If.class, "notBlank");
    }

    @Override
    protected void build(TypeSpec.Builder builder) {
        builder.superclass(this.superKlass())
            .addField(this.f_select())
            .addField(this.f_groupBy())
            .addField(this.f_having())
            .addField(this.f_orderBy())
            .addField(this.f_where());
        builder
            .addMethod(this.constructor0())
            .addMethod(this.constructor2_String_Parameter())
            .addMethod(this.constructor1_Parameter())
            .addMethod(this.m_where())
            .addMethod(this.m_primary())
            .addMethod(this.m_allFields());
    }

    private MethodSpec m_allFields() {
        return MethodSpec.methodBuilder("allFields")
            .addModifiers(Modifier.PUBLIC)
            .returns(CN_List_Str)
            .addStatement("return $T.ALL_COLUMNS", mapping(fluent))
            .build();
    }

    /**
     * public final Selector select = new Selector(this);
     *
     * @return
     */
    private FieldSpec f_select() {
        return FieldSpec.builder(selector(fluent),
            "select", Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc("指定查询字段, 默认无需设置")
            .initializer("new Selector(this)")
            .build();
    }

    /**
     * public final GroupBy groupBy = new GroupBy(this);
     *
     * @return
     */
    private FieldSpec f_groupBy() {
        return FieldSpec.builder(groupBy(fluent), "groupBy", Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc("分组：GROUP BY 字段, ...\n")
            .addJavadoc("例: groupBy('id', 'name')")
            .initializer("new GroupBy(this)")
            .build();
    }

    /**
     * public final GroupBy groupBy = new GroupBy(this);
     *
     * @return
     */
    private FieldSpec f_having() {
        return FieldSpec.builder(having(fluent), "having", Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc("分组条件设置 having...")
            .initializer("new Having(this)")
            .build();
    }

    /**
     * public final GroupBy groupBy = new GroupBy(this);
     *
     * @return
     */
    private FieldSpec f_orderBy() {
        return FieldSpec.builder(queryOrderBy(fluent), "orderBy", Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc("排序设置 order by ...")
            .initializer("new QueryOrderBy(this)")
            .build();
    }

    /**
     * public final QueryWhere where = new QueryWhere(this);
     *
     * @return
     */
    private FieldSpec f_where() {
        return FieldSpec.builder(queryWhere(fluent), "where", Modifier.PUBLIC, Modifier.FINAL)
            .initializer("new QueryWhere(this)")
            .addJavadoc("查询条件 where ...")
            .build();
    }

    /**
     * public EntityQuery() {}
     *
     * @return
     */
    private MethodSpec constructor0() {
        return MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addStatement("super($T.Table_Name, $T.class, $T.class)",
                mapping(fluent),
                fluent.className(),
                query(fluent)
            )
            .build();
    }

    /**
     * public XyzQuery(ParameterPair parameters) {}
     *
     * @return
     */
    private MethodSpec constructor1_Parameter() {
        return MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addParameter(ClassName.get(ParameterPair.class), "parameters")
            .addStatement("super($T.Table_Name, parameters, $T.class, $T.class)",
                mapping(fluent),
                fluent.className(),
                query(fluent)
            )
            .build();
    }

    /**
     * public XyzQuery(String alias) {}
     *
     * @return
     */
    private MethodSpec constructor2_String_Parameter() {
        return MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addParameter(ClassName.get(String.class), "alias")
            .addParameter(ClassName.get(ParameterPair.class), "parameters")
            .addStatement("this(parameters)")
            .addStatement("super.alias = alias")
            .build();
    }

    /**
     * public QueryWhere where() {}
     *
     * @return
     */
    private MethodSpec m_where() {
        return MethodSpec.methodBuilder("where")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .returns(queryWhere(fluent))
            .addStatement("return this.where")
            .build();
    }

    private ParameterizedTypeName superKlass() {
        ClassName base = ClassName.get(BaseQuery.class);
        ClassName entity = fluent.className();
        ClassName query = query(fluent);
        return ParameterizedTypeName.get(base, entity, query);
    }

    @Override
    protected boolean isInterface() {
        return false;
    }
}