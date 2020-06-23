package resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import resource.db.JDBCUtilAll;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class CreatBean {
    static String projectName = CreatBean.class.getResource("").getPath()+"generator";
    static String poClass  = "C:\\Users\\Administrator\\IdeaProjects\\app\\code\\src\\main\\java\\com\\test\\code\\dto\\User.java";
    static String table = "test";
    
    static String projectWebName = "";
    static String serverName = "orderCount";
    private static Map<String, Object> data = new SelfCurrentHashMap();

    static {

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
        table=p.getProperty("dbTableName",null);
        data.put("serverName",  p.getProperty("serverName",null));
        data.put("template",  p.getProperty("template",null));
        data.put("outFileName",  p.getProperty("outFileName",null));
        data.put("dbtableName", table);
        data.put("projectPath", projectName);
        poClass= p.getProperty("outFileName",null);
    }
    public static void main(String[] args) throws Exception {
//        String s = "汉字-123-abc";
//        printIT(s.getBytes());
//        printIT(s.getBytes("GBK"));
//        printIT(s.getBytes("ASCII"));
//        printIT(s.getBytes("UTF-8"));
//        printIT(s.getBytes("UnicodeBigUnmarked"));
        //System.getProperty("user.dir");
//        StringUtils.isNotBlank(null);
        System.out.println("org.postgresql.Driver".contains("postgresql")); ;
    }
    @Test
    public void test() throws Exception {
//        System.out.print(URLDecoder.decode(Thread.currentThread().getContextClassLoader().
//                getResource("/").getPath(), "UTF-8"));
       System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println( CreatBean.class.getResource("").getPath());
        System.out.println(this .getClass().getClassLoader().getResource("").getPath());
       // CRUDTemplate.executeQuery(mysql,new BeanHandler<>(Object.class),table);
        System.out.println(        StringUtils.isNotBlank(null));

    }
    @Test
    public void creatColumnCode() throws Exception {
        FreeMarkerUtil util = new FreeMarkerUtil();
        util.init();
        getColumn();
        List<String> list1 = (List<String>) data.get("columnNames");
        Map<String,String> list2 = (Map<String, String>) data.get("conmments");
        List<String> list3 = (List<String>) data.get("columnTypes");
        List<Map<String, Object>> list4 = new ArrayList<Map<String, Object>>();

        if (list1.size() > 0 &&  list1.size() == list3.size()) {
            for (int i = 0; i < list1.size(); i++) {
                Map<String, Object> columns = new HashMap<String, Object>();
                try {
                    columns.put("columnName", list1.get(i));
                    columns.put("columnComment", list2.get(list1.get(i))==null?"":list2.get(list1.get(i)));
                    columns.put("columnType",list3.get(i)==null?"String":list3.get(i));
                    //columns.put("dbcolumnType", list3.get(i).toUpperCase());
                    //columns.put("dbcolumnName", list1.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println (i+":"+e.getMessage()+e.getLocalizedMessage()+e.getCause());
                }
                list4.add(columns);
            }
        }
        data.put("columns", list4);
        util.creatCode(util, data, table);
    }

    @Test
    public void creatDbColumnCode() throws Exception {
        FreeMarkerUtil util = new FreeMarkerUtil();
        util.init();
        JDBCUtilAll jdbc = new JDBCUtilAll();
        String sql="";
        List<Map<String, Object>> dbColumns = new ArrayList<Map<String, Object>>();
       String driverName=JDBCUtilAll.driverName();
        if(driverName.contains("mysql")){
          sql="SELECT COLUMN_NAME, DATA_TYPE,  COLUMN_COMMENT,character_maximum_length  FROM INFORMATION_SCHEMA.COLUMNS where table_name='"+table+"'";
            dbColumns =jdbc.excuteQuery(sql,null);
            for (Map<String, Object> dbc:dbColumns ){
                dbc.put("columnLength",dbc.get("character_maximum_length"));
                dbc.put("columnComment",dbc.get("COLUMN_COMMENT"));
                dbc.put("columnName",VarNameRule.columnToProperty(dbc.get("COLUMN_NAME")==null?"":dbc.get("COLUMN_NAME").toString()));
                dbc.put("columnType",VarNameRule.dbTypeToJavaType(dbc.get("DATA_TYPE")==null?"":dbc.get("DATA_TYPE").toString()));
            }
        }else if(driverName.contains("postgresql")){
            sql="SELECT c.COLUMN_NAME, c.DATA_TYPE,c.numeric_precision," +
                    " (SELECT des.description AS COMMENT " +
                    " FROM pg_attribute AS a, pg_description AS des, pg_class AS pgc " +
                    " WHERE pgc.oid = a.attrelid AND des.objoid = pgc.oid AND pg_table_is_visible(pgc.oid) " +
                    " AND pgc.relname = '"+table+"' and c.COLUMN_NAME=a.attname AND a.attnum = des.objsubid)columnComment" +
                    " from information_schema.columns c WHERE TABLE_NAME='"+table+"';";
            dbColumns =jdbc.excuteQuery(sql,null);
            for (Map<String, Object> dbc:dbColumns ){
                dbc.put("columnLength",dbc.get("numeric_precision"));
                dbc.put("columnComment",dbc.get("columncomment"));
                dbc.put("columnName",VarNameRule.columnToProperty(dbc.get("column_name")==null?"":dbc.get("column_name").toString()));
                dbc.put("columnType",VarNameRule.dbTypeToJavaType(dbc.get("DATA_TYPE")==null?"":dbc.get("DATA_TYPE").toString()));
            }
        }else if(driverName.contains("sqlserver")) {
            sql="SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+table+"';";
            dbColumns =jdbc.excuteQuery(sql,null);
            for (Map<String, Object> dbc:dbColumns ){
                dbc.put("columnLength",dbc.get("character_maximum_length"));
                dbc.put("columnComment",dbc.get("COLUMN_COMMENT"));
                dbc.put("columnName",VarNameRule.columnToProperty(dbc.get("COLUMN_NAME")==null?"":dbc.get("COLUMN_NAME").toString()));
                dbc.put("columnType",VarNameRule.dbTypeToJavaType(dbc.get("DATA_TYPE")==null?"":dbc.get("DATA_TYPE").toString()));
            }
        }
        data.put("columns",dbColumns);
        util.creatCode(util, data, table);
    }

    /**
     * 将一个文件内容写入另外一个
     *
     * @param bufSize
     * @param fcin
     * @param rBuffer
     */
    public static void saveOtherFile(int bufSize, FileChannel fcin, ByteBuffer rBuffer) {
        String enterStr = "\n";
        try {
            byte[] bs = new byte[bufSize];
            StringBuilder strBuf = new StringBuilder("");
            String tempString = null;
            List<String> columlist = new ArrayList<String>();
            Map<String,String>  commentlist = new HashMap<>();
            List<String> commentTypelist = new ArrayList<String>();
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    StringBuffer str = new StringBuffer(line);
                    getColumnsName(columlist, line, str);
                    getCommentName(commentlist, line, str);
                    getColumnType(commentTypelist, line, str);
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
            }
            data.put("columnNames", columlist);
            data.put("conmments", commentlist);
            data.put("columnTypes", commentTypelist);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    private static void getColumnsName(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.lastIndexOf(" ")+1, line.indexOf(";"));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }
    }
    private static void getCommentName(Map<String,String> list, String line, StringBuffer str) {
        if (line.contains("/*") && line.contains("*/\n")) {
            String line2=line;
            line = str.substring(line.lastIndexOf("/*") + 2, line.indexOf("*/"));
            line = line.replaceAll("\r|\n", "");
            list.put(line2,line);
        }
    }
    private static void getColumnType(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.indexOf("private ") + 8, line.lastIndexOf(" "));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }

    }

    private void getColumn() throws FileNotFoundException {
        int _5M = 1024 * 1024 * 50;
        File target = new File(poClass );
        FileChannel fcin = new RandomAccessFile(target, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(_5M);
        saveOtherFile(_5M, fcin, rBuffer);
    }



}