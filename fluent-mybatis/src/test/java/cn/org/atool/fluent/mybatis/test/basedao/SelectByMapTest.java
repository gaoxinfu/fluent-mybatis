package cn.org.atool.fluent.mybatis.test.basedao;

import cn.org.atool.fluent.mybatis.demo.dao.intf.UserDao;
import cn.org.atool.fluent.mybatis.demo.dm.entity.UserEntityMap;
import cn.org.atool.fluent.mybatis.demo.dm.table.UserTableMap;
import cn.org.atool.fluent.mybatis.demo.entity.UserEntity;
import cn.org.atool.fluent.mybatis.demo.mapping.UserMP;
import cn.org.atool.fluent.mybatis.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author darui.wu
 * @create 2019/10/29 9:33 下午
 */
public class SelectByMapTest extends BaseTest {
    @Autowired
    private UserDao dao;

    @Test
    public void test_selectByMap() throws Exception {
        db.table(t_user).clean().insert(new UserTableMap(10).init()
                .user_name.values(DataGenerator.increase("username_%d")));

        List<UserEntity> users = dao.selectByMap(new HashMap<String, Object>() {
            {
                this.put(UserMP.Column.user_name, "username_4");
            }
        });
        db.sqlList().wantFirstSql().start("SELECT").end("FROM t_user WHERE user_name = ?");
        want.list(users).eqDataMap(new UserEntityMap(1)
                .userName.values("username_4"));
    }
}