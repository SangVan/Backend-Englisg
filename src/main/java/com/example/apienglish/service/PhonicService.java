package com.example.apienglish.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.PhonicRepository;
import com.example.apienglish.repository.UnitRepository;


@Service
public class PhonicService {
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private PhonicRepository phonicsRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
    public Phonics uploadPhonics(MultipartFile file, MultipartFile audio ,String name, Long unitId, String mean) throws IOException {
    	Unit unit = unitRepository.findById(unitId).orElseThrow();

       
        
	        Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	        
	        Map<?, ?> params = ObjectUtils.asMap("resource_type", "video");
	        Map<?, ?> uploadResult = cloudinary.uploader().upload(audio	.getBytes(), params);
	        
	        Phonics phonics = new Phonics();
	        phonics.setUnit(unit);
	        phonics.setName(name);
	        phonics.setImageUrl(cloudinaryResponse.get("url").toString());
	        phonics.setAudioUrl(uploadResult.get("url").toString());
	        phonics.setMean(mean);
	        return phonicsRepository.save(phonics);
        
    }
   
    
    public List<Phonics> getAllPhonics() {
        return phonicsRepository.findAll();
    }

    public Phonics getPhonicById(Long id) {
        return phonicsRepository.findById(id)
                .orElse(null);
    }
    public String deletePhonics(Long unitId) {
    	List<Phonics> listPhonics  = phonicsRepository.findPhonicsByUnitId(unitId);
        if (!listPhonics.isEmpty()) {
        	phonicsRepository.deleteAll(listPhonics);
            return "Delete Successfully";
        }
        return "Error on Server";
    }
    
    public Phonics getPhonicsById(Long unitId) {
        return phonicsRepository.findById(unitId)
                .orElse(null);
    }
}
