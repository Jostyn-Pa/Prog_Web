package com.prog.web.servicios.impl;

import com.prog.web.db.Photo;
import com.prog.web.repositories.PhotoRepository;
import com.prog.web.servicios.interfaces.PhotoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Inject
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<Photo> findById(Integer id) {
        return photoRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    @Transactional
    public void update(Integer id, Photo photo) {
        photoRepository.findOptionalBy(id).ifPresent(existing -> {
            photo.setId(id);
            photoRepository.save(photo);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        photoRepository.findOptionalBy(id).ifPresent(photoRepository::remove);
    }
}