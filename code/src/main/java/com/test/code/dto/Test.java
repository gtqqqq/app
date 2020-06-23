package com.test.code.dto;

import lombok.Data;

@Data
public class Test {
    /*姓名*/    private String name;
    /*id*/    private Integer Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
