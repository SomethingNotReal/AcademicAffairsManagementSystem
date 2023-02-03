package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.SetCurricula;

import java.util.List;

public interface SetCurriculaService extends IService<SetCurricula> {
    List<SetCurricula> getByClassroom(String classRoom);
    List<SetCurricula> getByCurriculaId(String curriculaId);
    Integer updateByCurriculaId(SetCurricula setCurricula);

    Integer deleteByCurriculaId(String curriculaId);
}
