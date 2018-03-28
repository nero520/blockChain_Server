package cn.itcast.utils;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class XmlUtils {
	
	private static String usersfilepath;
	private static String blocksfilepath;
	static {
		//usersfilepath = XmlUtils.class.getClassLoader().getResource("users.xml").getPath();
		//判断操作系统
		if(System.getProperty("file.separator").equals("/")) {
			usersfilepath = "/home/upfiles/block/users.xml";
			blocksfilepath = "/home/upfiles/block/blocks.xml";
		}else{
			usersfilepath = "D:\\users.xml";
			blocksfilepath = "D:\\blocks.xml";
		}

		try {
			usersfilepath = URLDecoder.decode(usersfilepath, "UTF-8");	//解决文件夹命名中有空格的问题！！！
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Document getDocument() throws Exception {
		//检查数据文件目录，不存在就创建
		/*File dirFile = new File(usersfilepath);
		boolean bFile   = dirFile.exists();
		if(!bFile ){
			bFile = dirFile.mkdir();
			//往新创建的本地文件里面写一个创世块
			try{
				FileOutputStream out = new FileOutputStream(new File(usersfilepath));
				out.write((BlockChain.CreateFirstBlock().toInfoString()+"\r\n").getBytes());
				out.close();
			}catch(Exception e){
			}
		}*/
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(usersfilepath));
		return document;
		
	}

	public static Document getBlocksDocument() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(blocksfilepath));
		return document;

	}
	
	public static void write2UsersXml(Document document) throws IOException, IOException {
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(usersfilepath), format);
		writer.write(document);
		writer.close();
		
	}

	public static void write2BlocksXml(Document document) throws IOException, IOException {

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(blocksfilepath), format);
		writer.write(document);
		writer.close();

	}

}
