package com.nf.howdoyoudo.servlet;

import com.sun.deploy.net.HttpRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 保存文件到硬盘
            newage_advanced(req);
            // 跳转到成功页面
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
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
        if(realfilename.equals(part.getSubmittedFileName())) {
            System.out.println("恭喜你，真厉害~~~");
        }

        String realfilename2 = part.getHeader("content-disposition").substring(part.getHeader("content-disposition").lastIndexOf("=")+2,part.getHeader("content-disposition").lastIndexOf("\""));
        System.out.println(realfilename2+"fileName");
        if(realfilename2.equals(part.getSubmittedFileName())) {
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
            if(!file.isFormField()) {
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
        while(input.read(bytes) != -1) {
            output.write(bytes);
        }

        // 释放资源
        output.close();
        input.close();
    }
}
