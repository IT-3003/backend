package com.threefour.backend.dinuvi;

import org.springframework.stereotype.Service;

@Service
public class dinuviService {
    private final dinuviRepository dinuviRepository;

    public dinuviService(dinuviRepository dinuviRepository){
        this.dinuviRepository = dinuviRepository;
    }

    public static String addnumbers(int a, int b){
        return "The sum is" + (a+b);
    }
}
