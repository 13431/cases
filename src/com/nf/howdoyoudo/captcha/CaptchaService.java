package com.nf.howdoyoudo.captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CaptchaService {
    private int width = 120, height = 50, fontSize = 32;
    private String fontName = "Fixedsys";

    private ThreadLocalRandom r = ThreadLocalRandom.current();


    public CaptchaService() {
    }

    public CaptchaService(int width, int height, int fontSize) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
    }


    /**
     * 得到长度为 len 的随机字符串
     */
    public String getRandomString(int len) {
        String seeds = "abcdefghijklmnopqrstuvwzyzABCDEFGHJKLMNOPQRSTUVWZYZ23456789";

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; i++ ) {
            sb.append(seeds.charAt(r.nextInt(seeds.length())));
            // sb.append(seeds.toCharArray()[r.nextInt(seeds.length())]);
        }

        return sb.toString();
    }


    /**
     *  得到随机颜色
     */
    private Color getRandomColor() {
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }


    /**
     * 根据字符串画验证码图片
     * @param input
     * @return 验证码图片
     */
    public BufferedImage getCaptchaImage(String input) {

        // 1. 创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2. 得到画笔
        Graphics g = image.getGraphics();

        // 3. 填充背景色
        g.setColor(new Color(r.nextInt(200, 255), r.nextInt(240, 255), r.nextInt(200, 255)));
        g.fillRect(0, 0, width, height);

        // 4. 画字符串
        char[] inputs = input.toCharArray();
        for (int i = 0; i < inputs.length; i++) {
            g.setColor(getRandomColor());
            g.setFont(new Font(fontName, Font.PLAIN, fontSize));
            g.drawString(String.valueOf(inputs[i]), i*20 + 15, fontSize + r.nextInt(-3, 3));
        }

        // 画干扰线
        for(int i = 0; i < 4; i++ ) {
            g.setColor(getRandomColor());
            g.drawLine(0, r.nextInt(width), width, r.nextInt(height));
        }

        // 画干扰点
        for(int i = 0; i < 100; i++ ) {
            g.setColor(getRandomColor());
            g.fillOval(r.nextInt(width), r.nextInt(height), r.nextInt(5), r.nextInt(5));
        }

        g.dispose();
        return image;
    }

    /**
     * TODO: 发送短信验证码
     * @param phoneNumber 发送给谁
     * @param message 发送的内容
     */
    public void sendMessageCaptcha(String phoneNumber, String message) {
        // 尚未实现，留作练习
    }


    public static void main(String[] args) {
        CaptchaService captchaUtil = new CaptchaService();
        BufferedImage im = captchaUtil.getCaptchaImage("hello");

        try {
            ImageIO.write(im, "JPG", new File("D:\\" + System.currentTimeMillis() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
