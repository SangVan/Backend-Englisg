package com.example.apienglish.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.SongRepository;
import com.example.apienglish.repository.UnitRepository;


@Service
public class SongService {
		
	@Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private SongRepository songRepository;
    
    @Autowired
    private UnitRepository unitRepository;
    
    public Songs uploadSong(MultipartFile file, MultipartFile audio ,String name ,Long unitId) throws IOException {
    	 Unit unit = unitRepository.findById(unitId).orElseThrow();

         // Sử dụng truy vấn JPA để kiểm tra xem có Stories liên kết với Unit hay không
         boolean hasStories = songRepository.existsByUnit(unit);
         if (hasStories) {
             // Đã tồn tại một Stories liên kết với Unit, xử lý hoặc trả về thông báo lỗi.
         	throw new RuntimeException("Đã tồn tại Stories liên kết với Unit.");
         } else {
        	 Map<?, ?> cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
             
             Map<?, ?> params = ObjectUtils.asMap("resource_type", "video");
             Map<?, ?> uploadResult = cloudinary.uploader().upload(audio.getBytes(), params);
             
             Songs song = new Songs();
             song.setUnit(unit);
             song.setName(name);
             song.setImageUrl(cloudinaryResponse.get("url").toString());
             song.setAudioUrl(uploadResult.get("url").toString());
            
             return songRepository.save(song);
         }
    	
       
    }
    
    
    public List<Songs> getAllSong() {
        return songRepository.findAll();
    }

    public Songs getSongById(Long id) {
        return songRepository.findById(id)
                .orElse(null);
    }
    
    public Songs getSongsById(Long unitId) {
        return songRepository.findById(unitId)
                .orElse(null);
    }
   
    public String deleteSongs(Long unitId) {
    	Songs songs = songRepository.findOneSongsByUnitId(unitId);
        if (songs != null) {
        	songRepository.delete(songs);
            return "Delete Successfully";
        }
        return "Error on Server";
    }

}
