package com.novel.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public final class CaptchaUtil
{

	private static final long serialVersionUID = 1L;
	private static int WIDTH=60;
	private static int HEIGHT=30;


	private CaptchaUtil(){}



	/*
	 * 随机数
	 */
	private static void drawBackground(Graphics g) {

		g.setColor(new Color (0xDCDCDC));
		g.fillRect(0,0, WIDTH , HEIGHT );
		for ( int i =0; i <120; i ++) {
			int x =(int)( Math . random () * WIDTH );
			int y =(int)( Math . random () * HEIGHT );
			int red =(int)( Math . random () * 255);
			int green =(int)( Math . random () * 255);
			int blue =(int)( Math . random () * 255);
			g.setColor (new Color (red,green,blue));
			g.drawOval(x,y,1,0);
		}
	}

	private static void drawRands(Graphics g, char[] rands) {

		g.setColor( Color.BLACK );
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));
		g.drawString (""+ rands [0],1,17);
		g.drawString (""+ rands [1],16,15);
		g.drawString (""+ rands [2],31,18);
		g.drawString (""+ rands [3],46,16);

		System.out.println(rands);
	}

	private static char[] generatedCheckCode() {

		String chars ="0123456789abcdefghijklmnopqrstuvwxyz";
		char [] rands = new char [4];
		for (int i = 0 ; i < 4 ; i++) {
			int rand =(int)( Math . random ()*36);
			rands [i]= chars.charAt(rand);

		}
		return rands;
	}

	public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");

		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires",0);
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		char [] rands = generatedCheckCode();
		drawBackground(g);
		drawRands(g,rands);
		g.dispose ();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image,"JPEG", bos);
		byte [] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close ();
		sos.close ();
		HttpSession session = request.getSession();
		session.setAttribute("check_code",new String (rands));
	}
}