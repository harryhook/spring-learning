package com.coderunning.service;

import com.coderunning.dao.ColorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class ColorService {
    @Autowired
    private ColorDao colorDao2;

    public void printColor() {
        System.out.println(colorDao2);
    }

    @Override
    public String toString() {
        return "ColorService{" +
                "colorDao=" + colorDao2 +
                '}';
    }
}
