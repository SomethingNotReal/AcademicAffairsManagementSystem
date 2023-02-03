package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.CurriculaDao;
import com.chen.domain.Curricula;
import com.chen.service.CurriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculaServiceImpl extends ServiceImpl<CurriculaDao,Curricula> implements CurriculaService {
    @Autowired
    CurriculaDao curriculaDao;

    @Override
    public List<Curricula> getMyCurricula(String teacherId) {
        return curriculaDao.selectCurriculaByTeacherId(teacherId);
    }

    @Override
    public Integer updateByCurriculaId(Curricula curricula) {
        return curriculaDao.updateByCurriculaId(curricula);
    }

    @Override
    public Integer deleteCurriculaById(String curriculaId) {
        return curriculaDao.deleteCurriculaById(curriculaId);
    }

    @Override
    public IPage<Curricula> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        curriculaDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Curricula> getPage(int currentPage, int pageSize, String message) {
        QueryWrapper<Curricula> lqw = new QueryWrapper<Curricula>();
        lqw.like("curricula_name",message);
        IPage page = new Page(currentPage,pageSize);
        curriculaDao.selectPage(page,lqw);
        return page;
    }

}
