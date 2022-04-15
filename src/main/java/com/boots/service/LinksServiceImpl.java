package com.boots.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;
import java.util.Map;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Executing a query to the DB(delete from links where id = ?)
     * @param id
     */
    @Override
    public void delete(Long id) {

        String deleteQuery = "delete from links where id = ?";

        jdbcTemplate.update(deleteQuery, id);
    }

    /**
     * Deleting a file from the root folder of the project and outputting the result to the console
     * @param file_token
     */
    @Override
    public void deleteFile(String file_token) {
        File file = new File(file_token);
        if(file.delete()){
            System.out.println("file.txt файл был удален с корневой папки проекта");
        }else System.out.println("Файл file.txt не был найден в корневой папке проекта");
    }

    /**
     * Preparing a file for download
     * @param file_token
     * @return ResponseEntity<Object>
     * @throws IOException
     */
    @Override
    public ResponseEntity<Object> downloadFileAlongTheWay(String file_token) throws IOException {

        File file = new File(file_token);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object>
                responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    /**
     * Selection (token, path, id) from the table, adding to the file the root of the folder and adding these elements to the list
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> getPathsForLinks() {
        String textSQL = "select 'src/main/resources/templates/img/'||l.token::TEXT AS file_token, ";
        textSQL = textSQL + "l.path::TEXT AS path, ";
        textSQL = textSQL + "l.id::TEXT AS file_id ";
        textSQL = textSQL + "from links l";

        List<Map<String, Object>> listPathsForLinks = jdbcTemplate.queryForList(textSQL);
        return listPathsForLinks;
    }
}
