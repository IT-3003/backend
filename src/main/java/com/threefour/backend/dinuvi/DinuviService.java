package com.threefour.backend.dinuvi;

import org.springframework.stereotype.Service;

@Service
public class DinuviService {

    private final DinuviRepository dinuviRepository;

    public DinuviService(DinuviRepository dinuviRepository) {
        this.dinuviRepository = dinuviRepository;
    }

    public String addNumbers(int a, int b) {
        return "The sum is " + (a + b);
    }

    public Dinuvi saveDinuvi(Dinuvi dinuvi) {
        return dinuviRepository.save(dinuvi);
    }
}