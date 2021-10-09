package cn.qkmango.captcha;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author qkmango
 * @version 1.0
 * CaptchaBuilder 验证码构造类
 * 此类实例可以通过 create() 返回一个内部类 Captcha 类实例
 * @date 2021-10-09 15:18
 */
public class CaptchaBuilder {

    //region Attributes
    /**
     * 图片 width
     */
    private int width = 120;
    /**
     * 图片 height
     */
    private int height = 45;
    /**
     * 图片上显示验证码的个数
     */
    private int codeSize = 4;

    /**
     * 是否将图片转为base64
     */
    private boolean base64 = false;

    /**
     * 是否开启线条扰乱
     */
    private boolean interference = true;
    /**
     * 线条扰乱个数
     */
    private int interferenceSize = 50;
    /**
     * 线条扰乱颜色
     */
    private Color interferenceColor = Color.BLACK;
    /**
     * 图片背景颜色
     */
    private Color backgroundColor = Color.WHITE;
    /**
     * 图片边框颜色
     */
    private Color borderColor = Color.BLACK;
    /**
     * 左内边距
     */
    private int paddingLeft = 0;
    /**
     * 右内边距
     */
    private int paddingRight = 0;
    /**
     * 字符间距
     */
    private int codeBasicLeft = (width - paddingLeft - paddingRight) / codeSize;
    /**
     * 文字高度
     */
    private int fontHeight = 35;
    /**
     * 验证码Y坐标,居中显示
     */
    private int codeY = height - (height - fontHeight) / 2;
    /**
     * 验证码字符可选字符
     */
    private char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    //endregion

    public CaptchaBuilder() {
    }


    /**
     * 创建一个验证码
     * @return Captcha 返回一个验证码对象
     */
    public Captcha create() throws IOException {

        Captcha captcha = new Captcha();

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定，文字使用默认字体 Default
        Font font = new Font(null, Font.BOLD, fontHeight);
        // 设置字体
        g.setFont(font);

        // 画边框
        g.setColor(borderColor);
        g.drawRect(0, 0, width - 1, height - 1);

        if (interference) {
            // 随机产生 interferenceSize 条干扰线
            g.setColor(interferenceColor);
            for (int i = 0; i < interferenceSize; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
        }

        // randomCode 保存随机产生的验证码字符
        StringBuilder randomCode = new StringBuilder(codeSize);

        int red, green, blue;

        // 随机产生 codeSize 个验证文字
        for (int i = 0; i < codeSize; i++) {
            // 得到随机产生的验证码数字
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red, green, blue));
            int codeLeft = paddingLeft + i * codeBasicLeft;
            g.drawString(code, codeLeft, codeY);

            // 将产生的四个随机数组合在一起
            randomCode.append(code);
        }

        //存放验证码
        captcha.code = randomCode.toString();

        //存放验证码图片
        //将图片转为 base64 存放
        if (base64) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "jpeg", out);
            byte[] bytes = out.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            //存放生成的验证码BufferedImage对象
            captcha.pic = "data:image/jpg;base64," + encoder.encode(bytes);
        } else {
            //存放生成的验证码 BufferedImage 对象
            captcha.pic = buffImg;
        }

        return captcha;
    }


    //region get and set methods

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(int codeSize) {
        this.codeSize = codeSize;
    }

    public boolean isBase64() {
        return base64;
    }

    public void setBase64(boolean base64) {
        this.base64 = base64;
    }

    public boolean isInterference() {
        return interference;
    }

    public void setInterference(boolean interference) {
        this.interference = interference;
    }

    public int getInterferenceSize() {
        return interferenceSize;
    }

    public void setInterferenceSize(int interferenceSize) {
        this.interferenceSize = interferenceSize;
    }

    public Color getInterferenceColor() {
        return interferenceColor;
    }

    public void setInterferenceColor(Color interferenceColor) {
        this.interferenceColor = interferenceColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getCodeBasicLeft() {
        return codeBasicLeft;
    }

    public void setCodeBasicLeft(int codeBasicLeft) {
        this.codeBasicLeft = codeBasicLeft;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public int getCodeY() {
        return codeY;
    }

    public void setCodeY(int codeY) {
        this.codeY = codeY;
    }

    public char[] getCodeSequence() {
        return codeSequence;
    }

    public void setCodeSequence(char[] codeSequence) {
        this.codeSequence = codeSequence;
    }
    //endregion


    /**
     * 验证码类，存储 验证码文字和图片
     */
    public class Captcha {
        /**
         * 验证码 代码
         */
        private String code;
        /**
         * 验证码图片
         * 以 BufferedImage 或 Base64 字符串存储
         */
        private Object pic;

        public Captcha() {
        }

        public Captcha(String code, Object pic) {
            this.code = code;
            this.pic = pic;
        }

        public String getCode() {
            return code;
        }

        public Object getPic() {
            return pic;
        }

    }

}
