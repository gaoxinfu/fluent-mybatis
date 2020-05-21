package cn.org.atool.fluent.mybatis.test.basedao;

import cn.org.atool.fluent.mybatis.demo.generate.datamap.EM;
import cn.org.atool.fluent.mybatis.demo.generate.datamap.TM;
import cn.org.atool.fluent.mybatis.demo.notgen.UserExtDao;
import cn.org.atool.fluent.mybatis.demo.generate.datamap.entity.UserEntityMap;
import cn.org.atool.fluent.mybatis.demo.generate.datamap.table.UserTableMap;
import cn.org.atool.fluent.mybatis.demo.generate.entity.UserEntity;
import cn.org.atool.fluent.mybatis.demo.generate.mapping.UserMP;
import cn.org.atool.fluent.mybatis.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author darui.wu
 * @create 2019/10/29 9:33 下午
 */
public class SelectByMapTest extends BaseTest {
    @Autowired
    private UserExtDao dao;

    @Test
    public void test_selectByMap() throws Exception {
        db.table(t_user).clean()
                .insert(TM.user.createWithInit(10)
                        .user_name.values(DataGenerator.increase("username_%d"))
                );

        List<UserEntity> users = dao.selectByMap(new HashMap<String, Object>() {
            {
                this.put(UserMP.Column.user_name, "username_4");
            }
        });
        db.sqlList().wantFirstSql().start("SELECT").end("FROM t_user WHERE (user_name = ?)");
        want.list(users).eqDataMap(EM.user.create(1)
                .userName.values("username_4"));
    }
}