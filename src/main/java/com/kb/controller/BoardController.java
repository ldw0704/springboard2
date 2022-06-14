package com.kb.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kb.domain.BoardVO;
import com.kb.mapper.BoardAttachMapper;
import com.kb.domain.AttachFileDTO;
import com.kb.domain.BoardAttachVO;
import com.kb.domain.BoardCriteria;
import com.kb.domain.BoardPageDTO;
import com.kb.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;	
	

//	@GetMapping("list")
//	public void list(Model model) {
//		log.info("목록");		
//		model.addAttribute("list", service.getList());
//	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(BoardCriteria cri, Model model) {
		log.info(cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new BoardPageDTO(service.getListWithCnt(cri), cri));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {

	}
	
	/**
	 * 이미지 파일이면 true 그렇지않으면 false
	 * @param file
	 * @return
	 */
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			if(contentType!=null) return contentType.startsWith("image");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MultipartFile[] upfile, BoardVO board) {
		
		String filePath = "d:/upload";		
		
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		
		for(MultipartFile multi : upfile) {
		
			BoardAttachVO attachVO = new BoardAttachVO();
			
			String upfileTemp = multi.getOriginalFilename();
			
			String fileName = upfileTemp.substring(upfileTemp.lastIndexOf("/")+1);
						
			UUID uuid = UUID.randomUUID();
			
			String realSaveFileName = uuid.toString()+"_"+fileName;			
							
			File saveFile = new File(filePath,realSaveFileName);
			
			try {
				multi.transferTo(saveFile);//넘긴파일을 받아서 해당파일에 저장
				
				//이미지 파일이면 썸네일을 만든다.
				
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(filePath,"sm_"+realSaveFileName));
					Thumbnailator.createThumbnail(multi.getInputStream(),thumbnail,256,256);
					thumbnail.close();
				}
				
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadPath(filePath);
				attachVO.setFileName(realSaveFileName);
				
				list.add(attachVO);				
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		String title = board.getTitle();
		title = title.trim();
		board.setTitle(title);
		board.setAttachList(list);
		
		service.register(board);
		
		return "redirect:/board/list";
	}
//	public String register(BoardVO board, RedirectAttributes rttr) {
//
//		service.register(board);
//		
//		return "redirect:/board/list";
//	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	//public void get(int bno) {
	public void get(@RequestParam("bno") int bno, Model model) {
		
		
		model.addAttribute("board", service.get(bno));
		
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	//public void get(int bno) {
	public String get(@RequestParam("oldfile") ArrayList<String> oldfiles, MultipartFile[] upfile, BoardVO board) {
		String filePath = "d:/upload";
		log.info("upfile여부"+upfile.length);
		
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		
		for(MultipartFile multi : upfile) {
			
			BoardAttachVO attachVO = new BoardAttachVO();
			String upfileTemp = multi.getOriginalFilename();
			
			String fileName = upfileTemp.substring(upfileTemp.lastIndexOf("/")+1);
						
			UUID uuid = UUID.randomUUID();
			
			String realSaveFileName = uuid.toString()+"_"+fileName;
			
					
			File saveFile = new File(filePath,realSaveFileName);
			
			try {
				multi.transferTo(saveFile);//넘긴파일을 받아서 해당파일에 저장
				
				//이미지 파일이면 썸네일을 만든다.
				
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(filePath,"sm_"+realSaveFileName));
					Thumbnailator.createThumbnail(multi.getInputStream(),thumbnail,256,256);
					thumbnail.close();
				}
				
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadPath(filePath);
				attachVO.setFileName(realSaveFileName);
				
				list.add(attachVO);
				
			} catch (IllegalStateException | IOException e) {				
				e.printStackTrace();
			}
		}
		
		board.setAttachList(list);
		//기존파일 삭제
		Iterator<String> it = oldfiles.iterator();
		while(it.hasNext()) {
			String fileName = it.next();
			deleteFile(fileName);
		}
		
		
		boolean result = service.modify(board);
		if(result) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/get?bno="+board.getBno();
		}

	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	//public void get(int bno) {
	public String remove(BoardVO board) {
		
		service.remove(board);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("oldfile") ArrayList<String> oldfiles, BoardVO board) {
		
		service.remove(board);
		
		//기존파일 삭제
		Iterator<String> it = oldfiles.iterator();
		while(it.hasNext()) {
			String fileName = it.next();
			deleteFile(fileName);
		}	
		
		return "redirect:/board/list";
	}
	
	/**
	 * 첨부된 파일을 보여주기 위함
	 * url경로에 localhost/board/dispaly?filename=asdadf.jpg
	 * @param filename
	 * @return
	 */
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("d:/upload/"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return result;
	}
	
	/**
	 * 첨부파일 삭제
	 * @param attachvo
	 */
	private void deleteFile(String fileName) {
		
		try {
			File deleteFile =  new File("d:/upload/"+URLDecoder.decode(fileName,"UTF-8"));
			deleteFile.delete();
			
			File smDeleteFile =  new File("d:/upload/sm_"+URLDecoder.decode(fileName,"UTF-8"));
			smDeleteFile.delete();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}





