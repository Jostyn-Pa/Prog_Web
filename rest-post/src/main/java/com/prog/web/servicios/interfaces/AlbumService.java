package com.prog.web.servicios.interfaces;

import com.prog.web.db.Album;
import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();
    Optional<Album> findById(Integer id);
    void save(Album album);
    void update(Integer id, Album album);
    void delete(Integer id);
}