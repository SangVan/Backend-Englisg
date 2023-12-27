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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.repository.LessonRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.UnitRepository;
import com.example.apienglish.service.LessonService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LessonController {
	
	@Autowired
	  private LessonService lessonService;
	
	@Autowired
	  private LessonRepository lessonRepository;

	  @Autowired
	  private UnitRepository unitRepository;
	  
//	  @Autowired 
//	  private LessonRepository lessonRepository;
	 //Grammar
    @PostMapping("/lesson/{unitId}/addLesson")
    public ResponseEntity<Lesson> addLesson(
    		@RequestParam("lessonNum") String lessonNum,
    		@RequestParam("name") String name,
    		@RequestParam("file") MultipartFile file, 
    		@PathVariable("unitId") Long unitId) {
        try {
        	Lesson lesson = lessonService.addLesson(lessonNum, name, file, unitId);
            return new ResponseEntity<>(lesson, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getLessonByUnitId/{unitId}")
    public List<Lesson> getLessonByUnitId(@PathVariable Long unitId) {
        return lessonRepository.findLessonByUnitId(unitId);
    }
}
