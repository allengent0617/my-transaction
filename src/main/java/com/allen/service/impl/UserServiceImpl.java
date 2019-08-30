package com.allen.service.impl;

import com.allen.annotaion.MyTransactional;
import com.allen.component.MyJdbcTemplate;
import com.allen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 22:39
 * description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MyJdbcTemplate  myJdbcTemplate;

    @Override
    public void test1() {
         myJdbcTemplate.update("insert into user(id,name,age,money,created_at) values(5,'zhangsan',29,100.23,now())");

         //test duplicate id  ,insert failed , record 1 do not rollback
         myJdbcTemplate.update("insert into user(id,name,age,money,created_at) values(5,'zhangsan2',29,100.23,now())");

         //执行完成后 ，在数据 中 执行 SELECT * FROM `user`; 发现第1条记录没有回滚，因为没有添加事务注解  @MyTransactional
    }

    @Override
    @MyTransactional
    public void test2() {
        myJdbcTemplate.update("insert into user(id,name,age,money,created_at) values(6,'zhangsan',29,100.23,now())");

        //test duplicate id  ,insert failed , record 1  rollback
        myJdbcTemplate.update("insert into user(id,name,age,money,created_at) values(6,'zhangsan2',29,100.23,now())");


        //执行完成后 ，在数据 中 执行 SELECT * FROM `user`; 发现第1条记录回滚了，因为添加了事务注解  @MyTransactional
    }



}
