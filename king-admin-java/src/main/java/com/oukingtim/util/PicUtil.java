package com.oukingtim.util;


//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Created by chenjian on 2018/3/26.
 */
public class PicUtil {

    /**
     * 裁剪、缩放图片工具类
     * @author CSDN 没有梦想-何必远方
     */
    /**
     * 缩放图片方法
     *
     * @param srcImageFile 要缩放的图片路径
     * @param result       缩放后的图片路径
     * @param height       目标高度像素
     * @param width        目标宽度像素
     * @param bb           是否补白
     */
//    public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
//        try {
//            double ratio = 0.0; // 缩放比例
//            File f = new File(srcImageFile);
//            BufferedImage bi = ImageIO.read(f);
//            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
//            // 计算比例
//            //if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
//            double ratioHeight = (new Integer(height)).doubleValue() / bi.getHeight();
//            double ratioWhidth = (new Integer(width)).doubleValue() / bi.getWidth();
//            if (ratioHeight > ratioWhidth) {
//                ratio = ratioHeight;
//            } else {
//                ratio = ratioWhidth;
//            }
//            AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
//                    .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
//            itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
//            // }
//            if (bb) {//补白
//                BufferedImage image = new BufferedImage(width, height,
//                        BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
//                Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
//                g.setColor(Color.white);//控制颜色
//                g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
//                if (width == itemp.getWidth(null))
//                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
//                            itemp.getWidth(null), itemp.getHeight(null),
//                            Color.white, null);
//                else
//                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
//                            itemp.getWidth(null), itemp.getHeight(null),
//                            Color.white, null);
//                g.dispose();
//                itemp = image;
//            }
//            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 裁剪图片方法
     *
     * @param bufferedImage 图像源
     * @param startX        裁剪开始x坐标
     * @param startY        裁剪开始y坐标
     * @param endX          裁剪结束x坐标
     * @param endY          裁剪结束y坐标
     * @return
     */
    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }


    /**
     *
     * @param souchFilePath
     *            ：源图片路径
     * @param targetFilePath
     *            ：生成后的目标图片路径
     * @param markContent
     *            :要加的文字
     * @param markContentColor
     *            :文字颜色
     * @param qualNum
     *            :质量数字
     * @param fontType
     *            :字体类型
     * @param fontSize
     *            :字体大小
     * @return
     */
    public static void createMark(String souchFilePath, String targetFilePath,
                                  String markContent, Color markContentColor, float qualNum,
                                  String fontType, int fontSize, int w, int h, Color color) {
        markContentColor = color;
                          /* 构建要处理的源图片 */
        ImageIcon imageIcon = new ImageIcon(souchFilePath);
                          /* 获取要处理的图片 */
        Image image = imageIcon.getImage();
        // Image可以获得图片的属性信息
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        // 为画出与源图片的相同大小的图片（可以自己定义）
        BufferedImage bImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 构建2D画笔
        Graphics2D g = bImage.createGraphics();
                          /* 设置2D画笔的画出的文字颜色 */
        g.setColor(markContentColor);
                          /* 设置2D画笔的画出的文字背景色 */
        g.setBackground(Color.white);
                          /* 画出图片 */
        g.drawImage(image, 0, 0, null);
                          /* --------对要显示的文字进行处理-------------- */
        AttributedString ats = new AttributedString(markContent);
        Font font = new Font(fontType, Font.BOLD, fontSize);
        g.setFont(font);
                         /* 消除java.awt.Font字体的锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                          /* 消除java.awt.Font字体的锯齿 */
        // font = g.getFont().deriveFont(30.0f);
        ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
        AttributedCharacterIterator iter = ats.getIterator();
                          /* 添加水印的文字和设置水印文字出现的内容 ----位置 */
        g.drawString(iter, width - w, height - h);
                          /* --------对要显示的文字进行处理-------------- www.it165.net */
        g.dispose();// 画笔结束
        try {
            // 输出 文件 到指定的路径
//            FileOutputStream out = new FileOutputStream(targetFilePath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
//            param.setQuality(qualNum, true);
//            encoder.encode(bImage, param);
//            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//
//    public static void main(String[] args) throws IOException {
//// 原图位置, 输出图片位置, 水印文字颜色, 水印文字
//        //PicUtil.createMark("/Users/chenjian/Desktop/a3.jpg", "/Users/chenjian/Desktop/a4.jpg","你好阿斯顿发送地方!",null, 1, "Times New Roman", 30, 200, 300,Color.GRAY);
//
//        //copyFile3("/Users/chenjian/Desktop/a3.jpg","/Users/chenjian/Desktop/a1.jpg");
//        FileUtils.copyFile(new File("/Users/chenjian/Desktop/a3.jpg"),new File("/Users/chenjian/Desktop/a1.jpg"));
//    }

}
