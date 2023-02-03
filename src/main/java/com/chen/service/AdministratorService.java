package com.chen.service;

import com.chen.domain.Administrator;


public interface AdministratorService {
    Administrator login(String name,String password);
    Administrator verify(String name,String password);

}
