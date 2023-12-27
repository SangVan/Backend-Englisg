package com.example.apienglish.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.exception.ResourceNotFoundException;
import com.example.apienglish.lmpl.UnitServiceImpl;
import com.example.apienglish.repository.GrammarRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.PhonicRepository;
import com.example.apienglish.repository.SongRepository;
import com.example.apienglish.repository.StoriesRepository;
import com.example.apienglish.repository.UnitRepository;
import com.example.apienglish.service.GrammarService;
import com.example.apienglish.service.PhonicService;
import com.example.apienglish.service.SongService;
import com.example.apienglish.service.StoriesService;
import com.example.apienglish.service.UnitService;



@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UnitController {
	 @Autowired 	
	  private LevelRepository levelRepository;

	  @Autowired
	  private UnitRepository unitRepository;
	  
	  @Autowired
	  private UnitService unitService;
	  
	  @Autowired
	  private StoriesService storiesService;
	  
	  @Autowired
	  private StoriesRepository storiesRepository;
	  
	  @Autowired
	  private SongService songService;
	  
	  
	  @Autowired
	  private SongRepository songRepository;
	  
	  @Autowired
	  private PhonicService phonicService;
	  
	  
	  @Autowired
	  private PhonicRepository phonicRepository;
	  	  
	  @Autowired
	  private GrammarService grammarService;
	  
	  @Autowired
	  private GrammarRepository grammarRepository;
	  //Unit Method
	  //Hiển thị tất cả Unit
	 	@GetMapping("/units/getAllUnits")
	 	public ResponseEntity<?> getAllUnits() {
	 		return new ResponseEntity<>(unitService.getAllUnits(), HttpStatus.OK);
	 	}
	  
//	 	@GetMapping("/units/findUnitsByUnitNameTest")
//	 	public ResponseEntity<?> findUnitsByUnitNameTest() {
//	 		return new ResponseEntity<>(unitRepository.findUnitsByUnitNameTest(), HttpStatus.OK);
//	 	}
//	 	
	  @GetMapping("/unitsByLevel/{levelId}")
		public ResponseEntity<?> getUnitsByLevelId(@PathVariable("levelId") Long levelId) {
			return new ResponseEntity<>(unitRepository.findUnitsByLevelId(levelId), HttpStatus.OK);
		}
	//Đếm số bài học đã tạo
	  @GetMapping("/countUnits")
		public ResponseEntity<?> countUnits() {
			return new ResponseEntity<>(unitRepository.countUnits(), HttpStatus.OK);
	  }
	  
	//Đếm số bài học theo cấp độ
	  @GetMapping("/countUnitsByLevelId/{levelId}")
		public ResponseEntity<?> countUnitsByLevelId(@PathVariable Long levelId) {
			return new ResponseEntity<>(unitRepository.countUnitsByLevelId(levelId), HttpStatus.OK);
	  }
	  
	  @GetMapping("/units/{id}")
	  public ResponseEntity<Unit> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
	    Unit comment = unitRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

	    return new ResponseEntity<>(comment, HttpStatus.OK);
	  }

	  @PostMapping("/levels/{levelId}/units")
	  public ResponseEntity<Unit> createUnit(@PathVariable(value = "levelId") Long levelId,
	      @RequestBody Unit unitRequest) {
	    Unit unit = levelRepository.findById(levelId).map(level -> {
	    	unitRequest.setLevel(level);
	      return unitRepository.save(unitRequest);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + levelId));

	    return new ResponseEntity<>(unit, HttpStatus.CREATED);
	  }

	  @PutMapping("/updateUnit/{id}")
	  public ResponseEntity<Unit> updateUnit(@PathVariable("id") long id, @RequestBody Unit unitRequest) {
		  Unit unit = unitRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

	
		  unit.setUnitName(unitRequest.getUnitName());
		  unit.setStatus(unitRequest.getStatus());

	    return new ResponseEntity<>(unitRepository.save(unit), HttpStatus.OK);
	  }

	  @DeleteMapping("/deleteUnit/{id}")
	  public ResponseEntity<HttpStatus> deleteUnit(@PathVariable("id") long id) {
	    unitRepository.deleteById(id);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
  
//	  @DeleteMapping("/deleteLevel/{levelId}/units")
//	  public ResponseEntity<List<Unit>> deleteLevel(@PathVariable(value = "levelId") Long levelId) {
//	    if (!levelRepository.existsById(levelId)) {
//	      throw new ResourceNotFoundException("Not found Tutorial with id = " + levelId);
//	    }
//
//	    unitRepository.deleteByLevelId(levelId);
//	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	  }
	  
	  //Stories Method
	  @PostMapping("/units/{unitId}/addStories")
	    public ResponseEntity<Stories> uploadStories(@RequestParam("file") MultipartFile file, 
	    		@RequestParam("video") MultipartFile video,
	    		@RequestParam("storyName") String storyName,
	    		@PathVariable("unitId") Long unitId) {
	        try {
	            Stories uploadedImage = storiesService.uploadStories(file, video, storyName, unitId);
	            return new ResponseEntity<>(uploadedImage, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	  
	  @GetMapping("/getStoriesByUnitId/{unitId}")
	    public List<Stories> getStoriesByUnitId(@PathVariable Long unitId) {
	        return storiesRepository.findStoriesByUnitId(unitId);
	  }
	  
	  @DeleteMapping("/deleteStoriesByUnitId/{unitId}")
	    public void deleteStoriesByUnitId(@PathVariable Long unitId) {
		  storiesService.deleteStories(unitId);
	    }
	  
	  
	  //Songs
	    @PostMapping("/units/{unitId}/addSongs")
	    public ResponseEntity<Songs> addSong(@RequestParam("file") MultipartFile file, @RequestParam("audio") MultipartFile audio, 
	    		@RequestParam("name") String name ,@PathVariable("unitId") Long unitId) {
	        try {
	            Songs uploadSong = songService.uploadSong(file, audio, name, unitId);
	            return new ResponseEntity<>(uploadSong, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @GetMapping("/getSongsByUnitId/{unitId}")
	    public List<Songs> getSongsByUnitId(@PathVariable Long unitId) {
	        return songRepository.findSongsByUnitId(unitId);
	    }
	    
	    @DeleteMapping("/deleteSongsByUnitId/{unitId}")
	    public void deleteSongsByUnitId(@PathVariable Long unitId) {
		  songService.deleteSongs(unitId);
	    }	    
	    
	  //Phonics
	    @PostMapping("/units/{unitId}/addPhonics")
	    public ResponseEntity<Phonics> uploadPhonics(@RequestParam("file") MultipartFile file, 
	    	@RequestParam("audio") MultipartFile audio, @RequestParam("name") String name, 
	    	@PathVariable("unitId") Long unitId, @RequestParam("mean") String mean) {
	        try {
	            Phonics uploadPhonics = phonicService.uploadPhonics(file, audio, name, unitId, mean);
	            return new ResponseEntity<>(uploadPhonics, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @GetMapping("/getPhonicsByUnitId/{unitId}")
	    public List<Phonics> getPhonicsByUnitId(@PathVariable Long unitId) {
	        return phonicRepository.findPhonicsByUnitId(unitId);
	    }
	    
	    @DeleteMapping("/deletePhonicsByUnitId/{unitId}")
	    public void deletePhonicsByUnitId(@PathVariable Long unitId) {
		  phonicService.deletePhonics(unitId);
	    }	
	    
	   
	    
	  //Grammar	   
	    @PostMapping("/units/{unitId}/addGrammar")
	    public ResponseEntity<Grammar> addGrammar(
	    		@RequestParam("file1") MultipartFile file1,
	    		@RequestParam("file2") MultipartFile file2,
	    		@RequestParam("name") String name,
	    		@PathVariable("unitId") Long unitId) 
	    {
	        try {
	        	Grammar addGrammar = grammarService.addGrammar(file1, file2, name, unitId);
	            return new ResponseEntity<>(addGrammar, HttpStatus.CREATED);
	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @GetMapping("/getGrammarByUnitId/{unitId}")
	    public List<Grammar> getGrammarByUnitId(@PathVariable Long unitId) {
	        return grammarRepository.findGrammarsByUnitId(unitId);
	  }
	    
	    @DeleteMapping("/deleteGrammarByUnitId/{unitId}")
	    public void deleteGrammarByUnitId(@PathVariable Long unitId) {
		  grammarService.deleteGrammar(unitId);
	    }

}
