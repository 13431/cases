package com.nf.howdoyoudo.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CaptchaUtil {
    ThreadLocalRandom r = ThreadLocalRandom.current();

    public String getRandomString(int len) {
        String seeds = "abcdefghijklmnopqrstuvwzyzABCDEFGHJKLMNOPQRSTUVWZYZ23456789";

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; i++ ) {
            sb.append(seeds.charAt(r.nextInt(seeds.length())));
            // sb.append(seeds.toCharArray()[r.nextInt(seeds.length())]);
        }

        return sb.toString();
    }

    /* 得到随机颜色 */
    private Color getRandomColor() {
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    /**
     * 根据字符串画验证码图片
     * @param input
     * @return 验证码图片
     */
    public BufferedImage getCaptchaImage(String input) {
        int width = 130, height = 60;

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
            g.setFont(new Font("Georgia", Font.PLAIN, 30));
            g.drawString(String.valueOf(inputs[i]), i*20 + 15, 33 + r.nextInt(-3, 3));
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

    public static void main(String[] args) {
        CaptchaUtil captchaUtil = new CaptchaUtil();
        BufferedImage im = captchaUtil.getCaptchaImage("hello");

        try {
            ImageIO.write(im, "JPG", new File("D:\\" + System.currentTimeMillis() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
