package com.chen.dao;

import com.chen.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorDao  {
    @Select("select * from administrator where name=#{name} and password=#{password}")
    Administrator selectStudentByNameAndPassword(String name, String password);


}
