package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.SetCurriculaDao;
import com.chen.domain.SetCurricula;
import com.chen.service.SetCurriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetCurriculaServiceImpl extends ServiceImpl<SetCurriculaDao, SetCurricula> implements SetCurriculaService {
    @Autowired
    SetCurriculaDao setCurriculaDao;

    @Override
    public List<SetCurricula> getByClassroom(String classRoom) {
        return setCurriculaDao.selectByClassroom(classRoom);
    }

    @Override
    public List<SetCurricula> getByCurriculaId(String curriculaId) {
        return setCurriculaDao.selectByCurriculaId(curriculaId);
    }

    @Override
    public Integer updateByCurriculaId(SetCurricula setCurricula) {
        return setCurriculaDao.updateByCurriculaId(setCurricula);
    }

    @Override
    public Integer deleteByCurriculaId(String curriculaId) {
        return setCurriculaDao.deleteByCurriculaId(curriculaId);
    }
}
