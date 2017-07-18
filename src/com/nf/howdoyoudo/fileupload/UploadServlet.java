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
 * ע��(Annotation)��������� XML ���ò���������������ֵ�
 * һ�� Servlet������� web.xml �����ã�������Ҫ���������ڵ㣺
 * <code>
 *     <servlet></servlet>
 *     <servlet-mapping></servlet-mapping>
 * </code>
 * ���������ô����Ƚ϶࣬д�ñȽ��鷳������ servlet 2.5 ֮��֧��ʹ��ע��
 * ֱ����Ҫӳ��������� @WebServlet(ӳ��·��) �ķ�ʽ���������渴�ӵ� xml ����
 *
 * ���򵥣���ֱ�ۣ�����Ч��
 * (��Ȼ��xml ����Ҳ���Լ������ƣ�Ҫ��֤�ؿ����⡣xml �������׼��й���)
 *
 * ���磬��������ע�⣬�ȼ۵� xml ����Ϊ��
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
 * �����ڿͻ��ˣ�ֻ��Ҫ���� /upload ���ܷ��ʵ���� Servlet �ˡ�
 * ͬ���ģ�filter �� listener Ҳ����Ӧ��ע�⣬���ڵ� web ��Ŀ��web.xml ����ɾ�������ˡ�
 */

@WebServlet("/upload")
@MultipartConfig  // �����MultipartConfig ����ʹ�����ǵ� Servlet ֧�� multipart ������ļ��ϴ�
public class UploadServlet extends HttpServlet {


    /**
     * HTTP �淶�涨�˺ܶ������ Method����Ӧ���� ��-ɾ-��-��
     * ����ңԶ�����, web �����������ʱ���������ʵ��֧�ֵ�ֻ�� get/post ����
     * ����������Ҳ�㹻��ҿ���ʹ����
     *
     * �����ڣ����籬����ʱ���������ĸ�������ķ����������������ھ����
     *
     * Servlet �У����ÿ�ֲ�ͬ�� request method�����ṩ�����Ӧ�� do ����
     * ���磬�����������õ��� get ���������� servlet �е��õĻ��Ƕ�Ӧ�� doGet ����
     * ���磬�����������õ��� post ���������� servlet �е��õĻ��Ƕ�Ӧ�� doPost ����
     *
     * ʵ�������У�������Ҫ���߼�д�� service ������
     * ��service ������ doXxx ��������ڣ������д�� service���Ͳ��������Ӧ�� doXxx �����ˣ�
     *
     * ���ǣ��ֹ���ȷ�����ε�һ�����ݿͻ�������� method��д�߼�����Ӧ�� doXxx ����
     *
     * ����Ҫ���ɣ����õ�ϰ�ߡ�
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            newage_advanced(req);  // ������Ӧ�����������ļ���Ӳ��
            req.setAttribute("upload_result", "��ϲ�����ϴ��ɹ���");
        } catch (Exception ex) {
            // �������ʧ�ܣ�����ʧ����Ϣ.
            req.setAttribute("upload_result", "�ܱ�Ǹ���ϴ�ʧ�ܣ�");
        }

        // ��ת�����ҳ��
        req.getRequestDispatcher("pages/fileupload/result.jsp").forward(req, resp);
    }

    /**
     * ���� MultiplePart 3.0 �Ƚ���֧�֣������ļ�����
     */
    private void newage_advanced(HttpServletRequest request) throws Exception {

        // 1. ��ȡ
        Part part = request.getPart("pic");

        // 2. �鿴 part ����
        System.out.printf(
                ">> ���������\nName: %s\nSize: %d\nContentType: %s\nHeaderNames: %s\ngetSubmittedFileName: %s\ndisposition: %s\n",
                part.getName(),
                part.getSize(),
                part.getContentType(),
                part.getHeaderNames(),
                // part.getSubmittedFileName(),
                part.getHeader("content-disposition"));

        // 3. ��ȡ�ļ�Ҫ���浽���ļ��У���������Ŀ�µ� upload �ļ��С�
        // ����ļ��в����ڣ�Ҫ�ȴ���
        File destdir = new File(request.getServletContext().getRealPath("upload"));
        if (!destdir.exists()) destdir.mkdir();

        // 4. ��ȡ��ʵ���ļ�����3.1 ֮���� part.getSubmittedFileName() ��������ֱ�ӻ�ȡ��
        // ֮ǰ�İ汾����Ҫ�� header �л�ȡ�����磺
        String realfilename = part.getHeader("content-disposition").split("filename=")[1].replace("\"", "");
        //    String realfilename = part.getHeader("content-disposition").substring(part.getHeader("content-disposition").lastIndexOf("=") + 2, part.getHeader("content-disposition").lastIndexOf("\""));
        //    String realfilename = part.getSubmittedFileName();

        // 5. �������յı���·��
        String fullSavePath = destdir + File.separator + System.currentTimeMillis() + "-" + realfilename;

        // 6. ����֮
        part.write(fullSavePath);
    }


    /* MultiplePart 3.0, ���ļ� */
    private void newage_multiplefiles(HttpServletRequest request) throws IOException, ServletException {
        for (Part part : request.getParts()) {
            // �������
        }
    }


    /* ������Я�ֽ�����ʱ��, Servlet 3.0 ����֧���ļ��ϴ� */
    private void newage_basic(HttpServletRequest request) throws IOException, ServletException {
        // ��ȡ�ļ�
        Part file = request.getPart("meinv");
        // ���浽λ��
        file.write("E:\\meinv_bk222.jpg");
    }


    /* ���뽭������ Apache ��ʱ����Commons-Fileupload.jar ����ʹ�� */
    private void apachestime(HttpServletRequest request) throws Exception {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> files = upload.parseRequest(request);
        for (FileItem file : files) {
            if (!file.isFormField()) {
                file.write(new File("E:\\beauty-bk.jpg"));
            }
        }
    }


    /* �ܾúܾ���ǰ����Ҫ�Լ��ֶ��� InputStream �д������� */
    private void longlongago(HttpServletRequest request) throws Exception {

        // �����ļ���·��
        File file = new File("E:\\saved-beauty.txt");
        FileOutputStream output = new FileOutputStream(file);

        // ȡ���ͻ��˴���������
        ServletInputStream input = request.getInputStream();

        // ��ȡ���ݣ�д���ļ�
        byte[] bytes = new byte[1024];
        while (input.read(bytes) != -1) {
            output.write(bytes);
        }

        // �ͷ���Դ
        output.close();
        input.close();
    }
}
