package com.prog.web.servicios.impl;

import com.prog.web.db.VideoGame;
import com.prog.web.repository.VideoGameRepository;
import com.prog.web.servicios.inter.VideoGameService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VideoGameServiceImpl implements VideoGameService {

    private final VideoGameRepository  videoGameRepository;

    @Inject
    public VideoGameServiceImpl(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public List<VideoGame> findAll() {
        return videoGameRepository.findAll();
    }

    @Override
    public Optional<VideoGame> findById(Integer id) {
        return videoGameRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public VideoGame save(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }

    @Override
    @Transactional
    public Optional<VideoGame> update(Integer id, VideoGame videoGame) {
        return videoGameRepository.findOptionalBy(id).map(up -> {
            videoGame.setId(up.getId());
            return videoGameRepository.save(videoGame);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return videoGameRepository.findOptionalBy(id).map(rem -> {
            videoGameRepository.remove(rem);
            return true;
        }).orElse(false);
    }

    @Override
    public List<VideoGame> findByPlatform(String platform) {
        return videoGameRepository.findByPlatform(platform);
    }

    @Override
    public List<VideoGame> findByPriceLessThanEquals(Double price) {
        return videoGameRepository.findByPriceLessThanEquals(price);
    }
}
