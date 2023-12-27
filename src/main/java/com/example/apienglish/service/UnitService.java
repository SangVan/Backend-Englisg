package com.example.apienglish.service;

//
//
//import com.example.apienglish.entity.Unit;
//
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UnitService {
//    List<Unit> getAllUnits();
//    Optional<Unit> getUnitById(Long unitId);
//    Unit createUnit(Unit unit);
//    void deleteUnit(Long unitId);
//}
import java.util.List;

import com.example.apienglish.entity.Unit;

public interface UnitService {
    List<Unit> getAllUnits();
    Unit getUnitById(Long unitId);
    Unit createUnit(Unit unit);
    void deleteUnit(Long unitId);
    List<Unit> getUnitsByLevelId(Long levelId);
}
