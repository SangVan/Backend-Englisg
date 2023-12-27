package com.example.apienglish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.apienglish.entity.AnsGrammar;
import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.AnsGrammarRepository;
import com.example.apienglish.repository.SongRepository;
import com.example.apienglish.repository.UnitRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnsGrammarService {
    private final AnsGrammarRepository ansGrammarRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired UnitRepository unitRepository;

//    @Autowired
//     private AnsGrammarRepository ansGrammarRepository;
//    
    @Autowired
    public AnsGrammarService(AnsGrammarRepository ansGrammarRepository) {
        this.ansGrammarRepository = ansGrammarRepository;
    }

    public List<AnsGrammar> getAllAnsGrammars() {
        return ansGrammarRepository.findAll();
    }

    public Optional<AnsGrammar> getAnsGrammarById(int id) {
        return ansGrammarRepository.findById(id);
    }

    public AnsGrammar saveAnsGrammar(String answer ,String explainTest,Long unitId) throws IOException {
    	Unit unit = unitRepository.findById(unitId).orElseThrow();
            
       	 AnsGrammar ansGrammar = new AnsGrammar();
       	 ansGrammar.setAnswer(answer);
       	 ansGrammar.setExplainTest(explainTest);
       	 ansGrammar.setUnit(unit);
         return ansGrammarRepository.save(ansGrammar);
    }
    
    
   

    
    public String deleteAnsGrammar(Long unitId) {
    	List<AnsGrammar> listAnsGrammars  = ansGrammarRepository.findAnsGrammarsByUnitId(unitId);
        if (!listAnsGrammars.isEmpty()) {
        	ansGrammarRepository.deleteAll(listAnsGrammars);
            return "Delete Successfully";
        }
        return "Error on Server";
    }
    
    
   
}
