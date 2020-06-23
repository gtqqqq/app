package resource.db;

import org.apache.commons.beanutils.BeanMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 对jdbc的完整封装
 *
 */
public class JDBCUtilAll {

    public static  String driver = null;
    private static  String url = null;
    private static  String username = null;
    private static  String password = null;

    private CallableStatement callableStatement = null;//创建CallableStatement对象
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    public static String driverName() {
    //创建Properties对象
       Properties p = new Properties();
                //获取文件输入流
        InputStream in = null;
       try {
        in = new FileInputStream(JDBCUtilAll.class.getResource("").getPath()+"db.properties");
        //加载输入流
        p.load(in);
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

       return p.getProperty("driverClassName",null);
    }

    /**
     * 建立数据库连接  
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            try {
                //创建Properties对象
                Properties p = new Properties();
                //获取文件输入流
                InputStream in = null;
                try {
                    in = new FileInputStream(JDBCUtilAll.class.getResource("").getPath()+"db.properties");
                    //加载输入流
                    p.load(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //获取数据库连接驱动名字
                driver = p.getProperty("driverClassName",null);
                //获取数据库连接地址
                url = p.getProperty("url",null);
                //获取数据库连接用户名
                username = p.getProperty("username",null);
                //获取数据库连接密码
                password = p.getProperty("password",null);
                if(driver != null && url != null
                        && username != null && password != null){
                    //加载驱动
                    Class.forName(driver);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // 获取连接    
            conn = DriverManager.getConnection(url, username,
                    password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * insert update delete SQL语句的执行的统一方法  
     * @param sql SQL语句  
     * @param params 参数数组，若没有参数则为null  
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object[] params) {
        // 受影响的行数    
        int affectedLine = 0;

        try {
            // 获得连接    
            conn = this.getConnection();
            // 调用SQL     
            pst = conn.prepareStatement(sql);

            // 参数赋值    
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }    
            /*在此 PreparedStatement 对象中执行 SQL 语句，
                                          该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，比如 INSERT、UPDATE 或 DELETE 
                                          语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。    */
            // 执行    
            affectedLine = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源    
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中  
     * @param sql SQL语句  
     * @param params 参数数组，若没有参数则为null  
     * @return 结果集
     */
    private ResultSet executeQueryRS(String sql, Object[] params) {
        try {
            // 获得连接    
            conn = this.getConnection();

            // 调用SQL    
            pst = conn.prepareStatement(sql);

            // 参数赋值    
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            // 执行    
            rst = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rst;
    }

    /**
     * SQL 查询将查询结果：一行一列  
     * @param sql SQL语句  
     * @param params 参数数组，若没有参数则为null  
     * @return 结果集
     */
    public Object executeQuerySingle(String sql, Object[] params) {
        Object object = null;
        try {
            // 获得连接    
            conn = this.getConnection();

            // 调用SQL    
            pst = conn.prepareStatement(sql);

            // 参数赋值    
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            // 执行    
            rst = pst.executeQuery();

            if(rst.next()) {
                object = rst.getObject(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中  
     *
     * @param sql  SQL语句
     *         params  参数，没有则为null
     * @return List
     *                       结果集  
     */
    public List<Map<String, Object>> excuteQuery(String sql, Object[] params) {
        // 执行SQL获得结果集    
        ResultSet rs = executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象    
        ResultSetMetaData rsmd = null;

        // 结果集列数    
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();

            // 获得结果集列数    
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        // 创建List    
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            // 将ResultSet的结果保存到List中    
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);//每一个map代表一条记录，把所有记录存在list中    
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭所有资源    
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法  
     * @param sql 存储过程语句  
     * @param params 参数数组  
     * @param outParamPos 输出参数位置  
     * @param SqlType 输出参数类型  
     * @return 输出参数的值
     */
    public Object excuteQuery(String sql, Object[] params,int outParamPos, int SqlType) {
        Object object = null;
        conn = this.getConnection();
        try {
            // 调用存储过程    
            // prepareCall:创建一个 CallableStatement 对象来调用数据库存储过程。
            callableStatement = conn.prepareCall(sql);

            // 给参数赋值    
            if(params != null) {
                for(int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }

            // 注册输出参数    
            callableStatement.registerOutParameter(outParamPos, SqlType);

            // 执行    
            callableStatement.execute();

            // 得到输出参数    
            object = callableStatement.getObject(outParamPos);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源    
            closeAll();
        }

        return object;
    }

    /**
     * 关闭所有资源  
     */
    private void closeAll() {
        // 关闭结果集对象    
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭PreparedStatement对象    
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象    
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭Connection 对象    
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}