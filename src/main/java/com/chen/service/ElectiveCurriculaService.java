package com.chen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.ElectiveCurricula;

import java.util.List;

public interface ElectiveCurriculaService extends IService<ElectiveCurricula> {
    Integer updateByCurriculaId(ElectiveCurricula electiveCurricula);
    Integer deleteCurriculaById(String curriculaId);

    List<ElectiveCurricula> getCurriculaByTeacherId(String teacherId);
    IPage<ElectiveCurricula> getPage(int currentPage, int pageSize);

    IPage<ElectiveCurricula> getPage(int currentPage, int pageSize, String message);
}
