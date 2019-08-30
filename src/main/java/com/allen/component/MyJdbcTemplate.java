package com.allen.component;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 19:40
 * description :
 *
 * String sql="update user set name=?,money=? where id=?";
   jdbcTemplate.update(sql,new Object[]{"zhh",5,51});

 */
public class MyJdbcTemplate extends AbstractMyJdbcTemplate {


    public MyJdbcTemplate(DataSource dataSource) {
        this.setDataSource(dataSource);

    }

    @Override
    public int update(String sql) throws DataAccessException {

        PreparedStatement ps;
        int ret=0;
        try {

             Connection con=MyDatasourceUtils.getConnection(getDataSource());
             ps = con.prepareStatement(sql);
             ret= ps.executeUpdate();

        } catch (SQLException e) {
            throw new BadSqlGrammarException("",sql,e);
        }

        return ret;

    }

}
