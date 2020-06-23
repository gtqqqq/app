package cn.test.buildCode;

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

    @SuppressWarnings("unchecked")
    public void processApi(FreeMarkerUtil hf, Map root,
                           String table) throws Exception {
        String project = root.get("project").toString();
        String projectPath = project.substring(0, project.lastIndexOf("-") + 1) + "api/" + root.get("packPath").toString() + project.substring(project.indexOf("-") + 1, project.lastIndexOf("-")) + "/";
        root.put("date", new Date());
        String savePath = "api/";
        String fileName = table + "Api.java";
        root.put("apiPackName", projectPath.replace("/", ".").substring(projectPath.indexOf("java/") + 5) + savePath.replace("/", ""));
        Template template = cfg.getTemplate("Api.ftl", Locale.CHINA);
        hf.buildTemplate2(root, projectPath, savePath, fileName, template);

        projectPath = project.substring(0, project.lastIndexOf("-") + 1) + "impl/" + root.get("packPath").toString() + project.substring(project.indexOf("-") + 1, project.lastIndexOf("-")) + "/";
        savePath = "dao/";
        fileName = table + "Dao.java";
        template = cfg.getTemplate("Dao.ftl", Locale.CHINA);
        root.put("tableName", table);
        root.put("daoPackName", projectPath.replace("/", ".").substring(projectPath.indexOf("java/") + 5) + savePath.replace("/", ""));
        hf.buildTemplate2(root, projectPath, savePath, fileName, template);


        fileName = table + "Model.java";
        savePath = "model/";
        template = cfg.getTemplate("Model.ftl", Locale.CHINA);
        root.put("tableName", table);
        root.put("modelPackName", projectPath.replace("/", ".").substring(projectPath.indexOf("java/") + 5) + savePath.replace("/", ""));
        hf.buildTemplate2(root, projectPath, savePath, fileName, template);
        String savePathImpl = savePath;
        String fileimplname = table;

        savePathImpl = "api/impl/";
        fileimplname = table + "ApiImpl.java";
        root.put("apiImplPackName", projectPath.replace("/", ".").substring(projectPath.indexOf("java/") + 5) + savePathImpl.substring(0, savePathImpl.length() - 1).replace("/", "."));
        template = cfg.getTemplate("ApiImple.ftl", Locale.CHINA);
        hf.buildTemplate2(root, projectPath, savePathImpl, fileimplname, template);

        projectPath = project.substring(0, project.lastIndexOf("-") + 1) + "impl/" + root.get("resourcesPath").toString() + project.substring(project.indexOf("-") + 1, project.lastIndexOf("-")) + "-sql-mapper/";
        fileimplname = table + "Mapper.xml";
        template = cfg.getTemplate("mapper.ftl", Locale.CHINA);
        hf.buildTemplate2(root, projectPath, "", fileimplname, template);
    }

    @SuppressWarnings("unchecked")
    public void process3(FreeMarkerUtil hf, Map root,
                         String table) throws Exception {

        root.put("date", new Date());
        String project = root.get("project").toString();
        String projectPath = project + "/" + "src/main/webapp/WEB-INF/templates/";
        String projectBefore = project.substring(project.lastIndexOf("-") + 1);
        String fileName = projectBefore;
        String savePath = "";
        Template template = null;


        savePath = root.get("location").toString() + "/fields/";
        fileName = "field_" + root.get("tableVar").toString() + "_main.ftl";
        template = cfg.getTemplate("field_list_main.ftl.jsp", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        savePath = root.get("location").toString() + "/fields/";
        fileName = "field_" + root.get("tableVar").toString() + "_scripts.ftl";
        template = cfg.getTemplate("field_list_scripts.jsp", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        savePath = root.get("location").toString() + "/fields/";
        fileName = "field_" + root.get("tableVar").toString() + "_links.ftl";
        template = cfg.getTemplate("field_list_links.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        savePath = root.get("location").toString() + "/";
        fileName = root.get("tableVar").toString() + "_list.ftl";
        template = cfg.getTemplate("Object_list.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);

        projectPath = project + "/" + root.get("packPath").toString() + project.substring(project.indexOf("web"), project.length()).replace("-", "") + "/";
        savePath = "web/form/" + root.get("location").toString() + "/";
        String packStr = projectPath.replace("/", ".").substring(projectPath.indexOf("java/") + 5);
        root.put("poPackName", packStr + savePath.substring(0, savePath.length() - 1).replace("/", "."));
        fileName = table + "Form.java";
        root.put("tableName", table);
        template = cfg.getTemplate("po.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);

        fileName = projectBefore.replace(projectBefore.substring(0, 1), projectBefore.substring(0, 1).toUpperCase()) + table + "Model.java";
        savePath = "model/" + root.get("location").toString() + "/";
        template = cfg.getTemplate("ModelWeb.ftl", Locale.CHINA);
        root.put("webTableName", projectBefore.replace(projectBefore.substring(0, 1), projectBefore.substring(0, 1).toUpperCase()) + table);
        root.put("modelPackName", packStr
                + savePath.substring(0, savePath.length() - 1).replace("/", "."));
        hf.buildTemplate2(root, projectPath, savePath, fileName, template);

        savePath = "web/controller/" + root.get("location").toString() + "/";
        fileName = projectBefore.replace(projectBefore.substring(0, 1), projectBefore.substring(0, 1).toUpperCase()) + table + "Controller.java";
        template = cfg.getTemplate("Controller.ftl", Locale.CHINA);
        root.put("controllerPackName", packStr + savePath.substring(0, savePath.length() - 1).replace("/", "."));
        hf.buildTemplate2(root, projectPath, savePath, fileName, template);

    }

    @SuppressWarnings("unchecked")
    public void process4(FreeMarkerUtil hf, Map root,
                         String table) throws Exception {

        root.put("date", new Date());
        String projectPath = "f://" + "projects" + "//" + table;
        String fileName = table + "Dao.java";
        String savePath = "";
        Template template = cfg.getTemplate("full.ftl", Locale.CHINA);
        root.put("tableName", table);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);


    }

    public void init() throws Exception {
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration();
        // 设置FreeMarker的模版文件位置

        cfg.setDirectoryForTemplateLoading(new File(
                "D:\\test\\src\\main\\resource\\tempLate3"));
        cfg.setEncoding(Locale.CHINA, "utf-8");

    }

    @SuppressWarnings("unchecked")
    public void creatCode(FreeMarkerUtil hf, Map root,
                          String table) throws Exception {
        root.put("tableVar", table);
        root.put("date", new Date().toString());
        String projectPath = root.get("projectPath").toString();
        String fileName = "test.js";
        String savePath = "";
        Template template = cfg.getTemplate("bean.ftl", Locale.CHINA);
        root.put("tableName", table);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);

    }

    public void process1(FreeMarkerUtil hf) throws Exception {

        Map root = new HashMap();

        String Module = "";
        String model_name = "User";
        String model_name_list = "Users";
        String instant = "user";
        String model_name_cn = "用户";
        String author = "gtq";
        String link = "保亚";// 模块开发公司网地址
        Date date = new Date();

        root.put("module", Module);
        root.put("model_name", model_name);
        root.put("model_name_list", model_name_list);
        root.put("instant", instant);
        root.put("model_name_cn", model_name_cn);
        root.put("author", author);
        root.put("link", link);
        root.put("date", date);

        String projectPath = "D://s2sh//";

        String fileName = "I" + model_name + "DAO.java";

        String savePath = "src//com//media//dao//";

        Template template = cfg.getTemplate("test.ftl");

        hf.buildTemplate(root, projectPath, savePath, fileName, template);
    }

    public void process(FreeMarkerUtil hf) throws Exception {

        Map root = new HashMap();

        String Module = "";
        String model_name = "User";
        String model_name_list = "Users";
        String instant = "user";
        String model_name_cn = "用户";
        String author = "gtq";
        String link = "保亚";// 模块开发公司网地址
        Date date = new Date();

        root.put("module", Module);
        root.put("model_name", model_name);
        root.put("model_name_list", model_name_list);
        root.put("instant", instant);
        root.put("model_name_cn", model_name_cn);
        root.put("author", author);
        root.put("link", link);
        root.put("date", date);

        String projectPath = "D://s2sh//";

        String fileName = "I" + model_name + "DAO.java";

        String savePath = "src//com//media//dao//";

        Template template = cfg.getTemplate("test.ftl");

        hf.buildTemplate(root, projectPath, savePath, fileName, template);

    }

    @SuppressWarnings("unchecked")
    public void process2(FreeMarkerUtil hf, Map root,
                         String table) throws Exception {

     

        root.put("date", new Date());

        String projectPath = root.get("projectPath").toString();

        String fileName =  table + "Mapper.java";

        String savePath =  "mapper//";
        Template template = cfg.getTemplate("Dao.ftl", Locale.CHINA);
        root.put("tableName", table);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        String savePathImpl =  "mapper//";
        String fileimplname = table + "Mapper.xml";
        template = cfg.getTemplate("Mapper2.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePathImpl, fileimplname, template);
        savePath =  "service//";
        fileName =  table + "Service.java";
        template = cfg.getTemplate("service.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        savePathImpl =  "service//impl//";
        fileimplname = table + "ServiceImpl.java";
        template = cfg.getTemplate("serviceImpl.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePathImpl, fileimplname, template);
        savePathImpl =  "//entity//vo//";
        fileimplname = table + "VO.java";
        template = cfg.getTemplate("vo.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePathImpl, fileimplname, template);
        savePathImpl =  "entity//po//";
        fileimplname = table + "PO.java";
        template = cfg.getTemplate("po.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePathImpl, fileimplname, template);
        savePathImpl =  "entity//dto//";
        fileimplname = table + "DTO.java";
        template = cfg.getTemplate("dto.ftl", Locale.CHINA);
        hf.buildTemplate(root, projectPath, savePathImpl, fileimplname, template);
     
       
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
