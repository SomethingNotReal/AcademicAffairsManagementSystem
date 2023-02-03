package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.NewsDao;
import com.chen.domain.News;
import com.chen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {
    @Autowired
    NewsDao newsDao;
    @Override
    public Integer deleteNewsById(String id) {
        return newsDao.deleteNewsById(id);
    }

    @Override
    public Integer updateNewsById(News news) {
        return newsDao.updateNewsById(news);
    }

    @Override
    public IPage<News> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        newsDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<News> getPage(int currentPage, int pageSize, String message) {
        QueryWrapper<News> lqw = new QueryWrapper<News>();
        lqw.like("message",message);
        IPage page = new Page(currentPage,pageSize);
        newsDao.selectPage(page,lqw);
        return page;
    }
}
