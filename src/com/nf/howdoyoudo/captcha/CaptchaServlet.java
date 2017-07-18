package com.nf.howdoyoudo.captcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@WebServlet("/verifycode")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CaptchaService captchaUtil = new CaptchaService();

        // 1. �õ�һ������ַ���
        String randomstr = captchaUtil.getRandomString(4);
        System.out.println("�õ�����֤��Ϊ: " + randomstr);

        // 2. ������ַ������浽 Session ����
        req.getSession().setAttribute("_verifycode", randomstr);

        // 3. Ϊ����ַ�������һ��ͼƬ
        BufferedImage image = captchaUtil.getCaptchaImage(randomstr);

        // 4. �����ͼƬ���͵��ͻ���
        ImageIO.write(image, "PNG", resp.getOutputStream());

    }
}
