package com.kb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kb.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RestController
@RequestMapping("/data/*")
public class RESTController {

	/**
	 * 단순문자
	 * 
	 * @return
	 */
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8;")
	public String getText() {
		log.info(MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}

	/**
	 * 객체타입 리턴
	 * 
	 * @return
	 */
	@GetMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		return new SampleVO(113, "스타", "로드");
	}

	/**
	 * 컬렉션타입 리턴
	 * 
	 * @return
	 */
	@GetMapping(value = "/getSample2")
	public List<SampleVO> getSample2() {
		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 10; i++) {
			list.add(new SampleVO(i, "스타" + i, "로드" + i));
		}
		return list;
	}

	/**
	 * 맵(Map<key,value>) 리턴 json은 형태주의
	 */
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(103, "스타", "로드"));
		map.put("second", new SampleVO(113, "스타", "로드"));

		return map;
	}

	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, ""+height, ""+weight);

		ResponseEntity<SampleVO> result = null;
		
		if(height > 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	@GetMapping(value = "/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		
		return new String[] {"category : "+cat,"product : "+pid};
	}

}
