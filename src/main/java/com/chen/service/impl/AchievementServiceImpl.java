package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.AchievementDao;
import com.chen.domain.Achievement;
import com.chen.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImpl extends ServiceImpl<AchievementDao, Achievement> implements AchievementService {
    @Autowired
    AchievementDao achievementDao;
    @Override
    public Integer updateScore(Achievement achievement) {
        return achievementDao.updateByStudentIdAndCurriculaId(achievement);
    }

    @Override
    public Integer updateAchievement(Achievement achievement) {
        return achievementDao.updateAchievement(achievement);
    }

    @Override
    public List<Achievement> getAchievementByStudentId(String studentId) {
        return achievementDao.selectByStudentId(studentId);
    }

    @Override
    public Achievement getByCurriculaIdAndStudentId(String curriculaId,String studentId) {
        return achievementDao.selectByCurriculaIdAndStudentId(curriculaId,studentId);
    }

    @Override
    public List<Achievement> getByCurriculaId(String curriculaId) {
        return achievementDao.selectByCurriculaId(curriculaId);
    }

    @Override
    public List<Achievement> getByStudentIdAndCurriculaId(String curriculaId, String studentId) {
        return achievementDao.selectByStudentIdAndCurriculaId(curriculaId,studentId);
    }

    @Override
    public Integer removeByCurriculaId(String curriculaId) {
        return achievementDao.deleteByCurriculaId(curriculaId);
    }

    @Override
    public Integer removeByStudentId(String studentId) {
        return achievementDao.deleteByStudentId(studentId);
    }
}
