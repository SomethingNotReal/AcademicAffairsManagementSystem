package com.chen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.controller.utils.Resource;
import com.chen.domain.News;
import com.chen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/{currentPage}/{pageSize}")
    public Resource getNews(@PathVariable int currentPage, @PathVariable int pageSize){
        IPage<News> page=newsService.getPage(currentPage,pageSize);
        if( currentPage > page.getPages()){
            page = newsService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);
    }
    @GetMapping("/getNewsByLikeMessage/{currentPage}/{pageSize}/{message}")
    public Resource getNewsByLikeMessage(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String message){
        IPage<News> page=newsService.getPage(currentPage,pageSize,message);
        if( currentPage > page.getPages()){
            page = newsService.getPage((int)page.getPages(), pageSize,message);
        }
        return new Resource(true,page);
    }
}
