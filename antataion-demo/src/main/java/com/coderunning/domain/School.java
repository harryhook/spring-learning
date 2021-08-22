package com.coderunning.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mySchool")
public class School {


    @Value("peking")
    private String name;
    @Value("shangdibaijie")
    private String address;

    public School() {
        System.out.println("无参构造1");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
