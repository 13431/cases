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
     * �õ�����Ϊ len ������ַ���
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
     *  �õ������ɫ
     */
    private Color getRandomColor() {
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }


    /**
     * �����ַ�������֤��ͼƬ
     * @param input
     * @return ��֤��ͼƬ
     */
    public BufferedImage getCaptchaImage(String input) {

        // 1. ����ͼƬ����
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2. �õ�����
        Graphics g = image.getGraphics();

        // 3. ��䱳��ɫ
        g.setColor(new Color(r.nextInt(200, 255), r.nextInt(240, 255), r.nextInt(200, 255)));
        g.fillRect(0, 0, width, height);

        // 4. ���ַ���
        char[] inputs = input.toCharArray();
        for (int i = 0; i < inputs.length; i++) {
            g.setColor(getRandomColor());
            g.setFont(new Font(fontName, Font.PLAIN, fontSize));
            g.drawString(String.valueOf(inputs[i]), i*20 + 15, fontSize + r.nextInt(-3, 3));
        }

        // ��������
        for(int i = 0; i < 4; i++ ) {
            g.setColor(getRandomColor());
            g.drawLine(0, r.nextInt(width), width, r.nextInt(height));
        }

        // �����ŵ�
        for(int i = 0; i < 100; i++ ) {
            g.setColor(getRandomColor());
            g.fillOval(r.nextInt(width), r.nextInt(height), r.nextInt(5), r.nextInt(5));
        }

        g.dispose();
        return image;
    }

    /**
     * TODO: ���Ͷ�����֤��
     * @param phoneNumber ���͸�˭
     * @param message ���͵�����
     */
    public void sendMessageCaptcha(String phoneNumber, String message) {
        // ��δʵ�֣�������ϰ
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
