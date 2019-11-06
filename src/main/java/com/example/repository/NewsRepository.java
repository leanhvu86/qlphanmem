package com.example.repository;

import com.example.model.News;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "newsRepository")
public interface NewsRepository extends CrudRepository<News, Integer> {
    Page<News> findAll(Pageable pageable);
    List<News> findById(Integer id);
}
