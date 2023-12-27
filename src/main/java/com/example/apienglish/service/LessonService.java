package com.example.apienglish.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.GrammarRepository;
import com.example.apienglish.repository.LessonRepository;
import com.example.apienglish.repository.UnitRepository;

@Service
public class LessonService {
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
    public Lesson addLesson(String lessonNum, String name, MultipartFile file, Long unitId) throws IOException {
    	 Unit unit = unitRepository.findById(unitId).orElseThrow();

        	 Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
             
        	 Lesson lesson = new Lesson();
        	 lesson.setImageUrl(cloudinaryResponse.get("url").toString());
        	 lesson.setLessonNumber(lessonNum);
        	 lesson.setName(name);
        	 lesson.setUnit(unit);
            
            
             return lessonRepository.save(lesson);
       
    }
    
    
    public List<Lesson> getAllSong() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElse(null);
    }
    

    public void deleteLesson(Long id) {
    	lessonRepository.deleteById(id);
    }
}
