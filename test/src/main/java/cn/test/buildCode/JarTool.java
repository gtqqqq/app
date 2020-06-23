    package cn.test.buildCode;
     
    import java.io.BufferedReader;
    import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
    import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
    import java.util.jar.JarFile;
    import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
     
    /**
     * jarPath: jar包所在路径 
     * jarFilePath: jar中想要修改文件所在的路径
     * 
     */
    public class JarTool {
     
    	public JarTool change(String jarPath, String jarFilePath,String pack)
    			throws IOException {
     
    		if (jarPath != null && jarFilePath != null) {
    			File file = new File(jarPath);
    			JarFile jarFile = new JarFile(file);// 通过jar包的路径 创建 jar包实例
    			JarEntry entry = jarFile
    					.getJarEntry(jarFilePath);// 通过某个文件在jar包中的位置来获取这个文件
    			InputStream input = jarFile.getInputStream(entry); // 创建该文件输入流
    			
    			List<JarEntry> lists = new LinkedList<JarEntry>();
    			
    			Map<String,String>  map = new HashMap<String,String> ();
    			for (Enumeration<JarEntry> entrys = jarFile.entries(); entrys.hasMoreElements();) {
    				JarEntry jarEntry = entrys.nextElement();
    				InputStream inputs = jarFile.getInputStream(jarEntry);
    				lists.add(jarEntry);
    				map.put(jarEntry.getName(), procesSteam(inputs,pack));
    			}
     
    			write(lists, entry, jarPath, procesSteam(input,pack),jarFile,map); // 修改文件内容
    			jarFile.close();
    			input.close();
    		}
     
    		return null;
    	}

     
    	public static void write(List<JarEntry> lists, JarEntry entry,
    			String jarPath, String content,JarFile jarFile,Map<String,String>  map) throws IOException {
     
    		JarOutputStream jos = null;
    		FileOutputStream fos = new FileOutputStream(jarPath);
    		jos = new JarOutputStream(fos);
    		try {
System.out.println(lists.get(lists.size()-1).getName()+"@@@@"+lists.get(lists.size()-2).getName());
    			for (JarEntry je : lists) {

    			        JarEntry newEntry = new JarEntry(je.getName());
    			        try {
    			            jos.putNextEntry(newEntry);
				} catch (Exception e) {
				    e.printStackTrace();
				}
    				if (je.getName().equals(entry.getName())) {
    				    jos.write(content.getBytes());
    				}else{
    				if (!je.isDirectory()&&je.getSize() >0) {
    				  if(map.get(je.getName())!=null){
    				  System.out.println(je.getName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
    				   jos.write(map.get(je.getName()).getBytes());
    				  }
    				  }
    				}
    			}
     
    			// 将内容写入文件中
     
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			// 关闭流
    			if (jos != null) {
    				try {
    					jos.close();
    					fos.close();
    				} catch (IOException e) {
    					jos = null;
    				}
    			}
    		}
    	}
    	private static String procesSteam( InputStream input,String pack)
			throws IOException {
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer buf = new StringBuffer();
		String line = null;

		while ((line = br.readLine()) != null) {
			if(line.indexOf("parent=\"defaultSqlSessionFactory\"")!=-1 && line.indexOf("class=\""+pack+"\"")==-1){
			    buf.append(line.substring(0, line.length()-1)+" class=\""+pack+"\">");
			}else{
			    buf.append(line); 
			}
			buf.append(System.getProperty("line.separator"));
		}
		
		isr.close();
		br.close();
		return buf.toString();
	}
    	
    	public static String upateDBConcfgFile( String path,String pack)
		throws IOException {
    	File file = new File(path);
	InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
	BufferedReader br = new BufferedReader(isr);
	StringBuffer buf = new StringBuffer();
	String line = null;

	while ((line = br.readLine()) != null) {
		if(line.indexOf("parent=\"defaultSqlSessionFactory\"")!=-1 && line.indexOf("class=\""+pack+"\"")==-1){
		    buf.append(line.substring(0, line.length()-1)+" class=\""+pack+"\">");
		}else{
		    buf.append(line); 
		}
		buf.append(System.getProperty("line.separator"));
	}
	isr.close();
	br.close();
	Writer writer = new FileWriter(file);
	    writer.write(buf.toString());
	    writer.flush();
	    writer.close();
	return buf.toString();
}
    }