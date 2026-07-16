package com.threefour.backend.thinula;

import org.springframework.stereotype.Service;

@Service
public class ThinulaService {
    private final ThinulaRepository thinulaRepository;

    public ThinulaService(ThinulaRepository thinulaRepository) {
        this.thinulaRepository = thinulaRepository;
    }

    public static String addnumbers(int a , int b){
        return "The sum is "+ (a+b);
    }
}
