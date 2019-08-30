package com.allen.component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 19:27
 * description :
 */
public class MyDatasourceUtils {

    private  static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();


    public static Connection  getConnection(DataSource datasource) throws SQLException {
        Connection con= threadLocal.get();
        if (con==null)
        {
            con=datasource.getConnection();
            threadLocal.set(con);
        }
        return con;

    }
}
