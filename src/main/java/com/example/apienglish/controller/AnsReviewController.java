package com.example.apienglish.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apienglish.entity.AnsLesson;
import com.example.apienglish.entity.AnsReview;
import com.example.apienglish.repository.AnsReviewRepository;
import com.example.apienglish.service.AnsReviewService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AnsReviewController {
	@Autowired
	private AnsReviewService ansReviewService;
	
	@Autowired
	private AnsReviewRepository ansReviewRepository;
	
	  @PostMapping("/ansreview/{lessonId}/addAnsReview")
	   public ResponseEntity<AnsReview> AnsReview(@RequestBody AnsReview ansReviewRequest, 
	    	 @PathVariable("lessonId") Long lessonId){
		  try {
	            AnsReview ansReview = ansReviewService.addAnsReview(ansReviewRequest.getName(), ansReviewRequest.getExplainRight(), ansReviewRequest.getExplainWrong(), lessonId);
	            return new ResponseEntity<>(ansReview, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	  }
	  
	  @GetMapping("/getAnsReviewByLessonId/{lessonId}")
	    public List<AnsReview> getAnsReviewByLessonId(@PathVariable Long lessonId) {
	        return ansReviewRepository.findAnsReviewByLessonId(lessonId);
	    }
}
