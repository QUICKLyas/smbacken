package com.example.smbacken.util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyUtil {
    private static final char[] chars = {
        '0','1','2','3','4','5','6','7','8','9',
        'a','b','c','d','e','f','g',
        'h','i','j','k', 'l','m','n',
        'o','p','q','r','s','t',
        'u','v', 'w','x','y','z',
        'A','B','C','D','E','F','G',
        'H','I','J','K','L','M','N',
        'O','P','Q','R','S','T',
        'U','V','W','X','Y','Z'
    };

    private static final int SIZE = 4;
    private static final int LINES = 5;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static final int FONT_SIZE = 30;

    public static Object[] createImage(){
        StringBuilder bufferedImage = new StringBuilder();
        BufferedImage image = new BufferedImage(
                WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.fillRect(0,0,WIDTH,HEIGHT);
        Random random = new Random();
        for (int i = 0 ; i < SIZE;i++){
            // 随机引用字符
            int n = random.nextInt(chars.length);
            // 设置字体和颜色
            graphic.setColor(getRandomColor());
            graphic.setFont(
                    new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphic.drawString(chars[n] + "" , i*WIDTH/SIZE,HEIGHT*2/3);
            bufferedImage.append(chars[n]);
        }
        for (int i = 0 ;i <LINES;i++) {
            graphic.setColor(getRandomColor());
            graphic.drawLine(random.nextInt(WIDTH),random.nextInt(HEIGHT),
                    random.nextInt(WIDTH),random.nextInt(HEIGHT));
        }
        // 返回验证码和图片
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "jpeg",byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // byteArrayOutputStream.toByteArray()
        return new Object[]{bufferedImage.toString(),new BASE64Encoder().encode(byteArrayOutputStream.toByteArray())};
    }
    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
        return color;
    }
}
