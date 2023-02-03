package com.chen.service.impl;

import com.chen.dao.AdministratorDao;
import com.chen.domain.Administrator;
import com.chen.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    AdministratorDao administratorDao;
    @Override
    public Administrator login(String name, String password) {
        return administratorDao.selectStudentByNameAndPassword(name,password);
    }

    @Override
    public Administrator verify(String name, String password) {
        return administratorDao.selectStudentByNameAndPassword(name,password);
    }


}
