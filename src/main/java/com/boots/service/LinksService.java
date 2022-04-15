package com.boots.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

import java.util.List;
import java.util.Map;

public interface LinksService {

    /**
     * Deleting a file from the project root folder
     * @param file_token
     */
    void deleteFile(String file_token);

    /**
     * Removing data about a file from the database
     * @param id
     */
    void delete(Long id);


    /**
     * Fetching all elements from the table and initializing them for the thymeleaf
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getPathsForLinks();

    /**
     * Generating a file for download
     * @param file_token
     * @return ResponseEntity<Object>
     * @throws IOException
     */
    ResponseEntity<Object> downloadFileAlongTheWay(String file_token) throws IOException;
}
