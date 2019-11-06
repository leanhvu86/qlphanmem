package com.example.service;

import com.example.model.News;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    public Iterable<News> getAllNews();

    public News getNewsById(Integer id);

    Page<News> findAll(Pageable pageable);

    public boolean saveNews(News news);
}
