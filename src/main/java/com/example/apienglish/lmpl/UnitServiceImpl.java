package com.example.apienglish.lmpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apienglish.entity.Unit;
import com.example.apienglish.repository.UnitRepository;
import com.example.apienglish.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getUnitById(Long unitId) {
        return unitRepository.findById(unitId).orElse(null);
    }

    @Override
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public void deleteUnit(Long unitId) {
        unitRepository.deleteById(unitId);
    }

	@Override
	public List<Unit> getUnitsByLevelId(Long levelId) {
		 return unitRepository.findUnitsByLevelId(levelId);
	}
    
    
}

