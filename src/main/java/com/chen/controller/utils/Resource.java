package com.chen.controller.utils;

import lombok.Data;

@Data
public class Resource {
    private Boolean flag;
    private Object data;
    private String msg;
    private int size;
    public Resource(){}

    public Resource(Boolean flag){
        this.flag = flag;
    }

    public Resource(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }

    public Resource(Boolean flag,Object data,int size){
        this.flag = flag;
        this.data = data;
        this.size=size;
    }
    public Resource(Boolean flag,Object data,String msg){
        this.flag = flag;
        this.data = data;
        this.msg=msg;
    }

    public Resource(Boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }

    public Resource(String msg){
        this.flag = false;
        this.msg = msg;
    }
}
