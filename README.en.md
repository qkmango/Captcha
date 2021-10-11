# Captcha

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Release](https://img.shields.io/github/v/release/qkmango/Captcha?style=flat-square)](https://github.com/qkmango/Captcha) [![homepage:qkmango.cn](https://img.shields.io/badge/homepage-qkmango-default)](https://qkmango.gitee.io/homepage/)

🌐 语言 / language 
 <a href="README.md">中文<img src="README\CN.png"/></a> English<img src="README\EN.png"/>


## Introduction

Captcha is a verification code generation tool, which can easily generate verification codes. All configuration items have default configurations and can be used out of the box.

## Configuration items

All configuration items are in the CaptchaBuilder object and have default values

| Attribute        | Function                                       | Type           | Default                                                      |
| ---------------- | ---------------------------------------------- | -------------- | ------------------------------------------------------------ |
| width            | image width                                    | int            | 120                                                          |
| height           | image height                                   | int            | 45                                                           |
| codeSize         | The number of verification text on the picture | int            | 4                                                            |
| base64           | Whether to convert the image to base64         | boolean        | false                                                        |
| disturbance      | Whether to turn on line disturbance            | boolean        | true                                                         |
| disturbanceSize  | Number of line disturbances                    | int            | 50                                                           |
| disturbanceColor | Color of line disturbances                     | java.awt.Color | Color.BLACK                                                  |
| backgroundColor  | Image background color                         | java.awt.Color | Color.WHITE                                                  |
| borderColor      | Image border color                             | java.awt.Color | Color.BLACK                                                  |
| paddingLeft      | Image padding left                             | int            | 0                                                            |
| paddingRight     | Image padding right                            | int            | 0                                                            |
| codeBasicLeft    | Text spacing                                   | int            | average<br>`(width - paddingLeft - paddingRight) / codeSize` |
| fontHeight       | Font height                                    | int            | 35                                                           |
| codeY            | Text Y coordinate                              | int            | Vertically centered<br>`height - (height - fontHeight) / 2`  |
| codeSequence     | Image text character optional characters       | char[]         | Uppercase letters and numbers                                |



## Instructions for use

### example 1

Create tow captchas, captcha image is base64 strng

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



### example 2

创建一个验证码并保存到本地文件

Create a captcha image and save it to a local file

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

