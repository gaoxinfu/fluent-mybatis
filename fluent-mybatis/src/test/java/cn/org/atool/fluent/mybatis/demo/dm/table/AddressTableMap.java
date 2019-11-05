package cn.org.atool.fluent.mybatis.demo.dm.table;

import cn.org.atool.fluent.mybatis.demo.mapping.AddressMP;
import cn.org.atool.fluent.mybatis.annotation.ColumnDef;
import com.baomidou.mybatisplus.annotation.TableName;
import org.test4j.module.ICore.DataGenerator;
import org.test4j.module.ICore.DataMap;
import org.test4j.tools.datagen.KeyValue;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @ClassName AddressTableMap
 * @Description AddressTableMap
 *
 * @author generate code
 */
@TableName(AddressMP.Table_Name)
public class AddressTableMap extends DataMap<AddressTableMap> {
    /**
     * 设置address对象id字段值
     */
    @ColumnDef(type = "bigint(21) unsigned", primary = true)
    public transient final KeyValue<AddressTableMap> id = new KeyValue(this, AddressMP.Column.id);
    /**
     * 设置address对象address字段值
     */
    @ColumnDef(type = "varchar(45)")
    public transient final KeyValue<AddressTableMap> address = new KeyValue(this, AddressMP.Column.address);
    /**
     * 设置address对象is_deleted字段值
     */
    @ColumnDef(type = "tinyint(2)")
    public transient final KeyValue<AddressTableMap> is_deleted = new KeyValue(this, AddressMP.Column.is_deleted);
    /**
     * 设置address对象gmt_created字段值
     */
    @ColumnDef(type = "datetime")
    public transient final KeyValue<AddressTableMap> gmt_created = new KeyValue(this, AddressMP.Column.gmt_created);
    /**
     * 设置address对象gmt_modified字段值
     */
    @ColumnDef(type = "datetime")
    public transient final KeyValue<AddressTableMap> gmt_modified = new KeyValue(this, AddressMP.Column.gmt_modified);

    public AddressTableMap(){
        super();
    }

    public AddressTableMap(int size){
        super(size);
    }

    public AddressTableMap init(){
        this.id.values(DataGenerator.increase(1, 1));
        this.is_deleted.values(false);
        this.gmt_created.values(new Date());
        this.gmt_modified.values(new Date());
        return this;
    }

    public AddressTableMap with(Consumer<AddressTableMap> init){
        init.accept(this);
        return this;
    }

    public static AddressTableMap create(){
        return new AddressTableMap();
    }

    public static AddressTableMap create(int size){
        return new AddressTableMap(size);
    }
}