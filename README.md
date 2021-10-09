# Captcha

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Release](https://img.shields.io/github/v/release/qkmango/Captcha?style=flat-square)](https://github.com/qkmango/Captcha) [![homepage:qkmango.cn](https://img.shields.io/badge/homepage-qkmango-default)](https://qkmango.gitee.io/homepage/)




## 介绍
Captcha 是一个验证码生成的工具，可以很方便的生成验证码，所有的配置项都有默认的配置，开箱即用。


## 安装教程



## 可配置项

所有的配置项都在 CaptchaBuilder 实例中，且都有默认值

| 属性              | 功能                   | 类型           | 默认值                                                       |
| ----------------- | ---------------------- | -------------- | ------------------------------------------------------------ |
| width             | 图片宽度               | int            | 120                                                          |
| height            | 图片高度               | int            | 45                                                           |
| codeSize          | 图片上显示验证码的个数 | int            | 4                                                            |
| base64            | 是否将图片转为 base64  | boolean        | false                                                        |
| interference      | 是否开启线条扰乱       | boolean        | true                                                         |
| interferenceSize  | 线条扰乱个数           | int            | 50                                                           |
| interferenceColor | 线条扰乱颜色           | java.awt.Color | Color.BLACK                                                  |
| backgroundColor   | 图片背景颜色           | java.awt.Color | Color.WHITE                                                  |
| borderColor       | 图片边框颜色           | java.awt.Color | Color.BLACK                                                  |
| paddingLeft       | 左内边距               | int            | 0                                                            |
| paddingRight      | 右内边距               | int            | 0                                                            |
| codeBasicLeft     | 字符间距               | int            | 平均分配<br>`(width - paddingLeft - paddingRight) / codeSize` |
| fontHeight        | 文字高度               | int            | 35                                                           |
| codeY             | 验证码Y坐标            | int            | 垂直居中<br>`height - (height - fontHeight) / 2`             |
| codeSequence      | 验证码字符可选字符     | char[]         | 大写字母和数字                                               |



## 使用说明

### 例子 1

创建两个验证码以 base64 字符串方式

```java
import cn.qkmango.captcha.CaptchaBuilder;
import java.io.IOException;

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
```



### 例子 2

创建一个验证码并保存到本地文件

```java
import cn.qkmango.captcha.CaptchaBuilder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

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
```

