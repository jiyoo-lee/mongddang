package com.jeeyulee.mongddang.genre.service;

import com.jeeyulee.mongddang.genre.domain.GenreDTO;
import com.jeeyulee.mongddang.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    public List<GenreDTO> retrieveGenre() {
        return genreRepository.selectAll();
    }
}
