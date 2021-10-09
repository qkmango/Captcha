import cn.qkmango.CaptchaBuilder;

import java.io.IOException;

/**
 * @author qkmango
 * @version 1.0
 * Demo 使用演示 创建两个验证码以 base64 字符串方式
 * @date 2021-10-09 16:52
 */
public class Demo {
    public static void main(String[] args) throws IOException {

        //验证码构造实例
        CaptchaBuilder builder = new CaptchaBuilder();

        //设置文字长度
        builder.setCodeSize(4);
        //设置图片宽度
        builder.setWidth(120);
        //设置图片以base64字符串形式
        builder.setBase64(true);

        //创建一个验证码，获得一个验证码对象
        CaptchaBuilder.Captcha code1 = builder.create();
        System.out.println(code1.getCode());
        System.out.println(code1.getPic());

        //创建一个验证码，获得一个验证码对象
        CaptchaBuilder.Captcha code2 = builder.create();
        System.out.println(code2.getCode());
        System.out.println(code2.getPic());


    }
}
