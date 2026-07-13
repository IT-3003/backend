package com.threefour.backend.himandi;

import org.springframework.stereotype.Service;

@Service
public class HimandiService {
    private final HimandiRepository himandiRepository;

    public HimandiService(HimandiRepository himandiRepository) {
        this.himandiRepository = himandiRepository;
    }

    public static String addnumbers(int a, int b){
        return "This sum is" + (a+b);
    }
}
