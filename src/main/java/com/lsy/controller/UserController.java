package com.lsy.controller;

import com.lsy.bean.User;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

//    @RequestMapping("/testAjax")
//    public void testAjax(@RequestBody String boby) {
//        System.out.println("111111111111111111111");
//        System.out.println(boby);
//    }user

    @RequestMapping("/testAjax")
    @ResponseBody
    public User testAjax(@RequestParam(value = "user") User user,@RequestParam(value = "newAge") Integer newAge) {
        System.out.println("###########newAge："+newAge);
        System.out.println(user);
        user.setUsername("崔慧格");
        return user;
    }

    @RequestMapping("/tx")
    public String tx() {
        System.out.println("111111111111111111111");
        return "login";
    }

    /*
     * 文件上传--传统方式
     * */
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) throws Exception {
        //获取上传文件目录的路径
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        System.out.println("111111111111111111111" + request.getSession().getServletContext());
        System.out.println("111111111111111111111" + path);
        File file = new File(path);
// 判断路径是否存在，如果不存在，创建该路径
        if (!file.exists()) {
            file.mkdirs();
        }
        //创建磁盘文件项工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        List<FileItem> list = fileUpload.parseRequest(request);
        //遍历
        for (FileItem fileItem : list) {
            //判断文件是普通字段，还是上传文件
            if (fileItem.isFormField()) {

            } else {
                //上传文件项
                //获取上传文件名称
                String filename = fileItem.getName();
                System.out.println("上传文件名字" + filename);
                //上传文件
                fileItem.write(new File(file, filename));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    /*
     * 文件上传--传统方式--磁盘任意指定位置
     * */
    @RequestMapping("/atWillUpload")
    public String atWillUpload(HttpServletRequest request) throws Exception {
        //获取上传文件目录的路径
        String path = "F:\\file";
        System.out.println("111111111111111111111" + request.getSession().getServletContext());
        System.out.println("111111111111111111111" + path);
        File file = new File(path);
// 判断路径是否存在，如果不存在，创建该路径
        if (!file.exists()) {
            file.mkdirs();
        }
        //创建磁盘文件项工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        List<FileItem> list = fileUpload.parseRequest(request);
        //遍历
        for (FileItem fileItem : list) {
            //判断文件是普通字段，还是上传文件
            if (fileItem.isFormField()) {

            } else {
                //上传文件项
                //获取上传文件名称
                String filename = fileItem.getName();
                System.out.println("上传文件名字" + filename);
                //上传文件
                fileItem.write(new File(file, filename));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    /**
     * SpringMVC方式的文件上传
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/springMvcFileUpload")
    public String springMvcFileUpload(Model model, User user, String sex, HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("接受的对象：" + user + sex);
        System.out.println("SpringMvc的方式上传。。。");
        //获取上传文件目录的路径
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        System.out.println("111111111111111111111" + request.getSession().getServletContext());
        System.out.println("111111111111111111111" + path);
        File file = new File(path);
// 判断路径是否存在，如果不存在，创建该路径
        if (!file.exists()) {
            file.mkdirs();
        }
        //名字相同会覆盖
        String filename = upload.getOriginalFilename();
        System.out.println("上传文件名字" + filename + "wwwwwwwwwww" + upload.getName());
        //防止名字相同覆盖
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        //上传文件
        model.addAttribute("pic", filename);
        upload.transferTo(new File(file, filename));

        return "success";
    }
    @PostMapping("/doDownFile")
    public  ResponseEntity<byte[]> downloadFile(String filename, HttpServletRequest request) throws IOException {
        System.out.println("接受的参数："+filename);
        HttpHeaders headers = new HttpHeaders();
        File isFile=new File(filename);
        if(isFile.exists()){
            StringBuffer sb = new StringBuffer();
            for (int i =6; i < filename.length(); i++) {
                sb.append(filename.charAt(i));
            }

            String name=sb.toString();
            System.out.println("参数name："+name);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
            name = URLEncoder.encode(name,"utf-8");
            headers.setContentDispositionFormData("attachment",name);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(isFile),
                    headers, HttpStatus.OK);
        }else{
            File directory = new File("");// 参数为空
            String path = directory.getCanonicalPath()+"\\upload";
            File file = new File(path+"/"+filename);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
            filename = URLEncoder.encode(filename,"utf-8");
            System.out.println("参数path："+path);
            headers.setContentDispositionFormData("attachment",filename);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
        }
/*
* 下载文件 成功
* */
//    @RequestMapping("/doDownFile")
//    public ResponseEntity<byte[]> doDownFile(String filename, HttpServletRequest request) throws IOException {
//        System.out.println("接受的参数："+filename);
//        String path=request.getSession().getServletContext().getRealPath("/springmvcFile");
//        HttpHeaders headers = new HttpHeaders();
//        File file = new File(path+"/"+filename);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
//        filename = URLEncoder.encode(filename,"utf-8");
//        headers.setContentDispositionFormData("attachment",filename);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                headers, HttpStatus.OK);
//    }
    /*
     * 下载文件2 ajax请求下载 ajax没有流形式，无法实现下载
     * ajax 是无法实现文件下载的，原因：ajax请求只是个“字符型”的请求，即请求的内容是以文本类型存放的。
     * 文件的下载是以二进制形式进行的，虽然可以读取到返回的response，但只是读取而已，是无法执行的。
     * */
//    @RequestMapping("/doDownFile2")
//    public ResponseEntity<byte[]> doDownFile2(@RequestBody Map<String,String> map, HttpServletRequest request) throws IOException {
//        String filename=map.get("filename");
//        System.out.println("接受的参数22222:"+filename);
//        String path=request.getSession().getServletContext().getRealPath("/springmvcFile");
//
//        HttpHeaders headers = new HttpHeaders();
//        File file = new File(path+"/"+filename);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment",filename);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                headers, HttpStatus.OK);
//    }
//    @RequestMapping("/Ajax1")
//    public void Ajax1(String name, HttpServletRequest request , HttpServletResponse response) throws IOException {
//        String name1=request.getParameter("name");
//        System.out.println(name+"####"+name1);
//        response.getWriter().println(1);
//    }
}}
