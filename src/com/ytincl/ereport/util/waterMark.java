package com.ytincl.ereport.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.ytincl.ereport.constant.CommonConstants;


public class waterMark {
	private static final int width = 400;
	private static final int heigth = 300;
	private static final String path = System.getProperty("ereport.root");
	private static final String Res_Path = path+"resources/img/";
	private waterMark(){
	}
	public static String getWaterMark(String institution,String operator){
		return makewaterMark(waterMark.width,waterMark.heigth,institution,operator);
	}
	public static String getWaterMark(int w,int h,String institution,String operator){
		return makewaterMark(w,h,institution,operator);
	}
	private static synchronized String makewaterMark(int w,int h,String institution,String operator){
		//����һ��͸��ͼƬ
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setStroke(new BasicStroke(1));
		String time = Long.toString(System.currentTimeMillis());
		String filename = time+CommonConstants.POINT + CommonConstants.SUFFIX_PNG;
		//����
		Font f = new Font("����", Font.BOLD, 40);
		g2d.setFont(f);
		//�����
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//������ɫ    ǳ��16579320
		g2d.setColor(new Color(15985636));
		//����Բ����ͼƬ�����½�
		g2d.translate(0, h);
		g2d.rotate(-0.55);
		//����ˮӡ
		g2d.drawString(institution, w/4, -(h/4));
		g2d.drawString(operator, w/4+10, -((h/4)-35));
		
		g2d.dispose();
		try {
			ImageIO.write(image, "PNG", new File(Res_Path+filename));
			return filename;
		} catch (Exception e) {
			// TODO: handle exception
			return CommonConstants.NULL_STRING;
		}
	}
}
