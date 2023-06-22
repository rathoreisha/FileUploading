package com.edreamz.fileupload.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.edreamz.fileupload.model.FileModal;

import java.util.ArrayList;
import java.util.List;

import com.edreamz.fileupload.serviceimpl.FileServiceImplementation;

@Controller
public class FileController {

	@Autowired
	FileServiceImplementation fileServiceImplementation;

	// @GetMapping annotation for
	// mapping HTTP GET requests onto
	// specific handler methods. */
	@GetMapping("/")
	public String getData() {
		return "File";
	}

	// @PostMapping annotation maps HTTP POST
	// requests onto specific handler methods
	@PostMapping("/")
	public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model modal,
			@RequestParam(defaultValue = "1") Integer page) {
		try {

			List<FileModal> fileList = new ArrayList<FileModal>();
			for (MultipartFile file : files) {
				String fileContentType = file.getContentType();
				String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
				String fileName = file.getOriginalFilename();
				FileModal fileModal = new FileModal(fileName, sourceFileContent, fileContentType);

				// Adding file into fileList
				fileList.add(fileModal);
			}

			// Saving all the list item into database
			fileServiceImplementation.saveAllFilesList(fileList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		int total = 5;
		if (page == 1) {
		} else {
			page = (page - 1) * total + 1;
		}
		modal.addAttribute("allFiles", fileServiceImplementation.getAllFiles(total, page));

		return "FileList";
	}

	@GetMapping("/delete")
	public String deleteContact(@RequestParam("fileid") Long id, Model model) {
		fileServiceImplementation.delete(id);
		return "redirect:/";
	}

}
