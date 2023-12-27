package com.example.apienglish.controller;

import java.io.IOException;
import java.util.List;
import com.example.apienglish.entity.AnsLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.Phonics;
import com.example.apienglish.repository.AnsLessonRepository;
import com.example.apienglish.service.AnsLessonService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AnsLessonController {
		
	@Autowired
	private AnsLessonService ansLessonService;
	
	@Autowired
	private AnsLessonRepository ansLessonRepository;
	
	  @PostMapping("/anslesson/{lessonId}/addAnsLesson")
	    public ResponseEntity<AnsLesson> AnsLesson( 
	    		@RequestBody AnsLesson ansLessonRequest, 
	    	 @PathVariable("lessonId") Long lessonId) {
	        try {
	            AnsLesson ansLesson = ansLessonService.addAnsLesson(ansLessonRequest.getName(), ansLessonRequest.getExplain(), lessonId);
	            return new ResponseEntity<>(ansLesson, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @GetMapping("/getAnsLessonsByLessonId/{lessonId}")
	    public List<AnsLesson> getAnsLessonsByUnitId(@PathVariable Long lessonId) {
	        return ansLessonRepository.findAnsLessonByLessonId(lessonId);
	    }
	    
	    @GetMapping("/getAllLessonByUnitId")
	    public List<AnsLesson> getAllPhonicsByUnitId() {
	        return ansLessonRepository.findAll();
	    }
}
