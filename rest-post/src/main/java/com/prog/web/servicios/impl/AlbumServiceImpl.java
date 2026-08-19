package com.prog.web.servicios.impl;

import com.prog.web.db.Album;
import com.prog.web.repositories.AlbumRepository;
import com.prog.web.servicios.interfaces.AlbumService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Inject
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Integer id) {
        return albumRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    @Transactional
    public void update(Integer id, Album album) {
        albumRepository.findOptionalBy(id).ifPresent(existing -> {
            album.setId(id);
            albumRepository.save(album);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        albumRepository.findOptionalBy(id).ifPresent(albumRepository::remove);
    }
}