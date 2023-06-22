package com.edreamz.fileupload.services;

import java.util.List;

import com.edreamz.fileupload.model.FileModal;

public interface Fileservice {
	
	List<FileModal> getAllFiles(Integer pagesize,Integer pagenumber);

	void saveAllFilesList(List<FileModal> fileList);
	
    boolean delete(Long id);
}
