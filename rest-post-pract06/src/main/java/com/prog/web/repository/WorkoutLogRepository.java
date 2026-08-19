package com.prog.web.repository;

import com.prog.web.db.WorkoutLog;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface WorkoutLogRepository extends FullEntityRepository<WorkoutLog, Integer> {
    //Encontrar todos los registros por el tipo de división exacta
    List<WorkoutLog> findBySplitType(String splitType);
    //Encontrar registros donde el peso levantado sea mayor o igual a un valor dado
    List<WorkoutLog> findByWeightGreaterThanEquals(Double weight);
}
