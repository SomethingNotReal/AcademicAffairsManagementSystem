package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.Achievement;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface AchievementService extends IService<Achievement> {
    Integer updateScore(Achievement achievement);
    Integer updateAchievement(Achievement achievement);
    List<Achievement> getAchievementByStudentId(String studentId);
    Achievement getByCurriculaIdAndStudentId(String curriculaId,String studentId);

    List<Achievement> getByCurriculaId(String curriculaId);

    List<Achievement> getByStudentIdAndCurriculaId(String curriculaId,String studentId);
    Integer removeByCurriculaId(String curriculaId);

    Integer removeByStudentId(String studentId);
}
