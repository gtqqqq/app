package resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TestNio {
    static String projectName = "C:\\Users\\Administrator\\IdeaProjects\\app\\test\\src\\main\\resources\\generator";
    static String sqlFile = "C:\\Users\\Administrator\\IdeaProjects\\app\\test\\src\\main\\resources\\generator\\media.sql";
    static String table = "media";
    
    static String projectWebName = "";
    static String serverName = "orderCount";
    private static Map<String, Object> data = new SelfCurrentHashMap();

    static {
        data.put("location", "");
        data.put("projectPath", projectName);
        data.put("serverName", serverName);
        data.put("tableVar", table);
        data.put("dbtableName", table);
        data.put("tableName", table.substring(0, 1).toUpperCase() + table.substring(1, table.length()));
        data.put("columns", new String[]{"id"});
        data.put("methods", new String[]{
                "",
                //"Map-Map",
                "List_Map-Map",
                //"update-Map",
                //"HashMap-Page-PaginationRequest",
                ""});
    }

    @Test
    public void test() throws Exception {
//        System.out.print(URLDecoder.decode(Thread.currentThread().getContextClassLoader().
//                getResource("/").getPath(), "UTF-8"));
//        System.out.print(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
        System.out.print( TestNio.class.getResource("").getPath());
        System.out.print(this .getClass().getClassLoader().getResource("").getPath());
    }
    @Test
    public void creatColumnCode() throws Exception {
        FreeMarkerUtil util = new FreeMarkerUtil();
        util.init();
        getColumn();
        List<String> list1 = (List<String>) data.get("columnNames");
        Map<String,String> list2 = (Map<String, String>) data.get("conmments");
        List<String> list3 = (List<String>) data.get("columnTypes");
        List<Map<String, String>> list4 = new ArrayList<Map<String, String>>();

        if (list1.size() > 0 &&  list1.size() == list3.size()) {
            System.out.println(list1.size() + "-" + list2.size() + "-" + list3.size());

            for (int i = 0; i < list1.size(); i++) {
                Map<String, String> columns = new HashMap<String, String>();
                try {
                    columns.put("columnName", VarNameRule.columnToProperty(list1.get(i)));
                    columns.put("dbcolumnName", list1.get(i));
                    columns.put("columnComment", list2.get(list1.get(i))==null?"":list2.get(list1.get(i)));
                    columns.put("dbcolumnType", list3.get(i).toUpperCase());
                    columns.put("columnType", VarNameRule.postgredbTypeToJavaType(list3.get(i))==null?"String":VarNameRule.postgredbTypeToJavaType(list3.get(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println (i+":"+e.getMessage()+e.getLocalizedMessage()+e.getCause());
                }
                list4.add(columns);
            }
        }
        data.put("daoPackageName","com.runlin.mapper");
        data.put("poPackageName","com.runlin.entity.po");
        data.put("columns", list4);
        util.creatCode(util, data, data.get("tableName").toString());
        //util.process2(util, data, data.get("tableName").toString());
    }
    public static void main(String[] args) throws Exception {
//        String s = "汉字-123-abc";
//        printIT(s.getBytes());
//        printIT(s.getBytes("GBK"));
//        printIT(s.getBytes("ASCII"));
//        printIT(s.getBytes("UTF-8"));
//        printIT(s.getBytes("UnicodeBigUnmarked"));
    }

    static void printIT(byte[] theByte) {
        for (byte b : theByte) {
            System.out.print(b);
            System.out.print(" ");
        }
        System.out.println();
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
//		    System.out.println(">>>>>>>>>>>>>>>>>>" + line);
                    getPostgreColumnsName(columlist, line, str);
                    getPostgreCommentName(commentlist, line, str);
                    getPostgreColumnType(commentTypelist, line, str);
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
        if (line.contains(" as ")) {
            line = str.substring(line.indexOf(" as ") + 4, line.length());
            line = line.replaceAll("\r|\n", "");
            line = line.replaceAll(",", "");
            list.add(line);
        }
    }

    private static void getColumnsName2(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.lastIndexOf(" "), line.indexOf(";"));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }
        if (line.contains(" NULL ") || line.contains("DEFAULT '") && !line.contains("PRIMARY KEY")) {
            line = line.split(" ")[2];
            line = line.replaceAll("\r|\n", "");
            line = line.replaceAll("`|\"", "");
            list.add(line);
        }
    }
    private static void getPostgreColumnsName(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.lastIndexOf(" "), line.indexOf(";"));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }
        if (line.contains("  \"")) {
            line = line.split(" ")[2];
            line = line.replaceAll("\r|\n", "");
            line = line.replaceAll("`|\"", "");
            list.add(line);
        }
    }

    private static void getCommentName(List<String> list, String line, StringBuffer str) {
        if (line.contains("/*") && line.contains("*/\n")) {
            line = str.substring(line.lastIndexOf("/*") + 2, line.indexOf("*/"));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }
        if (line.contains(" NULL ") ) {
            line = str.substring(line.indexOf("COMMENT") < 0 ? 0 : line.indexOf("COMMENT") + 9, line.indexOf("',") < 0 ? 0 : line.indexOf("',"));
            line = line.replaceAll("\r|\n", "");
            System.out.println(line.indexOf("COMMENT") < 0 ? "" : line);
            list.add(str.indexOf("COMMENT") < 0 ? "" : line);
        }
    }
    private static void getPostgreCommentName(Map<String,String> list, String line, StringBuffer str) {

        if (line.contains("COMMENT ON COLUMN") ) {
            String line2=line;
            line2 = str.substring(line2.lastIndexOf(".\"") < 0 ? 0 : line2.lastIndexOf(".\"")+2, line2.lastIndexOf("\"") < 0 ? 0 : line2.lastIndexOf("\""));
            line = str.substring(line.indexOf("'") < 0 ? 0 : line.indexOf("'")+1, line.lastIndexOf("'") < 0 ? 0 : line.lastIndexOf("'"));
            line = line.replaceAll("\r|\n", "");
            System.out.println(line.indexOf("'") < 0 ? "" : line);
            list.put(line2,line);
        }
    }

    private static void getColumnType(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.indexOf("private ") + 8, line.lastIndexOf(" "));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }

        if (line.contains(" NULL ") || line.contains("DEFAULT '") && !line.contains("PRIMARY KEY")) {
            String s = line.split(" ")[3];
            line = s.substring(0, s.indexOf("(") < 0 ? s.length() : s.indexOf("("));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + line);
            line = line.replaceAll("\r|\n", "");
            list.add(line == null?"":line);
        }
    }

    private static void getPostgreColumnType(List<String> list, String line, StringBuffer str) {
        if (line.contains("private") && !line.contains("static")) {
            line = str.substring(line.indexOf("private ") + 8, line.lastIndexOf(" "));
            line = line.replaceAll("\r|\n", "");
            list.add(line);
        }

        if (line.contains("  \"")) {
            String s = line.split(" ")[3];
            line = s.substring(0, s.indexOf("(") < 0 ? s.length() : s.indexOf("("));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + line);
            line = line.replaceAll("\r|\n|,", "");
            list.add(line == null?"":line);
        }
    }
    /**
     * 读文件写入数据库
     *
     * @param bufSize
     * @param fcin
     * @param rBuffer
     */
    public static void readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer) {
        Connection conn = null;
        Statement pst = null;
        String enterStr = "\n";
        try {
            byte[] bs = new byte[bufSize];
            StringBuilder strBuf = new StringBuilder("");
            String tempString = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/doctor?useUnicode=true&characterEncoding=utf-8", "root", "root");
            pst = conn.createStatement();
            while (fcin.read(rBuffer) != -1) {

                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex = 0;
                int i = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                    pst.addBatch(line);
            /*
		     * System.out.println("-----------------------");
		     * System.out.println(line);
		     * System.out.println("-----------------------");
		     */
                    if (i % 100 == 0) {
                        System.out.println("执行了：" + i);
                        if (i == 2700) {
                            System.out.println("导了：" + i);
                        }
                        int[] flag = pst.executeBatch();
                        System.out.println("结果：" + flag[0]);
                    }
                    i += 1;
                }
                // 执行批量更新
                pst.executeBatch();

                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
                // System.out.println(strBuf.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//    @Test
//    public void test11() throws Exception {
//	RandomAccessFile raf = new RandomAccessFile(
//		"F:/workspace/shop-notify-impl/src/main/java/cn/com/fan1080/shop/notify/dao/SalesSmsStatisticsDao.java",
//		"rw");
//	raf.seek(raf.length() - 11);
//	raf.write("\r\n中国移动阅读基地".getBytes());
//	raf.close();
//
//    }

    public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer, String line) throws IOException {
        try {
            StringBuffer str = new StringBuffer(line);

            if (line.contains(" as ")) {
                line = str.substring(line.indexOf(" as ") + 4, line.length());
                line = line.replace(",", "");
                System.out.println(line);
                wBuffer.clear();
                wBuffer.put(line.getBytes());
                wBuffer.flip();
                while (wBuffer.hasRemaining()) {
                    fcout.write(wBuffer);

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }

    }

    @Test
    public void creatCode() throws Exception {
//	FreeMarkerUtil util = new FreeMarkerUtil();
//	util.init();
//	data.put("project", projectName);
//	data.put("packPath", "src/main/java/cn/com/fan1080/shop/");
//	data.put("resourcesPath", "src/main/resources/");
//	util.processApi(util, data, data.get("tableName").toString());
        System.out.println(VarNameRule.columnToProperty("biz_voyage_info"));
    }


//    @Test
//    public void cratecode() throws Exception {
//	FreeMarkerUtil util = new FreeMarkerUtil();
//	int _5M = 1024 * 1024 * 50;
//	File fin = new File("E:\\OrderWholeDetailInfoMapper.xml");
//	File fout = new File("e:\\mm.sql");
//	FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
//	ByteBuffer rBuffer = ByteBuffer.allocate(_5M);
//	FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
//	ByteBuffer wBuffer = ByteBuffer.allocateDirect(_5M);
//	saveOtherFile(_5M, fcin, rBuffer, fcout, wBuffer);
//	util.init();
//
//	String table = "areaTradeStatics";
//	String projectName = "f:/shop-project-impl/";
//	data.put("tableVar", table);
//	data.put("project", projectName);
//	data.put("packPath", "");
//	data.put("tableName", table.substring(0, 1).toUpperCase() + table.substring(1, table.length()));
//	util.process3(util, data, data.get("tableName").toString());
//
//    }



    private void getColumn() throws FileNotFoundException {
        int _5M = 1024 * 1024 * 50;
        File fin = new File(sqlFile);
        FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(_5M);
        saveOtherFile(_5M, fcin, rBuffer);
    }

    @Test
    public void testCharset() throws Exception {
        System.getProperties().list(System.out);
    }

//    @Test
//    public void t3() throws Exception {
//        String[] projectName = new String[]
//                {null,
//                        "goods",
//                        "member",
//                        "notify",
//                        "order",
//                        "seller",
//                        "shequyy",
//                        "system",
//                        "canyinyy",
//                        null
//                };
//        for (String name : projectName) {
//            if (name != null)
//                //   modifyConfig(name);
//                modifyDBConfig(name);
//        }
//
//    }

//    private void modifyConfig(String project) throws IOException {
//        String path = "E:/workspace/shop-" + project + "-impl/target/shop-" + project + "-impl-0.1.0-SNAPSHOT.jar";
////	String path = "D:/repository/cn/com/fan1080/shop/shop-"+project+"-impl/0.1.0-SNAPSHOT/shop-"+project+"-impl-0.1.0-SNAPSHOT.jar";
//        JarTool jar = new JarTool();
//        jar.change(path, "conf/shop-" + project + "-service-config.xml", "cn.com.fan1080.shop." + project + ".util.SqlSessionFactoryBean1");
//    }

//    private void modifyDBConfig(String project) throws IOException {
//        String path = "E:/workspace/shop-" + project + "-impl/target/classes/conf/shop-" + project + "-service-config.xml";
////   	String path = "D:/repository/cn/com/fan1080/shop/shop-"+project+"-impl/0.1.0-SNAPSHOT/shop-"+project+"-impl-0.1.0-SNAPSHOT.jar";
//        JarTool jar = new JarTool();
//        // 	jar.upateDBConcfgFile(path,"cn.com.fan1080.shop."+project+".util.SqlSessionFactoryBean1");
//    }

    private String getMethod(String line, StringBuffer str) {
        if (line.contains("select id=\"")) {
            line = str.substring(line.indexOf("select id=\""), line.length());
            line = line.substring(0, line.indexOf("\""));
            return line;
        }
        return null;
    }

    @Test
    public void testName() throws Exception {

    }


}