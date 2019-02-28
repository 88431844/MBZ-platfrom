package com.fh.util.mbh;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class WriteStringOnImg {
	public static void main(String[] args){
		String path="F:/MBH/数据标定/小狗机器人/人脸标注项目/元数据/第二批数据/10.209.2.59-3-170809_145604-00007467_2_1.jpg";    
		String path2="F:/MBH/数据标定/小狗机器人/人脸标注项目/元数据/第二批数据/标注数据3/10.209.2.59-3-170809_145604-00007467_2_1.jpg"; 
		try {
			File f1 = new File(path);
			BufferedImage image = ImageIO.read(f1);
			Graphics g = image.getGraphics();
			g.setFont(new Font("Serif",Font.BOLD,40));
			g.setColor(Color.red);
			g.drawString("happy new year!", 20, 40);
			File f2 = new File(path2);
			ImageIO.write(image, "JPEG", f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入源文件路径，以及修改后新生成的路径,writeContent为写入内容
	 * @param oldimgPath
	 * @param newImgPath
	 */
	public static void writeStringToImg(String oldimgPath,String newImgPath,String writeContent,int fontSize){
		try {
			File f1 = new File(oldimgPath);
			BufferedImage image = ImageIO.read(f1);
			Graphics g = image.getGraphics();
			g.setFont(new Font("Serif",Font.BOLD,fontSize));
			g.setColor(Color.red);
			g.drawString(writeContent, 40, 80);
			File f2 = new File(newImgPath);
			ImageIO.write(image, "JPEG", f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入源文件路径，以及修改后新生成的路径,writeContent为写入内容
	 * @param oldimgPath
	 * @param newImgPath
	 */
	public static void writeStringListToImg(String oldimgPath,String newImgPath,List<String> writeContent,int fontSize){
		try {
			File f1 = new File(oldimgPath);
			BufferedImage image = ImageIO.read(f1);
			Graphics g = image.getGraphics();
			g.setFont(new Font("Serif",Font.BOLD,fontSize));
			g.setColor(Color.red);
			for (int i = 0; i < writeContent.size(); i++) {
				g.drawString(writeContent.get(i), 40, 20+i*30);
			}
			File f2 = new File(newImgPath);
			ImageIO.write(image, "JPEG", f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
