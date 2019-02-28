package com.fh.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**	文件处理
*  创建人：FH Q313596790
 * 创建时间：2014年12月23日
 */
public class FileUtil {
	//存放需要的后缀名
	public static final String[] suffixName= new String[]{"mp3","mp4"};

	public static void main(String[] args) {
//		String dirName = "E:\\jsonTest";// 创建目录
//		Map<String,String> allFile = FileUtil.getAllFileName(dirName,suffixName);
//		for(Map.Entry<String, String> entry : allFile.entrySet()){
//			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
//		}
		
		String userFilePath = "F:\\MBH\\数据标定\\七牛云\\元数据\\鉴黄图片分类\\黄金题汇总_20181002\\账号及题.txt";
		File file  = new File(userFilePath);
		System.out.println(file.isDirectory());
	}

	/**
	 * 创建目录
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 读取到字节数组0
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取所有文件名称
	 * 
	 * @param path
	 * @return
	 */
	public static Map<String,String> getAllFileName(String path,String[] suffixName){
		Map<String,String> allFileName = new HashMap<String,String>();
		
		getFileName(path,allFileName,suffixName);
		return allFileName;
	}
	
	public static void getFileName(String path,Map<String,String> allFileName,String[] suffixName){
		
		File file = new File(path);
		File[] files = file.listFiles();
		String[] names = file.list();
		
//		if(names != null){
//			allFileName.addAll(Arrays.asList(names));
//		}
		for(File a:files){
			if(a.isDirectory()){
				getFileName(a.getAbsolutePath(),allFileName,suffixName);
			}else{
				//获取文件名后缀
				String suffix = FileUtil.getFileNameSuffix(a.getName().toString());
				//获取文件名称
				String fileName = FileUtil.getFileName(a.getName().toString());
				//判断文件名称是否存在
				boolean fileNameFlag = FileUtil.getFileNameFlag(fileName, names);
				//判断文件名后缀是否是在规定范围内
				boolean suffixFlag = Arrays.asList(suffixName).contains(suffix);
				if(!fileNameFlag && suffixFlag){
					allFileName.put(a.getName().toString(), a.getAbsolutePath());
				}
			}
		}
		
	}
	
	/**
	 * 根据路径获取文件名称后缀
	 * @return
	 */
	public static String getFileNameSuffix(String path){
		String fileNameSuffix = path.substring(path.lastIndexOf(".")+1);
		return fileNameSuffix;
	}
	
	/**
	 * 根据路径获取文件名字
	 * @return
	 */
	public static String getFileName(String path){
		String[] fileNamea = path.split("\\.");
		String fileName = fileNamea[0];
		return fileName;
	}
	/**
	 * 判断文件夹中是否存在同名的.txt文件
	 * @param fileName
	 * @param names
	 * @return
	 */
	public static boolean getFileNameFlag(String fileName,String[] names){
		String suffix = ".txt";
		boolean fileNameFlag = Arrays.asList(names).contains(fileName+suffix);
		return fileNameFlag;
	}
	
	/**
	 * 判断文件夹中是否存在不存在则创建
	 * @return
	 */
	public static void getFolderFlag(String folderPath){
		File file = new File(folderPath);
		//如果存在则不创建
		if(!file .exists()  && !file .isDirectory()){
			file.mkdirs();
			System.out.println("文件夹创建成功！");
		}else{
			System.out.println("文件夹已经存在！");
		}
	}
	
	/**
	 * 判断文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean getFileNameFlage(String filePath){
		boolean flag = true;
		File file = new File(filePath);
		//如果存在则不创建
		if(!file .exists()  && !file .isDirectory()){
			flag = false;
		}
		
		return flag;
	}
	
}