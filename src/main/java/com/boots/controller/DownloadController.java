package com.boots.controller;

import com.boots.service.LinksService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class DownloadController {

    @Autowired
    private LinksService linksService;

    /**
     * A method that requests all files from the database and returns a generated html page with all files <<associated with getPathsForLinks()>>
     * @param model
     * @return html
     */
    @RequestMapping(value = "/downloading")
    public String download(Model model){
        model.addAttribute("pathList", linksService.getPathsForLinks());

        return "downloading";
    }

    /**
     * Method that returns the file to the user if he clicks on the download button<<associated with downloadFileAlongTheWay(file_token)>>
     * @param file_token
     * @return ResponseEntity<Object>
     * @throws IOException
     */
    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile(@RequestParam("file_token") String file_token) throws IOException{

        System.out.println("Путь файла: " + file_token);

        ResponseEntity<Object> file = linksService.downloadFileAlongTheWay(file_token);
        System.out.println("Файл выдан");

        return file;
    }

    /**
     *Method that deletes the file when the delete button is clicked<<associated with deleteFile(file_token), delete(id)>>
     * @param id
     * @param file_token
     * @return html
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,@RequestParam("file_token") String file_token) {

        linksService.deleteFile(file_token);
        linksService.delete(id);
        System.out.println("Удалено успешно");

        return "redirect:/downloading";
    }
}
