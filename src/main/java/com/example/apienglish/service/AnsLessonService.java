package com.example.apienglish.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.AnsLesson;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.AnsLessonRepository;
import com.example.apienglish.repository.LessonRepository;
import com.example.apienglish.repository.PhonicRepository;
import com.example.apienglish.repository.UnitRepository;

@Service
public class AnsLessonService {
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private AnsLessonRepository ansLessonRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    public AnsLesson addAnsLesson(String name, String explain, Long lessonId) throws IOException {

    	Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
	        AnsLesson ansLesson = new AnsLesson();
	        
	        ansLesson.setName(name);
	        ansLesson.setExplain(explain);
	        ansLesson.setLesson(lesson);
	       
	        
	        return ansLessonRepository.save(ansLesson);
  
    }
   
    
    public List<AnsLesson> getAllAnsLesson() {
        return ansLessonRepository.findAll();
    }

    public AnsLesson getAnsLessonById(Long id) {
        return ansLessonRepository.findById(id)
                .orElse(null);
    }
//    public void deletePhonics(Long id) {
//    	ansLessonRepository.deleteById(id);
//    }
    
    
}
