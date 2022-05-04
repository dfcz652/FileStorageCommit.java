package com.boots.controller;

import com.boots.dto.AuthResponse;
import com.boots.entity.Links;
import com.boots.repository.LinksRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.ALL;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@Controller
public class DownloadController {

    @Autowired
    private LinksRepository linksRepository;

    /**
     * A method that requests all files from the database and returns a generated html page with all files <<associated with getPathsForLinks()>>
     * @param //model
     * @return html
     */
//    @RequestMapping(value = "/downloading")
//    public String download(Model model){
//        model.addAttribute("pathList", linksRepository.getPathsForLinks());
//
//        return "downloading";
//    }

    @RequestMapping(value = "/downloading")
    public @ResponseBody
    List<Object> download(){
        List<Object> results = Collections.singletonList(linksRepository.getPathsForLinks());

        return results;
    }

    /**
     * Method that returns the file to the user if he clicks on the download button<<associated with downloadFileAlongTheWay(file_token)>>
     * @param //file_token
     * @return ResponseEntity<Object>
     * @throws IOException
     */
//    @GetMapping("/download")
//    public ResponseEntity<Object> downloadFile(@RequestParam("file_token") String file_token) throws IOException{
//
//        System.out.println("Путь файла: " + file_token);
//
//        ResponseEntity<Object> file = linksRepository.downloadFileAlongTheWay(file_token);
//        System.out.println("Файл выдан");
//
//        return file;
//    }

    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile(@RequestParam String file_token) throws IOException {

        String contentType = "";

        System.out.println("Путь файла: " + file_token);

        String substringFile = file_token.substring(18);

        int dotInFileName = file_token.lastIndexOf('.');

        System.out.println(dotInFileName);

//        String fileExtension = file_token.substring(file_token.length()-4);

        String fileExtension = file_token.substring(dotInFileName);

//        ResponseEntity<Object> file = linksRepository.downloadFileAlongTheWay(file_token);
        System.out.println("Файл выдан");

        Resource resource = new ClassPathResource(substringFile);

        //MediaType contentType = APPLICATION_OCTET_STREAM;

        switch (fileExtension) {

            case ".png" : contentType = "image/png";
            break;

            case ".txt" : contentType = "text/txt";
            break;

            case ".html" : contentType = "text/html";
            break;

            case ".mp3" : contentType = "audio/mpeg3";
            break;
        }




        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     *Method that deletes the file when the delete button is clicked<<associated with deleteFile(file_token), delete(id)>>
     * @param id
     * @param file_token
     * @return html
     */
//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") Long id,@RequestParam("file_token") String file_token) {
//
//        linksRepository.deleteFile(file_token);
//        linksRepository.delete(id);
//        System.out.println("Удалено успешно");
//
//        return "redirect:/downloading";
//    }

    //Проверить
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("file_token") String file_token) {

        System.out.println("OLEG");
        linksRepository.deleteFile(file_token);
        linksRepository.delete(id);
        System.out.println("Удалено успешно");
        return "Удалено успешно";
    }
}


