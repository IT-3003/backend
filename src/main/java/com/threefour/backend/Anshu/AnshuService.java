package com.threefour.backend.Anshu;

import org.springframework.stereotype.Service;


@Service
public class AnshuService {
    public final AnshuRepository anshuRepository;


    public AnshuService(AnshuRepository anshuRepository) {
        this.anshuRepository = anshuRepository;
    }
    public static String addnumbers(int a, int b) {
    return "The sum is "+(a+b);
    }

}
