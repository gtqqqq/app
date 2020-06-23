package resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FreeMarkerUtil {

    private Configuration cfg;

    public static void main(String[] args) throws Exception {
        // FreeMarkerUtil hf = new FreeMarkerUtil();
        // hf.init();
        // hf.process(hf);
        String tab = "fan-web-admin";
        int i = tab.indexOf("web-");
        String in = "";
        System.out.println(tab.replace(tab.substring(i, i + 2),
                tab.substring(i + 1, i + 2).toUpperCase()));
        System.out.println();
    }


    public void init() throws Exception {
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration();
        // 设置FreeMarker的模版文件位置

        cfg.setDirectoryForTemplateLoading(new File(
                CreatBean.class.getResource("").getPath()+"tempLate"));
        cfg.setEncoding(Locale.CHINA, "utf-8");

    }

    @SuppressWarnings("unchecked")
    public void creatCode(FreeMarkerUtil hf, Map root,
                          String table) throws Exception {
        root.put("tableVar",table.substring(0,1).toLowerCase()+table.substring(1));
        root.put("date", new Date().toString());
        String projectPath = root.get("projectPath").toString();
        String fileName = root.get("outFileName").toString();;
        String savePath = "/";
        Template template = cfg.getTemplate(root.get("template").toString(), Locale.CHINA);
        root.put("tableName", table);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);

    }
    /**
     * 首字母大写
     *
     * @param string
     * @return
     */
    public static String toUpperCase4Index(String string) {
        char[] methodName = string.toCharArray();
        methodName[0] = toUpperCase(methodName[0]);
        return String.valueOf(methodName);
    }

    /**
     * 字符转成大写
     *
     * @param chars
     * @return
     */
    public static char toUpperCase(char chars) {
        if (97 <= chars && chars <= 122) {
            chars ^= 32;
        }
        return chars;
    }


    public void buildTemplate(Map root, String projectPath, String savePath,
                              String fileName, Template template) {
        String realFileName = projectPath + savePath + fileName;
        String realSavePath = projectPath + savePath;
        System.out.println(realFileName);
        System.out.println(realSavePath);
        File newsDir = new File(realSavePath);
        File file = new File(realFileName);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        file.delete();
        if (!file.exists()) {
        	 root.put("fileExist", false);
            try {
                // SYSTEM_ENCODING = "UTF-8";
                Writer out = new OutputStreamWriter(new FileOutputStream(
                        realFileName), "utf-8");

                template.process(root, out);
                cfg.clearTemplateCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void buildTemplate2(Map root, String projectPath, String savePath, String fileName, Template template)
            throws IOException {
        String realFileName = projectPath + savePath + fileName;
        String realSavePath = projectPath + "/" + savePath;
        System.out.println(realFileName);
        System.out.println(realSavePath);
        File newsDir = new File(realSavePath);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        File file = new File(realFileName);
        if (file.exists()) {
            root.put("fileExist", true);
            try {
                //创建临时文件
                // SYSTEM_ENCODING = "UTF-8";
                Writer out = new OutputStreamWriter(new FileOutputStream(realFileName + ".tmp"), "utf-8");
                template.process(root, out);
                cfg.clearTemplateCache();
                out.flush();
                out.close();
                //读取临时文件
                RandomAccessFile tmprf = new RandomAccessFile(realFileName + ".tmp", "rw");
                StringBuilder tmpsb = new StringBuilder("");
                String tmpline = null;
                while ((tmpline = tmprf.readLine()) != null) {
                    // System.out.println(line);
                    // System.out.println(new String(tmpline.getBytes("ISO-8859-1"), "gbk"));
                    tmpsb.append(new String(tmpline.getBytes("ISO-8859-1"), "gbk") + "\r\n");
                }
                tmprf.close();

                RandomAccessFile rf = new RandomAccessFile(realFileName, "rw");
                StringBuilder sb = new StringBuilder("");
                String line = null;
                while ((line = rf.readLine()) != null) {
                    // System.out.println(line);
                    // System.err.println(EncodingUtils.getString(line.getBytes("ISO-8859-1"), "UTF-8"));
                    sb.append(new String(line.getBytes("ISO-8859-1"), "UTF-8") + "\r\n");
                }

                Writer writer = new FileWriter(file);
                if (file.getName().indexOf(".xml") > 0) {
                    writer.write(sb.substring(0, sb.lastIndexOf("</mapper>") - 1));
                } else {
                    writer.write(sb.substring(0, sb.lastIndexOf("}") - 1));
                }
                //将临时文件写入当前文件
                writer.write(tmpsb.toString());
                if (file.getName().indexOf(".xml") > 0) {
                    writer.write("</mapper>");
                } else {
                    writer.write("}");
                }
                writer.flush();
                writer.close();
                rf.close();
                while (true) {
                    File tmpfile = new File(realFileName + ".tmp");
                    boolean flag = tmpfile.exists();
                    if (flag) {
                        tmpfile.delete();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            root.put("fileExist", false);
            try {
                // SYSTEM_ENCODING = "UTF-8";
                Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "utf-8");

                template.process(root, out);
                cfg.clearTemplateCache();
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //替换文件中TODO部分
    public void buildTemplateTODO(Map root, String projectPath, String savePath, String fileName, Template template)
            throws IOException {
        String realFileName = projectPath + savePath + fileName;
        String realSavePath = projectPath + "/" + savePath;
        System.out.println(realFileName);
        System.out.println(realSavePath);
        File newsDir = new File(realSavePath);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        File lastOutFile = new File(realFileName);
        if (lastOutFile.exists()) {
            root.put("fileExist", true);
            try {
                //创建临时文件
                // SYSTEM_ENCODING = "UTF-8";
                Writer out = new OutputStreamWriter(new FileOutputStream(realFileName + ".tmp"), "utf-8");
                template.process(root, out);
                cfg.clearTemplateCache();
                out.flush();
                out.close();
                //读取临时文件
                RandomAccessFile tmprf = new RandomAccessFile(realFileName + ".tmp", "rw");
                StringBuilder insertedContent = new StringBuilder("");
                String tmpline = null;
                while ((tmpline = tmprf.readLine()) != null) {
                    // System.out.println(line);
                    // System.out.println(new String(tmpline.getBytes("ISO-8859-1"), "gbk"));
                    insertedContent.append(new String(tmpline.getBytes("ISO-8859-1"), "gbk") + "\r\n");
                }
                tmprf.close();

                RandomAccessFile rf = new RandomAccessFile(realFileName, "rw");
                StringBuilder sourceFilecontent = new StringBuilder("");
                String line = null;
                while ((line = rf.readLine()) != null) {
                    // System.out.println(line);
                    // System.err.println(EncodingUtils.getString(line.getBytes("ISO-8859-1"), "UTF-8"));
                    sourceFilecontent.append(new String(line.getBytes("ISO-8859-1"), "UTF-8") + "\r\n");
                }
                // @TODO gtq
                Writer writer = new FileWriter(lastOutFile);
                if (lastOutFile.getName().indexOf(".xml") > 0) {
                    writer.write(sourceFilecontent.toString().replace("@TODO", "-->\r\n " + insertedContent.toString()));
                } else {
                    writer.write(sourceFilecontent.toString().replace("@TODO", "\r\n " + insertedContent.toString()));
                }
                //将临时文件写入当前文件
                writer.write(insertedContent.toString());

                if (lastOutFile.getName().indexOf(".xml") > 0) {
                    writer.write("</mapper>");
                } else {
                    writer.write("}");
                }
                writer.flush();
                writer.close();
                rf.close();
                while (true) {
                    File tmpfile = new File(realFileName + ".tmp");
                    boolean flag = tmpfile.exists();
                    if (flag) {
                        tmpfile.delete();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            root.put("fileExist", false);
            try {
                // SYSTEM_ENCODING = "UTF-8";
                Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "utf-8");

                template.process(root, out);
                cfg.clearTemplateCache();
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
