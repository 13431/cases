package com.nf.howdoyoudo.fileupload;


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
 *       <servlet-class>com.nf.howdoyoudo.fileupload.UploadServlet</servlet-class>
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
        try {
            newage_advanced(req);  // 调用相应方法，保存文件到硬盘
            req.setAttribute("upload_result", "恭喜您，上传成功！");
        } catch (Exception ex) {
            // 如果保存失败，返回失败消息.
            req.setAttribute("upload_result", "很抱歉，上传失败！");
        }

        // 跳转到结果页面
        req.getRequestDispatcher("pages/fileupload/result.jsp").forward(req, resp);
    }

    /**
     * 利用 MultiplePart 3.0 內建的支持，进行文件处理
     */
    private void newage_advanced(HttpServletRequest request) throws Exception {

        // 1. 获取
        Part part = request.getPart("pic");

        // 2. 查看 part 对象
        System.out.printf(
                ">> 基本情况：\nName: %s\nSize: %d\nContentType: %s\nHeaderNames: %s\ngetSubmittedFileName: %s\ndisposition: %s\n",
                part.getName(),
                part.getSize(),
                part.getContentType(),
                part.getHeaderNames(),
                // part.getSubmittedFileName(),
                part.getHeader("content-disposition"));

        // 3. 获取文件要保存到的文件夹，这里是项目下的 upload 文件夹。
        // 如果文件夹不存在，要先创建
        File destdir = new File(request.getServletContext().getRealPath("upload"));
        if (!destdir.exists()) destdir.mkdir();

        // 4. 获取真实的文件名。3.1 之后有 part.getSubmittedFileName() 方法可以直接获取。
        // 之前的版本，需要从 header 中获取，比如：
        String realfilename = part.getHeader("content-disposition").split("filename=")[1].replace("\"", "");
        //    String realfilename = part.getHeader("content-disposition").substring(part.getHeader("content-disposition").lastIndexOf("=") + 2, part.getHeader("content-disposition").lastIndexOf("\""));
        //    String realfilename = part.getSubmittedFileName();

        // 5. 构建最终的保存路径
        String fullSavePath = destdir + File.separator + System.currentTimeMillis() + "-" + realfilename;

        // 6. 保存之
        part.write(fullSavePath);
    }


    /* MultiplePart 3.0, 多文件 */
    private void newage_multiplefiles(HttpServletRequest request) throws IOException, ServletException {
        for (Part part : request.getParts()) {
            // 逐个处理
        }
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
