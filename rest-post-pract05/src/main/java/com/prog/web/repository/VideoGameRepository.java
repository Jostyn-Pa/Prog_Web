package com.prog.web.repository;

import com.prog.web.db.VideoGame;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends FullEntityRepository<VideoGame,Integer> {
    //Juegos por plataforma exacta
    List<VideoGame> findByPlatform(String platform);

    //Juegos cuyo precio sea menor o igual a un valor dado
    List<VideoGame> findByPriceLessThanEquals(Double price);
}
