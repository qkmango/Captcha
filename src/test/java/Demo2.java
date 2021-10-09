import cn.qkmango.CaptchaBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qkmango
 * @version 1.0
 * Demo2 使用演示 创建一个验证码并保存到本地文件
 * @date 2021-10-09 17:05
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {

        //验证码构建器
        CaptchaBuilder builder = new CaptchaBuilder();

        //创建验证码
        CaptchaBuilder.Captcha captcha = builder.create();

        //获取验证码文字和图片
        String code = captcha.getCode();
        BufferedImage pic = (BufferedImage)captcha.getPic();

        //将验证码图片保存到本地文件
        FileOutputStream os = new FileOutputStream("D:\\" + code + ".jpg");
        ImageIO.write(pic,"jpeg",os);
        os.close();

    }
}
