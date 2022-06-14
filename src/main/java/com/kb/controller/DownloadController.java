package com.kb.controller;


import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class DownloadController {

	@GetMapping(value="/download" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException{
		log.info(fileName);
		Resource resource = new FileSystemResource("d:/upload/"+fileName);
		
		String resourceName =  resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
