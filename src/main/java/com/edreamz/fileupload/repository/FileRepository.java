package com.edreamz.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edreamz.fileupload.model.FileModal;


public interface FileRepository extends JpaRepository<FileModal, Long>{

}
