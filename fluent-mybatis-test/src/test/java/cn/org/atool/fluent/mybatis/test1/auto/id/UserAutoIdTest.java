package cn.org.atool.fluent.mybatis.test1.auto.id;

import cn.org.atool.fluent.mybatis.generator.ATM;
import cn.org.atool.fluent.mybatis.generator.shared2.entity.StudentEntity;
import cn.org.atool.fluent.mybatis.generator.shared2.mapper.StudentMapper;
import cn.org.atool.fluent.mybatis.test1.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserAutoIdTest extends BaseTest {
    @Autowired
    StudentMapper mapper;

    @Test
    void saveWithId() {
        db.table(ATM.table.student).clean();
        StudentEntity student = new StudentEntity()
            .setId(124L)
            .setUserName("fluent mybatis");
        int count = mapper.insertWithPk(student);
        want.number(count).isEqualTo(1);
        want.number(student.getId()).eq(124L);
        ATM.dataMap.student
            .table(1)
            .id.values(124L)
            .eqTable();
    }

    @Test
    void saveWithoutId() {
        StudentEntity student = new StudentEntity()
            .setUserName("fluent mybatis");
        db.table(ATM.table.student).clean();
        List<Long> ids = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            int count = mapper.insert(student.setId(null));
            want.number(count).isEqualTo(1);
            ids.add(student.getId());
        }
        db.table(ATM.table.student).query().eqByProperties("id", ids);
    }
}
