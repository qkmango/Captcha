# Captcha

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Release](https://img.shields.io/github/v/release/qkmango/Captcha?style=flat-square)](https://github.com/qkmango/Captcha) [![homepage:qkmango.cn](https://img.shields.io/badge/homepage-qkmango-default)](https://qkmango.gitee.io/homepage/)

π θ―­θ¨ / language 
- π©[δΈ­ζ ![](README/CN.png)](README.md)
- β[English ![](README/EN.png)](README.en.md)


## Introduction

Captcha is a verification code generation tool, which can easily generate verification codes. All configuration items have default values and can be used out of the box.

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

        //ιͺθ―η ζι ε?δΎ
        CaptchaBuilder builder = new CaptchaBuilder();

        //θ?Ύη½?ζε­ιΏεΊ¦
        builder.setCodeSize(4);
        //θ?Ύη½?εΎηε?½εΊ¦
        builder.setWidth(120);
        //θ?Ύη½?εΎηδ»₯base64ε­η¬¦δΈ²ε½’εΌ
        builder.setBase64(true);

        //εε»ΊδΈδΈͺιͺθ―η οΌθ·εΎδΈδΈͺιͺθ―η ε―Ήθ±‘
        CaptchaBuilder.Captcha code1 = builder.create();
        System.out.println(code1.getCode());
        System.out.println(code1.getPic());

        //εε»ΊδΈδΈͺιͺθ―η οΌθ·εΎδΈδΈͺιͺθ―η ε―Ήθ±‘
        CaptchaBuilder.Captcha code2 = builder.create();
        System.out.println(code2.getCode());
        System.out.println(code2.getPic());
    }
}
```



### example 2

εε»ΊδΈδΈͺιͺθ―η εΉΆδΏε­ε°ζ¬ε°ζδ»Ά

Create a captcha image and save it to a local file

```java
import cn.qkmango.captcha.CaptchaBuilder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {

        //ιͺθ―η ζε»Ίε¨
        CaptchaBuilder builder = new CaptchaBuilder();

        //εε»Ίιͺθ―η 
        CaptchaBuilder.Captcha captcha = builder.create();

        //θ·ειͺθ―η ζε­εεΎη
        String code = captcha.getCode();
        BufferedImage pic = (BufferedImage)captcha.getPic();

        //ε°ιͺθ―η εΎηδΏε­ε°ζ¬ε°ζδ»Ά
        FileOutputStream os = new FileOutputStream("D:\\" + code + ".jpg");
        ImageIO.write(pic,"jpeg",os);
        os.close();

    }
}
```

