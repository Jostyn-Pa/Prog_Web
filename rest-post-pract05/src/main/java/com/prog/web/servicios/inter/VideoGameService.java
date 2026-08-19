package com.prog.web.servicios.inter;

import com.prog.web.db.VideoGame;

import java.util.List;
import java.util.Optional;

public interface VideoGameService {
    List<VideoGame> findAll();
    Optional<VideoGame> findById(Integer id);
    VideoGame save(VideoGame videoGame);
    Optional<VideoGame> update(Integer id, VideoGame videoGame);
    boolean delete(Integer id);
    List<VideoGame> findByPlatform(String platform);
    List<VideoGame> findByPriceLessThanEquals(Double price);
}
