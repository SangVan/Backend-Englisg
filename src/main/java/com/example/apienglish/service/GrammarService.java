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
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.GrammarRepository;
import com.example.apienglish.repository.UnitRepository;

@Service
public class GrammarService {
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private GrammarRepository grammarRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
    public Grammar addGrammar(MultipartFile file1, MultipartFile file2, String name ,Long unitId) throws IOException {
    	 Unit unit = unitRepository.findById(unitId).orElseThrow();

         // Sử dụng truy vấn JPA để kiểm tra xem có Stories liên kết với Unit hay không
         boolean hasStories = grammarRepository.existsByUnit(unit);
         if (hasStories) {
             // Đã tồn tại một Stories liên kết với Unit, xử lý hoặc trả về thông báo lỗi.
         	throw new RuntimeException("Đã tồn tại Stories liên kết với Unit.");
         } else {
        	 Map<?, ?> cloudinaryResponse1 = cloudinary.uploader().upload(file1.getBytes(), ObjectUtils.emptyMap());
        	 Map<?, ?> cloudinaryResponse2 = cloudinary.uploader().upload(file2.getBytes(), ObjectUtils.emptyMap());
             
        	 Grammar grammar = new Grammar();
        	 grammar.setImageUrl1(cloudinaryResponse1.get("url").toString());
        	 grammar.setImageUrl2(cloudinaryResponse2.get("url").toString());
        	 grammar.setName(name);
        	 grammar.setUnit(unit);
            
            
             return grammarRepository.save(grammar);
         }
    	
       
    }
    
    
    public List<Grammar> getAllSong() {
        return grammarRepository.findAll();
    }

    public Grammar getGrammarById(Long id) {
        return grammarRepository.findById(id)
                .orElse(null);
    }
    

    public String deleteGrammar(Long unitId) {
    	Grammar grammar  = grammarRepository.findOneGrammarsByUnitId(unitId);
        if (grammar != null) {
        	grammarRepository.delete(grammar);
            return "Delete Successfully";
        }
        return "Error on Server";
    }

}
