package com.prog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutLogDto {
    private Integer id;
    private String exerciseName;
    private String splitType;
    private Double weight;
    private Integer reps;
}
