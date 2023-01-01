package com.coderunning.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class IocService {

    @Resource
    private ColorService colorService;

    public ColorService getColorService() {
        return colorService;
    }
}
