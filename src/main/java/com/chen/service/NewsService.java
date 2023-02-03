package com.chen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.News;

public interface NewsService extends IService<News> {
    Integer deleteNewsById(String id);
    Integer updateNewsById(News news);

    IPage<News> getPage(int currentPage, int pageSize);

    IPage<News> getPage(int currentPage, int pageSize, String message);
}
