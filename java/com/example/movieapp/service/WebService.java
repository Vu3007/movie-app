package com.example.movieapp.service;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public List<Movie> getHotMovie() {
        return movieRepository.findTop10ByStatusOrderByRatingDesc(true);
    }

    public Movie getMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true).orElse(null);
    }

    public List<Movie> getRelatedMovies(Movie movie) {
        return movieRepository
                .findTop6ByTypeAndStatusAndIdNotOrderByRatingDesc(movie.getType(), true, movie.getId());
    }

    public Object getInformationUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


}
