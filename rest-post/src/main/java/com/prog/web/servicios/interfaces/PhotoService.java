package com.prog.web.servicios.interfaces;

import com.prog.web.db.Photo;
import java.util.List;
import java.util.Optional;

public interface PhotoService {
    List<Photo> findAll();
    Optional<Photo> findById(Integer id);
    void save(Photo photo);
    void update(Integer id, Photo photo);
    void delete(Integer id);
}