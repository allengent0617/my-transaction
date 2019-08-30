package com.allen.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author : allengent@163.com
 * @date : 2019/8/29 19:37
 * description :
 */

@Component
@Scope("prototype")//设置成原型状态，避免线程安全问题
@Slf4j
public class MyTransactionManager {


    private  ThreadLocal<Connection> threadLocal=new ThreadLocal<>();

    @Autowired
    private DataSource  dataSource;


    public void begin() throws SQLException {
        log.info("方法上有事务注解，使用手动的方式开启事务...");

        Connection  con= MyDatasourceUtils.getConnection(dataSource);
        con.setAutoCommit(false);
    }

    public void commit() throws SQLException  {
        log.info("提交事务。。。");
        Connection  con= MyDatasourceUtils.getConnection(dataSource);
        con.commit();
    }

    public void rollback() throws SQLException{
        log.info("回滚事务。。。");
        Connection  con= MyDatasourceUtils.getConnection(dataSource);
        if (!con.getAutoCommit())
             con.rollback();
    }
}
