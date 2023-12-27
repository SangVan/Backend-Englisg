package com.example.apienglish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.apienglish.entity.AnsGrammar;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.repository.AnsGrammarRepository;
import com.example.apienglish.service.AnsGrammarService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AnsGrammarController {
	@Autowired
	private AnsGrammarRepository ansGrammarRepository;
	
	@Autowired
	private AnsGrammarService ansGrammarService;

  
    @GetMapping
    public List<AnsGrammar> getAllAnsGrammars() {
        return ansGrammarService.getAllAnsGrammars();
    }

//    @GetMapping("/{id}")
//    public AnsGrammar getAnsGrammarById(@PathVariable int id) {
//        return ansGrammarService.getAnsGrammarById(id)
//                .orElse(null);
//    }
    
    @GetMapping("/getAnsGrammarByUnitId/{unitId}")
    public List<AnsGrammar> getAnsGrammarByUnitId(@PathVariable Long unitId) {
        return ansGrammarRepository.findAnsGrammarsByUnitId(unitId);
    }

 
    
    @PostMapping("/units/{unitId}/addAnsGrammar")
    public ResponseEntity<AnsGrammar> addSong(@RequestParam("answer") String answer,@RequestParam("explainTest") String explain,@PathVariable("unitId") Long unitId ) {
        try {
        	AnsGrammar addAnsGrammar = ansGrammarService.saveAnsGrammar(answer,explain,unitId);
            return new ResponseEntity<>(addAnsGrammar, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    @DeleteMapping("/deleteAnsGrammarsByUnitId/{unitId}")
    public void deleteAnsGrammarsByUnitId(@PathVariable Long unitId) {
	  ansGrammarService.deleteAnsGrammar(unitId);
    }	
}
