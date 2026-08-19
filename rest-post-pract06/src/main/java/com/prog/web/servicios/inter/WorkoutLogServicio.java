package com.prog.web.servicios.inter;

import com.prog.web.db.WorkoutLog;

import java.util.List;
import java.util.Optional;

public interface WorkoutLogServicio {
    List<WorkoutLog> findAll();
    Optional<WorkoutLog> findById(Integer id);
    WorkoutLog save(WorkoutLog workoutLog);
    Optional<WorkoutLog> update(Integer id, WorkoutLog workoutLog);
    boolean delete(Integer id);
    List<WorkoutLog> findBySplitType(String splitType);
    List<WorkoutLog> findByWeightGreaterThanEquals(Double weight);
}
