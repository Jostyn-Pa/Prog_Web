package com.prog.web.servicios.impl;

import com.prog.web.db.WorkoutLog;
import com.prog.web.repository.WorkoutLogRepository;
import com.prog.web.servicios.inter.WorkoutLogServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class WorkoutLogServicioImpl implements WorkoutLogServicio {

    private final WorkoutLogRepository workoutLogRepository;

    @Inject
    public WorkoutLogServicioImpl(WorkoutLogRepository workoutLogRepository) {
        this.workoutLogRepository = workoutLogRepository;
    }

    @Override
    public List<WorkoutLog> findAll() {
        return workoutLogRepository.findAll();
    }

    @Override
    public Optional<WorkoutLog> findById(Integer id) {
        return workoutLogRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public WorkoutLog save(WorkoutLog workoutLog) {
        return workoutLogRepository.save(workoutLog);
    }

    @Override
    @Transactional
    public Optional<WorkoutLog> update(Integer id, WorkoutLog workoutLog) {
        return workoutLogRepository.findOptionalBy(id).map(up -> {
            workoutLog.setId(up.getId());
            return workoutLogRepository.save(workoutLog);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return workoutLogRepository.findOptionalBy(id).map(del -> {
            workoutLogRepository.remove(del);
            return true;
        }).orElse(false);
    }

    @Override
    public List<WorkoutLog> findBySplitType(String splitType) {
        return workoutLogRepository.findBySplitType(splitType);
    }

    @Override
    public List<WorkoutLog> findByWeightGreaterThanEquals(Double weight) {
        return workoutLogRepository.findByWeightGreaterThanEquals(weight);
    }
}
