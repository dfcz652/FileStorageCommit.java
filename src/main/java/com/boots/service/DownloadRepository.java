package com.boots.service;

import com.boots.entity.Links;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DownloadRepository extends JpaRepository<Links,Long> {

    /**
     *Saving a file and entering data about it in DB
     * @param //links
     * @return Links
     */
    Links save(Links links);

}
