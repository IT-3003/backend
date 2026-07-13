package com.threefour.backend.ruchitha;

import org.springframework.stereotype.Service;

@Service
public class RuchithaService {
    private final RuchithaRepository ruchithaRepository;

    public RuchithaService(RuchithaRepository ruchithaRepository){
        this.ruchithaRepository = ruchithaRepository;
    }

    public static String addnumbers(int a, int b){
        return "The Sum is" + (a+b);
    }
}
