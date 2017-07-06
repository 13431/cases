package com.nf.howdoyoudo.servlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * 注解(Annotation)是用来解决 XML 配置不够灵活的问题而出现的
 * 一个 Servlet，如果在 web.xml 中配置，至少需要配置两个节点：
 * <code>
 *     <servlet></servlet>
 *     <servlet-mapping></servlet-mapping>
 * </code>
 * 这样的配置代码会比较多，写得比较麻烦。所以 servlet 2.5 之后支持使用注解
 * 直接在要映射的类上用 @WebServlet(映射路径) 的方式，代替上面复杂的 xml 配置
 *
 * 更简单，更直观，更高效！
 * (当然，xml 配置也有自己的优势，要辩证地看问题。xml 配置容易集中管理。)
 *
 * 比如，下面的这个注解，等价的 xml 配置为：
 * <code>
 *   <servlet>
 *       <servlet-name>uploadServlet</servlet-name>
 *       <servlet-class>com.nf.howdoyoudo.servlet.UploadServlet</servlet-class>
 *   </servlet>
 *   <servlet-mapping>
 *       <servlet-name>uploadServlet</servlet-name>
 *       <url-pattern>/upload</url-pattern>
 *   </servlet-mapping>
 * </code>
 *
 * 这样在客户端，只需要访问 /upload 就能访问到这个 Servlet 了。
 * 同样的，filter 和 listener 也有相应的注解，现在的 web 项目，web.xml 可以删掉不用了。
 */
@WebServlet("/upload")
@MultipartConfig  // 必需项，MultipartConfig 用来使得我们的 Servlet 支持 multipart 编码的文件上传
public class UploadServlet extends HttpServlet {


    /**
     * HTTP 规范规定了很多请求的 Method，对应的是 增-删-改-查
     * 但在遥远的年代, web 技术不发达，那时候浏览器的实现支持的只有 get/post 两种
     * 这两种请求也足够大家快乐使用了
     *
     * 但现在，网络爆发的时代，其他的各种请求的方法，被人们重新挖掘出来
     *
     * Servlet 中，针对每种不同的 request method，都提供了想对应的 do 方法
     * 比如，如果浏览器采用的是 get 方法请求，在 servlet 中调用的会是对应的 doGet 方法
     * 比如，如果浏览器采用的是 post 方法请求，在 servlet 中调用的会是对应的 doPost 方法
     *
     * 实际运用中，尽量不要将逻辑写在 service 方法里
     * （service 是所有 doXxx 方法的入口，如果覆写了 service，就不会调用相应的 doXxx 方法了）
     *
     * 而是，分工明确，责任单一。根据客户端请求的 method，写逻辑到相应的 doXxx 方法
     *
     * 我们要养成，良好的习惯。
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try { //异常处理
            // 保存文件到硬盘
            newage_advanced(req);
            // 跳转到成功页面
            req.setAttribute("upload_result", "恭喜您，上传成功了！");
            req.getRequestDispatcher("/pages/fileupload/result.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* MultiplePart 3.0, 多文件 */
    private void newage_multiplefiles(HttpServletRequest request) throws IOException, ServletException {
        for (Part part : request.getParts()) {
            // 逐个处理
        }
    }


    /* MultiplePart 3.0, Advanced */
    private int newage_advanced(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart("meinv");

        System.out.printf(
                ">> 基本情况：\nName: %s\nSize: %d\nContentType: %s\nHeaderNames: %s\ngetSubmittedFileName: %s\ndisposition: %s\n",
                part.getName(),
                part.getSize(),
                part.getContentType(),
                part.getHeaderNames(),
                part.getSubmittedFileName(),
                part.getHeader("content-disposition"));

        String realfilename = part.getHeader("content-disposition").split("filename=")[1].replace("\"", "");
        if (realfilename.equals(part.getSubmittedFileName())) {
            System.out.println("恭喜你，真厉害~~~");
        }

        String realfilename2 = part.getHeader("content-disposition").substring(part.getHeader("content-disposition").lastIndexOf("=") + 2, part.getHeader("content-disposition").lastIndexOf("\""));
        System.out.println(realfilename2 + "fileName");
        if (realfilename2.equals(part.getSubmittedFileName())) {
            System.out.println("恭喜你，你真棒！！！");
        }

        return 1;
    }


    /* 让我们携手进入新时代, Servlet 3.0 内置支持文件上传 */
    private void newage_basic(HttpServletRequest request) throws IOException, ServletException {
        // 获取文件
        Part file = request.getPart("meinv");
        // 保存到位置
        file.write("E:\\meinv_bk222.jpg");
    }


    /* 进入江湖大佬 Apache 的时代，Commons-Fileupload.jar 包的使用 */
    private void apachestime(HttpServletRequest request) throws Exception {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> files = upload.parseRequest(request);
        for (FileItem file : files) {
            if (!file.isFormField()) {
                file.write(new File("E:\\beauty-bk.jpg"));
            }
        }
    }


    /* 很久很久以前，需要自己手动从 InputStream 中处理数据 */
    private void longlongago(HttpServletRequest request) throws Exception {

        // 保存文件的路径
        File file = new File("E:\\saved-beauty.txt");
        FileOutputStream output = new FileOutputStream(file);

        // 取出客户端传来的数据
        ServletInputStream input = request.getInputStream();

        // 读取数据，写入文件
        byte[] bytes = new byte[1024];
        while (input.read(bytes) != -1) {
            output.write(bytes);
        }

        // 释放资源
        output.close();
        input.close();
    }
}
