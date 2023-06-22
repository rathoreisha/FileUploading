package com.edreamz.fileupload.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edreamz.fileupload.model.FileModal;
import com.edreamz.fileupload.repository.FileRepository;
import com.edreamz.fileupload.services.Fileservice;

@Service
public class FileServiceImplementation implements Fileservice {
	@Autowired
	FileRepository fileRepository;
	 private final Path root = Paths.get("uploads");

	@Override
	public List<FileModal> getAllFiles(Integer pagesize, Integer pagenumber) {
		Pageable pageable = PageRequest.of(pagenumber, pagesize);

		// fetch all the files form database
		return fileRepository.findAll(pageable).getContent();
	}
	@Override
	public void saveAllFilesList(List<FileModal> fileList) {
		// Save all the files into database
		for (FileModal fileModal : fileList)
			fileRepository.save(fileModal);
	}
	@Override
	public boolean delete(Long id) {
		
		Optional<FileModal> findById = fileRepository.findById(id);
		if (findById.isPresent()) {
			FileModal find = findById.get();
			fileRepository.delete(find);
			return true;
		}
		return false;
	}

}
