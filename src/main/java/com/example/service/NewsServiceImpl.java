package com.example.service;

import com.example.model.News;
import com.example.model.Station;
import com.example.repository.NewsRepository;
import com.example.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {


    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Iterable<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Integer id) {
        return newsRepository.findOne(id);
    }


    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public boolean saveNews(News news) {
        try {
            newsRepository.save(news);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
