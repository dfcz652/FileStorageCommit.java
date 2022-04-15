package com.boots.controller;

import com.boots.entity.Links;

import com.boots.service.DownloadRepository;

import com.boots.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UploadFileController {

    @Autowired
    DownloadRepository downloadRepository;

    /**
     * File upload controller
     * @param  * @param null
     * @return html
     */
        @RequestMapping(value = "/upload",method = RequestMethod.GET)
        public String upload(){
            return "/upload";
        }

        /**
         * Single file upload<<associated with save(entityLinks)>>
         * @param file
         * @return redirectHtml
         */
        @RequestMapping(value = "/upload", method = RequestMethod.POST)
        @ResponseBody
        public RedirectView upload(@RequestParam("file") MultipartFile file){
            String contentType = file.getContentType (); // Image file type
            String fileName = FileUtil.getFileName (file.getOriginalFilename ()); // Image name
            // String fileUUID = FileUtil.getUUID();
            String filePath = "src/main/resources/templates/img/";

            System.out.println("Токен файла: " + fileName);
            System.out.println("Имя файла: " + file.getOriginalFilename ());

            Links entityLinks = new Links();

            entityLinks.setPath(file.getOriginalFilename());
            entityLinks.setToken(fileName);

            downloadRepository.save(entityLinks);
            try {
                // Call the file handling class FileUtil to process the file and write the file to the specified location
                FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:1001");
            return redirectView;
        }
    }
