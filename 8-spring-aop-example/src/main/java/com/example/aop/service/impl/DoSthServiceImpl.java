package com.example.aop.service.impl;

import com.example.aop.service.DoSthService;
import org.springframework.stereotype.Service;

@Service
public class DoSthServiceImpl implements DoSthService {

    @Override
    public void doSth() {
        System.out.println("DoSthServiceImpl---doSth");
    }
}
