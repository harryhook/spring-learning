package com.coderunning.service;

import com.coderunning.dao.ColorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    @Autowired(required = false)
    private ColorDao colorDao2;

    public void printColor() {
        System.out.println(colorDao2);
    }

    @Override
    public String toString() {
        return "ColorService{" +
                "red=" + colorDao2 +
                '}';
    }
}
