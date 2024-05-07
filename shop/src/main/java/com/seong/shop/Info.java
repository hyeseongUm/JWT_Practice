package com.seong.shop;


import lombok.Setter;

public class Info {

    private String name;
    private Integer age;

    public void AgePlus(){
        this.age += 1;
    }

    public void setAge(Integer age) {
        if(age >= 0 && age < 100){
            this.age = age;
        }
    }
}
