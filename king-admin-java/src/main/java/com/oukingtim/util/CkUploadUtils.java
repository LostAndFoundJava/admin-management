/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: CkUploadUtils.java
 * Author:   zhouyipeng
 * Date:     2017年6月28日 上午11:07:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.oukingtim.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author zhouyipeng
 * @since [产品/模块版本] （可选）
 */
public class CkUploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(CkUploadUtils.class);

    // 图片类型
    private static List<String> fileTypes = new ArrayList<String>();

    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }

    /**
     * 图片上传
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     */
    public static String upload(HttpServletRequest request, String savePath)
            throws IllegalStateException, IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());

                // fastdfs上传文件
                // fileName =
                // FastDFSClient.getFastDFSClient().uploadFile(file.getBytes(),
                // file.getOriginalFilename());
                File f = null;
                BufferedImage image = null;
                ImageOutputStream output = null;
                FileInputStream in = null;
                try {
                    f = File.createTempFile("tmp", null);
                    file.transferTo(f);

                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
                    String prefix = System.currentTimeMillis() + "";
                    savePath += "/" + prefix + suffix;


                    File file2 = new File(savePath);
                    boolean b = f.renameTo(file2);
                    image = ImageIO.read(file2);
                    output = ImageIO.createImageOutputStream(file2);
                    if (!ImageIO.write(image, "jpg", output)) {
                        logger.error("cmyk转化异常:{}", image);
                        throw new RuntimeException("图片上传失败,请换一张图试试！");
                    }
                    // List 返回图片size 原图 200*200 400*400 600*600 图采用600*600
                    in = new FileInputStream(file2);

                    //TODO 上传文件到服务器
//					List<String> image1 = new ArrayList<>();
                    /*
					List<String> image1 = FastDFSClient.getFastDFSClient().uploadFileImage(file2);
					*/
                    fileName = prefix + suffix;
                    FtpUtil.uploadFile(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, Constants.BASE_PATH, "", fileName, in);
//					fileName = image1.get(3);
                    file2.deleteOnExit();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                } finally {
                    if (image != null) {
                        image.flush();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (output != null) {
                        output.flush();
                        output.close();
                    }
                }
            }
        }
        return fileName;
    }

//
//	public static String upload(HttpServletRequest request, HttpSession session) {
//		// 创建一个通用的多部分解析器
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//				request.getSession().getServletContext());
//		// 图片名称
//		String fileName = null;
//		// 判断 request 是否有文件上传,即多部分请求
//		if (multipartResolver.isMultipart(request)) {
//			// 转换成多部分request
//			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//			// 取得request中的所有文件名
//			Iterator<String> iter = multiRequest.getFileNames();
//			while (iter.hasNext()) {
//				// 记录上传过程起始时的时间，用来计算上传时间
//				// int pre = (int) System.currentTimeMillis();
//				// 取得上传文件
//				MultipartFile file = multiRequest.getFile(iter.next());
//				fileName = XpersionFileUploadUtil.uploadImg(file, session);
//			}
//		}
//		return fileName;
//	}

    /**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     *
     * @param request
     * @param response 文件上传目录：比如upload(无需带前面的/) upload/..
     * @throws IOException
     * @throws IllegalStateException
     * @Title ckeditor
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String fastIp,
                                String savePath) throws IOException, IllegalStateException {
        String fileName;
        PrintWriter out = null;
        try {
            fileName = upload(request, savePath);
            fileName = Constants.FILE_SERVER_ADMIN +"/"+ fileName + "!" + Constants.IMAGE_SIZE;
            response.setContentType("text/html;charset=UTF-8");
            String callback = request.getParameter("CKEditorFuncNum");
            out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileName + "',''" + ")");
            out.println("</script>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
