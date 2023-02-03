package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NewsDao extends BaseMapper<News> {
    @Update("update news set id=#{id},message=#{message},release_time=#{releaseTime},location=#{location} where id=#{id}")
    Integer updateNewsById(News news);
    @Update("delete from news where id=#{id}")
    Integer deleteNewsById(String id);
}
