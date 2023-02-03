package com.chen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.Curricula;

import java.util.List;

public interface CurriculaService extends IService<Curricula> {
    List<Curricula> getMyCurricula(String teacherId);
    Integer updateByCurriculaId(Curricula curricula);
    Integer deleteCurriculaById(String curriculaId);

    IPage<Curricula> getPage(int currentPage, int pageSize);

    IPage<Curricula> getPage(int currentPage, int pageSize, String message);
}
