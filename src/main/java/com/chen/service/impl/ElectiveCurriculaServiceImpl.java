package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.ElectiveCurriculaDao;
import com.chen.domain.ElectiveCurricula;
import com.chen.service.ElectiveCurriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectiveCurriculaServiceImpl extends ServiceImpl<ElectiveCurriculaDao, ElectiveCurricula> implements ElectiveCurriculaService {
    @Autowired
    ElectiveCurriculaDao electiveCurriculaDao;
    @Override
    public Integer updateByCurriculaId(ElectiveCurricula electiveCurricula) {
        return electiveCurriculaDao.updateByCurriculaId(electiveCurricula);
    }

    @Override
    public Integer deleteCurriculaById(String curriculaId) {
        return electiveCurriculaDao.deleteCurriculaById(curriculaId);
    }

    @Override
    public List<ElectiveCurricula> getCurriculaByTeacherId(String teacherId) {
        return electiveCurriculaDao.selectCurriculaByTeacherId(teacherId);
    }

    @Override
    public IPage<ElectiveCurricula> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        electiveCurriculaDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<ElectiveCurricula> getPage(int currentPage, int pageSize, String message) {
        QueryWrapper<ElectiveCurricula> lqw = new QueryWrapper<ElectiveCurricula>();
        lqw.like("curricula_name",message);
        IPage page = new Page(currentPage,pageSize);
        electiveCurriculaDao.selectPage(page,lqw);
        return page;
    }
}
