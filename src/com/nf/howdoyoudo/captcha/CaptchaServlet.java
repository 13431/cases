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

        // 1. 得到一个随机字符串
        String randomstr = captchaUtil.getRandomString(4);
        System.out.println("得到的验证码为: " + randomstr);

        // 2. 将这个字符串保存到 Session 里面
        req.getSession().setAttribute("_verifycode", randomstr);

        // 3. 为这个字符串生成一个图片
        BufferedImage image = captchaUtil.getCaptchaImage(randomstr);

        // 4. 将这个图片发送到客户端
        ImageIO.write(image, "PNG", resp.getOutputStream());

    }
}
