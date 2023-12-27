package com.example.apienglish.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.StoriesRepository;
import com.example.apienglish.repository.UnitRepository;

@Service
public class StoriesService {
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private StoriesRepository storiesRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
//    public Stories uploadStories(MultipartFile file, MultipartFile video ,String name, Long unitId) throws IOException {
//	    
//        Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//        
//        Map<?, ?> params = ObjectUtils.asMap("resource_type", "video");
//        Map<?, ?> uploadResult = cloudinary.uploader().upload(video	.getBytes(), params);
//        
//        Stories stories = new Stories();
//        Unit unit = unitRepository.findById(unitId).orElseThrow();
//
//        stories.setUnit(unit);
//        stories.setStoryName(name);
//        stories.setImageUrl(cloudinaryResponse.get("url").toString());
//        stories.setVideoUrl(uploadResult.get("url").toString());
//       
//        return storiesRepository.save(stories);
//    }
	    public Stories uploadStories(MultipartFile file, MultipartFile video, String name, Long unitId) throws IOException {
	        Unit unit = unitRepository.findById(unitId).orElseThrow();
	
	        // Sử dụng truy vấn JPA để kiểm tra xem có Stories liên kết với Unit hay không
	        boolean hasStories = storiesRepository.existsByUnit(unit);
	
	        if (hasStories) {
	            // Đã tồn tại một Stories liên kết với Unit, xử lý hoặc trả về thông báo lỗi.
	        	throw new RuntimeException("Đã tồn tại Stories liên kết với Unit.");
	        } else {
	            // Tiếp tục tạo mới Stories
	            Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	            Map<?, ?> params = ObjectUtils.asMap("resource_type", "video");
	            Map<?, ?> uploadResult = cloudinary.uploader().upload(video.getBytes(), params);
	            Stories stories = new Stories();
	            stories.setUnit(unit);
	            stories.setStoryName(name);
	            stories.setImageUrl(cloudinaryResponse.get("url").toString());
	            stories.setVideoUrl(uploadResult.get("url").toString());
	            return storiesRepository.save(stories);
	        }
	    }


    
    public Stories getStoriesById(Long unitId) {
        return storiesRepository.findById(unitId)
                .orElse(null);
    }
    
    public String deleteStories(Long unitId) {
    	Stories stories = storiesRepository.findOneStoriesByUnitId(unitId);
        if (stories != null) {
        	storiesRepository.delete(stories);
            return "Delete Successfully";
        }
        return "Error on Server";
    }
}
